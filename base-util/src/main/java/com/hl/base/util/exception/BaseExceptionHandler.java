package com.hl.base.util.exception;

import org.apache.http.HttpStatus;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hl.base.util.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常捕获
 * @author liheyu
 * @date 2018-09-26
 */
@RestControllerAdvice
@Order(2)
@Slf4j
public class BaseExceptionHandler {

	    /**
	     * UnauthorizedException 处理所有 权限验证问题 
	     * @param ex UnauthorizedException
	     * @return CommonResult
	     */
	    @ExceptionHandler(UnauthorizedException.class)
	    public Result<String> handleBusinessException(UnauthorizedException ex) {
	    	log.warn("Unauthorized exception handler" + ex.getMessage(), ex);
	    	Result<String> result = new Result<>();
	    	result.setCode(HttpStatus.SC_UNAUTHORIZED);
	    	result.setMessage("用户没有该操做的权限!");
	    	return result;
	    }
	    
	    /**
	     * Exception handler 拦截不可预测的异常，提示给接口调用方，并记录日志
	     * @param ex Exception
	     */
	    @ExceptionHandler(Exception.class)
	    public Result<String> handleException(Exception ex) {
	        log.error("Exception " + ex.getMessage(), ex);
	        return new Result<String>(false, "系统发生未知异常！请联系管理员");
	    }
}
