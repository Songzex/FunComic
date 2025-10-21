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
package com.scy.common.aspect;



import com.scy.exception.LinfengException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Redis切面处理类
 *
 */
@Aspect
@Configuration
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //是否开启redis缓存  true开启   false关闭
    @Value("${spring.redis.open: false}")
    private boolean open;

    @Around("execution(* com.scy.utils.RedisUtil.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new LinfengException("Redis服务异常");
            }
        }
        return result;
    }
}
