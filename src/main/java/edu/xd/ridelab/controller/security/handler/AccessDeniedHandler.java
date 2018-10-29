package edu.xd.ridelab.controller.security.handler;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.controller.response.MetaData;
import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.controller.security.SecurityCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ChenXiang
 * @Date 2018/08/16,16:44
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        MetaData metaData = new MetaData(false,SecurityCode.NO_AUTHORITIES.getCode(),SecurityCode.NO_AUTHORITIES.getMessage());
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
