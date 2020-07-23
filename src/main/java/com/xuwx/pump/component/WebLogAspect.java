package com.xuwx.pump.component;


import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.xuwx.pump.dto.WebLog;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@ClassName WebLogAspect
 *@Description  统一日志处理切面  定义了一个日志切面类，在环绕通知中获取日志需要的信息，并应用到controller层中所有的public方法中去。
 *@Author wx.Xu
 *Date 2020/7/17 16:57
 *Version 1.0
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义切入点
     * controller层中所有的public方法
     */
    @Pointcut("execution(public * com.xuwx.pump.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint)throws Throwable{

    }

    @AfterReturning(value = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret)throws Throwable{

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        //当前时间
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        WebLog webLog = new WebLog();

        Object result = joinPoint.proceed();

        //通过反射拿到 ApiOperation 注解的方法 然后获取注解值
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();

        String urlStr = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr,URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method,joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int)(endTime-startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}",JSONUtil.parse(webLog));
        return result;
    }

    /**
     * 根据方法和传入参数获取请求参数
     * @param method 方法对象
     * @param args 传入参数
     * @return 请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody!=null){
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam!=null){
                Map<Object, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())){
                    key = requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0){
            return null;
        }else if(argList.size() == 1){
            return argList.get(0);
        }else {
            return argList;
        }
    }

}
