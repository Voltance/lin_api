package com.lin.project.service.impl.inner;

import com.lin.linapicommon.service.InnerUserInterfaceInfoService;
import com.lin.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 调用注入的UserInterfaceInfoService的invokeCount方法
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
