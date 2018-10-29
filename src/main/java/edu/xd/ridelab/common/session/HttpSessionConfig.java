package edu.xd.ridelab.common.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author PanTeng
 * @version 1.0
 * @file HttpSessionConfig.java
 * @descriptiopn 使用Redis作为Session数据的分布式缓存，在访问Session时，
 *               直接在Controller中将HttpSession作为参数注入，Spring会自动获取Session数据
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class HttpSessionConfig {

    /**
     * `maxInactiveIntervalInSeconds`:设置Session失效时间
     * 使用Redis Session之后，原Boot的server.session.timeout属性不再生效
     */
}
