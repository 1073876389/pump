package com.xuwx.pump.controller;

import com.xuwx.pump.api.CommonResult;
import com.xuwx.pump.entity.User;
import com.xuwx.pump.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *@ClassName LoginController
 *@Description TODO
 *@Author wx.Xu
 *Date 2020/7/6 16:56
 *Version 1.0
 */
@Api(tags = "LoginController",description = "登录管理")
@RestController
public class LoginController{
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User u) {
        User user = userService.userLogin(u.getUsername(),u.getPassword());
        if (null != user){
            return CommonResult.success(user.getUsername());
        }else{
            return CommonResult.validateFailed();
        }
    }
}
