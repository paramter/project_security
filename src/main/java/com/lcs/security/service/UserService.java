package com.lcs.security.service;

import com.lcs.security.dao.UserRepository;
import com.lcs.security.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * Created by liucs on 2018/11/28.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过用户名查询用户
     *
     * @param userName
     * @return
     */
    public SysUser findByName(String userName){
        return userRepository.findByName(userName);
    }

    /**
     * 更新实体
     *
     * @param sysUser
     */
    public void save(SysUser sysUser){
        userRepository.save(sysUser);
    }
}
