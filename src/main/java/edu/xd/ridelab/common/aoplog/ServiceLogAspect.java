package edu.xd.ridelab.common.aoplog;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author PanTeng
 * @version 1.0
 * @describtion Service层日志打印
 * @file ServiceLogAspect.java
 * @date 2018/8/14
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ServiceLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * edu.xd.ridelab.service.*.*.*(..))")
    public void serviceLog(){
    }

    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        String className = Utils.getClassName(joinPoint);
        String methodName = Utils.getMethodName(joinPoint);
        StringBuffer log = new StringBuffer();
        log.append("调用信息:\n");
        log.append("TIME:" + Utils.getStringDate() + "\n");
        log.append("CLASS_METHOD:" + className + "." + methodName + "\n");
        log.append("ARGS:" + Arrays.toString(joinPoint.getArgs()) + "\n");
        logger.info(log.toString());
    }

    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;

        proceed = joinPoint.proceed();
        StringBuffer log = new StringBuffer();
        log.append("返回结果:");
        log.append(JSON.toJSONString(proceed) + "\n");
        logger.info(log.toString());
        return proceed;
    }

    @AfterReturning("serviceLog()")
    public void doAfter(JoinPoint joinPoint) {
        StringBuffer log = new StringBuffer();
        log.append("调用耗时:");
        log.append((System.currentTimeMillis() - startTime.get()) + "ms" + "\n");
        logger.info(log.toString());
    }

    @AfterThrowing(pointcut = "serviceLog()", throwing = "e")
    public void throwing(Throwable e) throws Throwable {
        StringBuffer error = new StringBuffer();
        error.append("Service层异常:"+e.getMessage());
        logger.error(error.toString(),e);
    }
}
