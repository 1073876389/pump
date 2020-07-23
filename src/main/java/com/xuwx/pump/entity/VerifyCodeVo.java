package com.xuwx.pump.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuwx on 2018/8/31.
 */
@Getter
@Setter
public class VerifyCodeVo implements Serializable {
    private String phoneNumber;
    private String verifyCode;
    private Date sendCodeTime;
}