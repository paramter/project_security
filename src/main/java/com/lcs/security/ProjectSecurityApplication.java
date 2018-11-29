package com.lcs.security;

import com.lcs.security.config.Appctx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lcs.security")
public class ProjectSecurityApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ProjectSecurityApplication.class, args);
        SpringApplication app=new SpringApplication(ProjectSecurityApplication.class);
        Appctx.ctx=app.run(args);
//		UserService suserService = (UserService) Appctx.ctx.getBean("userService");
//		SysUser su= suserService.findByName("admin");
//        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密 可以先设置初始密码：000000
//        su.setPassword(bc.encode(su.getPassword()));//然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
//        System.out.println("密码"+su.getPassword());
//        suserService.save(su);//运行一次后记得注释这段重复加密会无法匹配
    }
}
