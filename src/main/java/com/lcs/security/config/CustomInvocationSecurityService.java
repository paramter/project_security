package com.lcs.security.config;

import com.lcs.security.domain.entity.SysResource;
import com.lcs.security.domain.entity.SysRole;
import com.lcs.security.service.ResourceService;
import com.lcs.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 获取用户权限
 *
 * Created by liucs on 2018/11/29.
 */
@Slf4j
@Service
public class CustomInvocationSecurityService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

    /**
     * 初始化权限资源
     */
    @PostConstruct
    private void loadResourceDefine(){
        log.info("# 开始初始化角色权限集合");
        List<SysResource> resourceList = resourceService.findAll();
        for(SysResource resource : resourceList) {
            List<SysRole> roles = roleService.findSysRolesByResourceUrl(resource.getResourceId());
            String url = resource.getResourceString();
            Collection<ConfigAttribute> atts = new ArrayList<>();
            for(SysRole role:roles) {
                ConfigAttribute ca = new SecurityConfig(role.getName());
                atts.add(ca);
            }
            resourceMap.put(url, atts);
        }
        log.info("角色集合列表,{}",resourceMap.toString());
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        log.info("# 获取相关配置，param:{}",o);
        FilterInvocation filterInvocation = (FilterInvocation)o;
        if(resourceMap == null){
            this.loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while(ite.hasNext()){
            String resourceUrl = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resourceUrl);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resourceUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
