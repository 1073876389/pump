package com.xuwx.pump.controller;

import com.xuwx.pump.api.CommonResult;
import com.xuwx.pump.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@ClassName UmsMemberController
 *@Description  测试 手机号码 与验证码绑定
 *@Author wx.Xu
 *Date 2020/7/29 14:30
 *Version 1.0
 */
@Controller
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone){
        return  umsMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAutnCode",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAutnCode(@RequestParam String telephone,@RequestParam String authCode){
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }
}
