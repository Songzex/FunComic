/**
 * -----------------------------------
 *  Copyright (c) 2021-2023
 *  All rights reserved, Designed By www.linfeng.tech
 * 林风婚恋交友商业版本请务必保留此注释头信息
 * 商业版授权联系技术客服	 QQ:  973921677/3582996245
 * 严禁分享、盗用、转卖源码或非法牟利！
 * 版权所有 ，侵权必究！
 * -----------------------------------
 */
package com.scy.common.config;


import com.scy.common.interceptor.AuthorizationInterceptor;
import com.scy.common.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;


    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }

}