package com.lin.linapicommon.service;


/**
* @author Lin
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-09-02 11:44:17
*/
public interface InnerUserInterfaceInfoService {

    /**
     *
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
