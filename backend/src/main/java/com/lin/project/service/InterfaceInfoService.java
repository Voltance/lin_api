package com.lin.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.linapicommon.model.entity.InterfaceInfo;

/**
* @author Lin
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-08-24 21:29:43
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
