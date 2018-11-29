package com.lcs.security.dao;

import com.lcs.security.domain.entity.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 资源服务
 *
 * Created by liucs on 2018/11/29.
 */
public interface ResourceRepository extends JpaRepository<SysResource, Integer> {

}
