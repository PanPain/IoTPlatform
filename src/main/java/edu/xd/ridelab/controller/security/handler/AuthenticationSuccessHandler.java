package edu.xd.ridelab.controller.security.handler;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.controller.response.MetaData;
import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.controller.security.SecurityCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ChenXiang
 * @Date 2018/08/16,16:43
 */
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MetaData metaData = new MetaData(false,SecurityCode.LOGIN_SUCCESSFUL.getCode(),SecurityCode.LOGIN_SUCCESSFUL.getMessage());
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
