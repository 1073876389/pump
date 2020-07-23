package com.xuwx.pump.util;


import com.xuwx.pump.entity.User;
import com.xuwx.pump.entity.VerifyCodeVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class UserContext {
    public static final String CURRENT_IN_SESSION = "logininfo";
    public static final String VERIFYCODE_IN_SESSION = "verifycode";


    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static void setCurrent(User current) {
        getRequest().getSession().setAttribute(CURRENT_IN_SESSION, current);
    }

    public static User getCurrent() {
        return (User) getRequest().getSession().getAttribute(CURRENT_IN_SESSION);
    }

    public static void setVerifyCode(VerifyCodeVo verifyCode) {
        getRequest().getSession().setAttribute(VERIFYCODE_IN_SESSION, verifyCode);
    }

    public static VerifyCodeVo getVerifyCode() {
       return (VerifyCodeVo) getRequest().getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }
}
