package com.lcs.security.service;

import com.lcs.security.dao.ResourceRepository;
import com.lcs.security.dao.ResourceRoleRepository;
import com.lcs.security.domain.entity.SysResource;
import com.lcs.security.domain.entity.SysResourceRole;
import com.lcs.security.domain.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资源服务
 *
 * Created by liucs on 2018/11/29.
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 查询所有资源
     *
     * @return
     */
    public List<SysResource> findAll(){
        return resourceRepository.findAll();
    }
}
