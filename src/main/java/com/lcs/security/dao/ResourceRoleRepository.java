package com.lcs.security.dao;

import com.lcs.security.domain.entity.SysResourceRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 资源角色服务
 *
 * Created by liucs on 2018/11/29.
 */
public interface ResourceRoleRepository extends JpaRepository<SysResourceRole, Integer> {

}
