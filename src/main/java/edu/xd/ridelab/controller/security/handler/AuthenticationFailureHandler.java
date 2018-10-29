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

/**
 * @Author ChenXiang
 * @Date 2018/08/16,16:46
 */
@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        MetaData metaData = new MetaData(false,SecurityCode.LOGIN_FAILED.getCode(),SecurityCode.LOGIN_FAILED.getMessage());
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
