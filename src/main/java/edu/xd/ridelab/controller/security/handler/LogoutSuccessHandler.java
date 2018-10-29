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
 * @Date 2018/08/16,16:50
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MetaData metaData = new MetaData(false,SecurityCode.LOGOUT_SUCCESSFUL.getCode(),SecurityCode.LOGOUT_SUCCESSFUL.getMessage());
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
