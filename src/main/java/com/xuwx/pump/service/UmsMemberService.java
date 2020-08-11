package com.xuwx.pump.service;

import com.xuwx.pump.api.CommonResult;

/**
 * 验证管理
 * Created by xuwx on 2020/7/28.
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     * @param telephone
     */
    CommonResult generateAuthCode(String telephone);

    /**
     *  判断验证码与手机号是否匹配(校验验证码)
     * @param telephone
     * @param authCode
     */
    CommonResult verifyAuthCode(String telephone,String authCode);
}
