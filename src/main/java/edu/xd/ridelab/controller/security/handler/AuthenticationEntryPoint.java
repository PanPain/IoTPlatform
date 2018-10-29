package edu.xd.ridelab.controller.security.handler;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.controller.response.MetaData;
import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.controller.security.SecurityCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

/**
 * 未登录
 *
 * @Author ChenXiang
 * @Date 2018/08/16,16:30
 */
@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        MetaData metaData = new MetaData(false,SecurityCode.NEED_LOGIN.getCode(),SecurityCode.NEED_LOGIN.getMessage());
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
