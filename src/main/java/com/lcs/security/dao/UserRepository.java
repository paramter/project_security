package com.lcs.security.dao;

import com.lcs.security.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liucs on 2018/11/28.
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, Integer> {
    /**
     * 通过用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser findByName(String userName);

}
