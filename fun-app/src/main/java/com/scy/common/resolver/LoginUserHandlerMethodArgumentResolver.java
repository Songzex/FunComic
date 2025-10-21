/**
 * -----------------------------------
 *  Copyright (c) 2021-2023
 *  All rights reserved, Designed By www.linfeng.tech
 *  林风婚恋交友商业版本请务必保留此注释头信息
 *  商业版授权联系技术客服	 QQ:  973921677/3582996245
 *  严禁分享、盗用、转卖源码或非法牟利！
 *  版权所有 ，侵权必究！
 * -----------------------------------
 */
package com.scy.common.resolver;

import com.scy.common.annotation.LoginUser;
import com.scy.common.interceptor.AuthorizationInterceptor;
import com.scy.user.pojo.User;
import com.scy.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public LoginUserHandlerMethodArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }
        return userService.getByuid(((String) object));
    }
}
