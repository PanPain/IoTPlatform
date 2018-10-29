package edu.xd.ridelab.common.aoplog;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author PanTeng
 * @version 1.0
 * @describtion Controller层日志打印
 * @file WebLogAspect.java
 * @date 2018/8/14
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * edu.xd.ridelab.controller.*.*.*(..))")
    public void controllerLog(){
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        String className = Utils.getClassName(joinPoint);
        String methodName = Utils.getMethodName(joinPoint);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer log = new StringBuffer();
        log.append("请求信息:\n");
        log.append("TIME:"+ Utils.getStringDate()+"\n");
        log.append("URL:" + request.getRequestURL().toString()+"\n");
        log.append("HTTP_METHOD:" + request.getMethod()+"\n");
        log.append("IP:" + request.getRemoteAddr()+"\n");
        log.append("CLASS_METHOD:" + className + "." + methodName+"\n");
        log.append("ARGS:" + Arrays.toString(joinPoint.getArgs())+"\n");
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            log.append("\t" + paraName + ":" + request.getParameter(paraName)+"\n");
        }
        logger.info(log.toString());
    }

    @After("controllerLog()")
    public void doAfter(JoinPoint joinPoint){
        StringBuffer log = new StringBuffer();
        log.append("请求耗时：");
        log.append((System.currentTimeMillis() - startTime.get()) + "ms" + "\n");
        logger.info(log.toString());
    }

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            result = joinPoint.proceed();
            StringBuffer log = new StringBuffer();
            log.append("请求结果：");
            log.append(JSON.toJSONString(result) + "\n");
            logger.info(log.toString());
        } catch (Throwable throwable) {
            StringBuffer error = new StringBuffer();
            error.append("Controller层异常:"+throwable.getMessage());
            logger.error(throwable.toString(), throwable);
        }
        return result;
    }

}
