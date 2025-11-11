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

package com.scy.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
