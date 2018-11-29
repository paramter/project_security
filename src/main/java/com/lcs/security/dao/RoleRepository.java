package com.lcs.security.dao;

import com.lcs.security.domain.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 角色服务
 *
 * Created by liucs on 2018/11/29.
 */
public interface RoleRepository extends JpaRepository<SysRole, Integer> {

    /**
     * 通过资源ID查询角色集合
     *
     * @param resourceId
     * @return
     */
    @Query(nativeQuery = true,value = "select t2.* from s_resource_role t1,s_role t2 where t1.role_id = t2.role_id and t1.resource_id = :resourceId")
    List<SysRole> findSysRolesByResourceUrl(@Param("resourceId") String resourceId);
}
