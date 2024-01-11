package com.lin.linapigateway.filter;

import com.lin.linapiclientsdk.util.SignUtil;
import com.lin.linapicommon.model.entity.InterfaceInfo;
import com.lin.linapicommon.model.entity.User;
import com.lin.linapicommon.service.InnerInterfaceInfoService;
import com.lin.linapicommon.service.InnerUserInterfaceInfoService;
import com.lin.linapicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Ordered就是执行顺序

/**
 * 全局过滤
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceService;

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    private static final String INTERFACE_HOST = "http://localhost:8123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        String path = INTERFACE_HOST + request.getPath().value();
        String method = request.getMethod().toString();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径" + path);
        log.info("请求方法" + method);
        log.info("请求参数" + request.getQueryParams());
        String sourceAddress = request.getRemoteAddress().getHostString();
        log.info("请求来源地址" + sourceAddress);
        log.info("请求来源地址" + request.getRemoteAddress());
        // 拿到响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 2. 黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            // 设置为403禁止访问
            response.setStatusCode(HttpStatus.FORBIDDEN);
            // 返回处理后的响应
            return response.setComplete();
        }
        // 3. 用户鉴权(ak/sk)
        // 从请求头中获取ak sk
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // 实际是去数据库中看是否有该用户
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error",e);
        }
        if (invokeUser == null) {
            return handleNoAuth(response);
        }
//        if (!accessKey.equals("lin")) {
//            return handleNoAuth(response);
//        }
        if (Long.parseLong(nonce) > 10000) {
            return handleNoAuth(response);
        }
        // 时间戳的时间检验 五分钟失效
        long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 5L * 60L;
        if (currentTime - Long.parseLong(timestamp) >= FIVE_MINUTES) {
            return handleNoAuth(response);
        }

        // 从数据库中调出secretKey
        // 从获取到的用户信息中获取用户的密钥
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtil.genSign(body, secretKey);
        if (sign == null || !sign.equals(serverSign)) {
            return handleNoAuth(response);
        }

        // 4. 请求的模拟接口是否存在？
        // 从数据库中查询模拟接口是否存在，以及请求方法是否匹配（还可以校验请求参数）
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInterfaceInfo error", e);
        }
        if (interfaceInfo == null) {
            handleNoAuth(response);
        }
        // todo 判断用户是否还有调用次数
        // 5. 请求转发，调用模拟接口
//        Mono<Void> filter = chain.filter(exchange);
        // 调用成功后要输入一个响应日志
        log.info("响应" + response.getStatusCode());
        // 6. 响应日志
        return handleResponse(exchange, chain, interfaceInfo.getId(), interfaceInfo.getUserId());
        // 调用成功后调用次数加一的逻辑写在handleResponse
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * 403错误
     * @param response
     * @return
     */
    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    /**
     * 接口调用失败
     * @param response
     * @return
     */
    public Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    /**
     * 处理响应
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId, long userId) {
        try {
            // 获取原始的响应对象
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 获取数据缓存工厂
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 获取响应的状态码
            HttpStatus statusCode = originalResponse.getStatusCode();
            // 判断状态码是否是200 OK
            if (statusCode == HttpStatus.OK) {
                // 创建响应对象装饰器（穿装备）
                ServerHttpResponseDecorator serverHttpResponseDecorator = new ServerHttpResponseDecorator(originalResponse) {
                    // 重写writeWith方法，用于处理响应体的数据
                    // 这段方法就是只要当我们的模拟接口调用完成之后，等它返回结果，
                    // 就会调用writeWith方法，我们就能根据响应结果做一些自己的处理
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}",(body instanceof Flux));
                        // 判断响应体是否是Flux类型
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 返回一个处理后的响应体
                            // 这里就理解为它在拼接字符串，它把缓冲区的数据取出来，一点一点拼接好
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7. 调用成功  接口调用次数+1
                                try {
                                    boolean b = innerUserInterfaceService.invokeCount(interfaceInfoId, userId);
                                } catch (Exception e) {
                                    log.error("invokeCount error", e);
                                }
                                // 将响应体中的内容转化为字节数组
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                // 释放掉内存
                                DataBufferUtils.release(dataBuffer);
                                //构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                ArrayList<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);// data
                                sb2.append(data);
                                log.info("响应结果" + data);
                                // 将处理后的内容重新包装成DataBuffer并返回
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            // 8. 调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 对于200 OK的请求，将装饰后的响应对象传递给下一个过滤器链，并继续处理（设置response对象为装饰过的）
                return chain.filter(exchange.mutate().response(serverHttpResponseDecorator).build());
            }
            // 对于非200 OK的请求，直接返回，进行降级处理
            return chain.filter(exchange);
        } catch (Exception e) {
            // 处理异常情况，记录错误日志
            log.error("网关处理响应异常\n" + e);
            return chain.filter(exchange);
        }
    }
}