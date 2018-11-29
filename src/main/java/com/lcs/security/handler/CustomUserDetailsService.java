package com.lcs.security.handler;

import com.lcs.security.domain.entity.SysUser;
import com.lcs.security.domain.model.SecurityUser;
import com.lcs.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 登录服务
 *
 * Created by liucs on 2018/11/28.
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("# 开始查询用户信息,userName:{}",userName);
        SysUser user = userService.findByName(userName);
        log.info("# 用户是否存在,{}", user == null ? false : true);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        // SecurityUser实现UserDetails并将SysUser的name映射为username
        SecurityUser seu = new SecurityUser(user);
        return seu;
    }
}
