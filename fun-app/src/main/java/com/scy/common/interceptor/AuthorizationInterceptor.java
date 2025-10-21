
package com.scy.common.interceptor;


import com.scy.common.annotation.Login;
import com.scy.exception.LinfengException;
import com.scy.user.service.UserService;
import com.scy.utils.Constant;
import com.scy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public static final String USER_KEY = "userId";

    public AuthorizationInterceptor(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }
        //获取用户凭证
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(Constant.TOKEN_HEADER);
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new LinfengException(Constant.TOKEN_HEADER + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        Claims claims = jwtUtil.getClaimByToken(token);
        if(claims == null || jwtUtil.isTokenExpired(claims.getExpiration())){
            throw new LinfengException(Constant.TOKEN_HEADER + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }

        //设置userId到request里，后续根据userId，获取用户信息

        request.setAttribute(USER_KEY, claims.getSubject());
//        User user = userService.getUserByUid(Integer.parseInt(claims.getSubject()));
//        if(user == null){
//            throw new LinfengException(Constant.TOKEN_HEADER + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
//        }
//
//        if(user.getVipExpireTime() != null
//                && user.getVip() == Constant.VIP_USER
//                && DateUtil.secondsBetween(user.getVipExpireTime(), DateUtil.nowDateTime()) > 0){
//            user.setVip(Constant.COMMON_USER);
//            user.setUpdateTime(DateUtil.nowDateTime());
//            userService.updateAndDeleteCache(user);
//        }

        return true;
    }


}
