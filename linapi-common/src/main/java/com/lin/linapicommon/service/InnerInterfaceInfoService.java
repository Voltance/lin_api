package com.lin.linapicommon.service;

import com.lin.linapicommon.model.entity.InterfaceInfo;


/**
* @author Lin
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-08-24 21:29:43
*/
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询模拟接口是否存在
     * @param url
     * @param method
     * @return InterfaceInfo
     */
    InterfaceInfo getInterfaceInfo(String url, String method);

}
