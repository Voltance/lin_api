package com.lin.linapicommon.service;


import com.lin.linapicommon.model.entity.User;



/**
 * 用户服务
 *
 * @author lin
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户密钥（accessKey）
     * @param accessKey
     * @return user
     */
    User getInvokeUser(String accessKey);
}
