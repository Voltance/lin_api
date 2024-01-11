package com.lin.linapiclientsdk;

import com.lin.linapiclientsdk.client.LinApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 注解生成get set方法
@Data
// 标记为配置类  告诉spring这是一个配置类
@Configuration
// 能够读取application.yml里的配置，读取到配置后，把这个读到的配置设置到我们这里的属性中，
// 这里给所有的配置加上前缀为linapi.client
@ConfigurationProperties("linapi.client")
//用于自动扫描组件，使得Spring能够自动组成相应的Bean
@ComponentScan
public class LinApiClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public LinApiClient linApiClient() {
        return new LinApiClient(accessKey, secretKey);
    }
}
