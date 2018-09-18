package com.hl.shiro.filter;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.hl.base.util.model.Result;

/**
 * url请求过滤
 * @author lhy
 * @date 2018年5月3日 下午2:55:05
 */
public class AuthorizeFilter extends AuthorizationFilter {
	
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		Subject subject = getSubject(request, response);
		Result<String> result = new Result<>();
		//String resource = getPathWithinApplication(request);
		if (!subject.isAuthenticated() || subject.getPrincipal() == null) {
			result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
			result.setMessage("登陆超时,请重新登录");
			response.setStatus(HttpStatus.OK.value());
			response.setCharacterEncoding(Consts.UTF_8.toString());
			response.setContentType(ContentType.APPLICATION_JSON.toString());
			Writer w = response.getWriter();
			w.write(JSON.toJSONString(result));
			w.flush();
			w.close();
		} 
		return false;
	}
	/**
	 * 判断用户是否可以访问请求的资源
	 * @param request 用户请求
	 * @param response 响应
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// 登陆请求直接放行
		if (isLoginRequest(request, response)) {
			return true;
		}
		// 获取用户认证信息
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated()) {
			return false;
		}
		return true;
	}
}
