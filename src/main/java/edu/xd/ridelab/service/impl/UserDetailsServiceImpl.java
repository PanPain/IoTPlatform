package edu.xd.ridelab.service.impl;

import edu.xd.ridelab.mapper.RoleMapper;
import edu.xd.ridelab.mapper.UserMapper;
import edu.xd.ridelab.mapper.UserRoleRelationMapper;
import edu.xd.ridelab.model.UserModel;
import edu.xd.ridelab.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author ChenXiang
 * @Date 2018/08/12,14:51
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserModel user = userMapper.selectByUserName(userName);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<RoleVO> roleVOS = roleMapper.selectById(userRoleRelationMapper.selectRoleIdByUserId(user.getUserId()));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleVO role : roleVOS) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        System.out.println("role:"+roleVOS.get(0).getName());
        System.out.println("userName:" + userName);
        System.out.println("userId:" + user.getUserId() != null);
        System.out.println("username:" + user.getUserName() + ";    password:" + new BCryptPasswordEncoder().encode(user.getUserPassword()));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getUserPassword(), grantedAuthorities
        );
    }
}


