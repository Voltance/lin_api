package com.lin.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.linapicommon.model.entity.UserInterfaceInfo;


/**
* @author Lin
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-09-02 11:44:17
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     *
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
