package com.lcs.security.service;

import com.lcs.security.dao.RoleRepository;
import com.lcs.security.domain.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务
 *
 * Created by liucs on 2018/11/29.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 查询所有橘色
     *
     * @return
     */
    public List<SysRole> findAll(){
        return roleRepository.findAll();
    }

    /**
     * 根据资源ID获取角色集合
     *
     * @param resourceId
     * @return
     */
    public List<SysRole> findSysRolesByResourceUrl(String resourceId){
        return roleRepository.findSysRolesByResourceUrl(resourceId);
    }
}
