package com.lcs.security.config;

import org.springframework.context.ApplicationContext;

/**
 * Created by liucs on 2018/11/28.
 */
public class Appctx {
    public static ApplicationContext ctx=null;

    public static Object getObject(String string){
        return ctx.getBean(string);
    }
}
