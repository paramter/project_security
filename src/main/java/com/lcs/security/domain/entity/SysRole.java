package com.lcs.security.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色表
 *
 * Created by liucs on 2018/11/28.
 */
@Entity
@Table(name = "s_role")
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",length=10)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private SysUser SUser;//角色对应的用户实体

    @Column(name="name",length=100)
    private String name;//角色名称

    @Column(name = "role_id", length = 100)
    private String roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SysUser getSUser() {
        return SUser;
    }

    public void setSUser(SysUser SUser) {
        this.SUser = SUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
