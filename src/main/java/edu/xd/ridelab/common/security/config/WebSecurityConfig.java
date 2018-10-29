package edu.xd.ridelab.common.security.config;

import edu.xd.ridelab.controller.security.handler.*;
import edu.xd.ridelab.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.HeaderWriterFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ChenXiang
 * @Date 2018/08/12,14:12
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    UserDetailsService userService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        HeaderWriter headerWriter = new HeaderWriter() {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                response.setHeader("X-Frame-Options", "ALLOWALL");
            }
        };
        List headerWriters = new ArrayList<>();
        headerWriters.add(headerWriter);
        HeaderWriterFilter headerWriterFilter = new HeaderWriterFilter(headerWriters);
        http.addFilter(headerWriterFilter);

        http
                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
                .authorizeRequests()
                .antMatchers("/login", "/assets/**", "/CSS/**", "/HTML/**", "/JavaScript/**").permitAll()


                .anyRequest().authenticated()//其他URL需要身份认证
                .and()

                .formLogin() //开启登录
                .successHandler(authenticationSuccessHandler)
                .loginPage("/login")
                .failureUrl("/login?error")
                .successForwardUrl("/index") //登录成功的URL
                .failureHandler(authenticationFailureHandler)

                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
    }


    /**
     * @param auth
     * @return void
     * @methodname configureGlobal
     * @description 登录认证过程
     * @author ChenXiang
     * @date 9:39,2018/8/15
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}