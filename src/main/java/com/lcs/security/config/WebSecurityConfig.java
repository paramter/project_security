package com.lcs.security.config;

import com.lcs.security.handler.CustomUserDetailsService;
import com.lcs.security.handler.LoginFailureHandler;
import com.lcs.security.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * security配置
 * <p>
 * Created by liucs on 2018/11/28.
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilter(filterSecurityInterceptor())
            .authorizeRequests()
            .antMatchers("/home").permitAll()//访问：/home 无需登录认证权限
            .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
//            .antMatchers("/hello").hasAuthority("admin") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
            .and()
            .formLogin()
            .loginPage("/login")//指定登录页是”/login”
            .permitAll()
            //登录失败处理
            .failureHandler(loginFailureHandler())
            .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
            .and()
            .logout()
            .logoutSuccessUrl("/home") //退出登录后的默认网址是”/home”
            .permitAll()
            .invalidateHttpSession(true)
            .and()
            .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
            .tokenValiditySeconds(1209600);
        /**
         *  处理AccessDeniedException 且用户不是匿名用户
         *  例如：USER角色访问ADMIN角色的资源，提示权限不足
         */
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl()).accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定加密方式
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private CustomInvocationSecurityService securityMetadataSource;

    @Autowired
    private CustomAccessDecisionManager accessDecisionManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        return filterSecurityInterceptor;
    }
}
