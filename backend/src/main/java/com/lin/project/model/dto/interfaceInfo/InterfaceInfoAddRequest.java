package com.lin.project.model.dto.interfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 为什么需要三个接口：
 * 为了方便前端人员  一个服务对应于一个接口文件
 *
 */

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    /**
     * 主键
     * id后台会自动生成
     */
//    @TableId(type = IdType.AUTO)
//    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParams;

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
     * 有默认值0 不需要创建
     */
//    private Integer status;

    /**
     * 创建人
     * 创建人就是登录的用户自动同步到后台，是后台自动生成的
     */
//    private Long userId;

//    /**
//     * 创建时间   都是自动生成的
//     */
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;

//    /**
//     * 是否删除(0-未删, 1-已删)
//     * 逻辑删除@TableLogic
//     */
//    @TableLogic
//    private Integer isDeleted;
}