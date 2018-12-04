package edu.xd.ridelab.controller.security;

import edu.xd.ridelab.mapper.UserMapper;
import edu.xd.ridelab.model.UserModel;
import edu.xd.ridelab.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @Author ChenXiang
 * @Date 2018/08/13,15:58
 */
@Controller
public class SecurityController {

    @Autowired
    UserMapper userMapper;

//    @ResponseBody
//    @RequestMapping(value = "/signin", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyRole('admin','user')")
//    public ResponseResult signin(@RequestParam(required = true) String userName) {
//        UserDetails userDetails = new UserDetailsServiceImpl().loadUserByUsername(userName);
////        httpSession.setAttribute("", userDetails.getUsername());
//
//        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//            return new ResponseResult(userDetails,
//                    new MetaData(true, SecurityCode.LOGIN_SUCCESSFUL.getCode(), SecurityCode.LOGIN_SUCCESSFUL.getMessage()));
//        }
//        return new ResponseResult(null, new MetaData(false, SecurityCode.LOGIN_FAILED.getCode(), SecurityCode.LOGIN_FAILED.getMessage()));
//    }

    @RequestMapping(value = "/getself", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSelf(Principal principal) {
        if (principal == null) { // 如未登录，响应401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 如已登录，返回登录用户信息
        return ResponseEntity.ok(userMapper.selectByUserName(principal.getName()));
    }
    @GetMapping("/login")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String index(){return "login";}

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping("/index")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String index(HttpSession httpSession) {

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("session_id=" + httpSession.getId());
        httpSession.setAttribute("userName",user.getUsername());
        UserModel userModel = userMapper.selectByUserName(user.getUsername());
        httpSession.setAttribute("userId",userModel.getUserId());
        System.out.println("userId="+userModel.getUserId());
        return "firstPage";  //实际返回的页面

    }
}
