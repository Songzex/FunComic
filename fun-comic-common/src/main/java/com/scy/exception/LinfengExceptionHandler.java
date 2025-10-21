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
package com.scy.exception;

import com.scy.api.R; // 确保这个类存在
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class LinfengExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(LinfengException.class)
	public R handleException(LinfengException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(NoHandlerFoundException e) { // 修改参数类型
		logger.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("暂无权限请联系管理员");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error(); // 确保R类有这个方法
	}
}
