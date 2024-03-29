package com.lin.project.model.dto.interfaceInfo;

import com.lin.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     * 用户可能根据id查询
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 请求地址
     */
    private String url;
//下面都有可能
    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口状态(0-关闭, 1-开启)
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long userId;

//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;
//
//    /**
//     * 是否删除(0-未删, 1-已删)
//     * 逻辑删除@TableLogic
//     */
//    @TableLogic
//    private Integer isDeleted;
}