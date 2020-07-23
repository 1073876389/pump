package com.xuwx.pump.entity;

import lombok.*;

/**
 *@ClassName User
 *@Description TODO
 *@Author wx.Xu
 *Date 2020/7/8 14:24
 *Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseDao  {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 头像路径
     */
    private String headPortrait;
    /**
     * 用户类型
     * 权限（0：管理员；1：普通用户）
     */
    private int userType;
    /**
     * 系统内用户唯一代码
     */
    private String userCode;
    /**
     * 是否在使用状态
     */
    private Boolean isActive;
    /**
     * 电子邮件
     */
    private String email;
}
