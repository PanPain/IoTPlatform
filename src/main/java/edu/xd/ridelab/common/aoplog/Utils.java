package edu.xd.ridelab.common.aoplog;

import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PanTeng
 * @version 1.0
 * @file Utils.java
 * @date 2018/8/14
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class Utils {

    /**
     * @description 获取类名
     * @author PanTeng
     * @date 21:26,2018/8/14
     * @param joinPoint
     * @return
     */
    public static String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    /**
     * @description 获取方法名
     * @author PanTeng
     * @date 21:26,2018/8/14
     * @param joinPoint
     * @return
     */
    public static String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    /**
     * @description 获取当前时间
     * @author PanTeng
     * @date 21:28,2018/8/14
     * @param
     * @return
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
