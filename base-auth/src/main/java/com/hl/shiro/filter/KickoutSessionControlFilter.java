/*
 * Copyright (c) 2018, Healthlink All Rights Reserved.
 *
 */

package com.hl.shiro.filter;

import java.io.Serializable;
import java.io.Writer;
import java.util.Deque;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hl.base.facade.model.SysUser;
import com.hl.base.util.model.Result;
import com.hl.shiro.session.SessionUtils;

import lombok.Setter;

/**
 * 踢出用户 <br/>
 * @author liheyu
 * @date 2018/9/23 17:40
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

	@Setter
    private CacheManager cacheManager;
	@Setter
    private SessionManager sessionManager;
	/**
	 * 最大登陆人数
	 */
	@Setter
    private int maxSession;
	/**
	 * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
	 */
	@Setter
	private boolean kickoutAfter = false; 

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	HttpServletResponse httpResponse = (HttpServletResponse) response;
        Subject subject = getSubject(request, response);
        if(subject.getPrincipal() == null) {
        	return true;
        }
        Session session = subject.getSession();
        SysUser user =  SessionUtils.getLoginUser();
        Serializable sessionId = session.getId();

        Cache<String , Deque<Serializable>> kickCache = cacheManager.getCache("kickCache");
        Deque<Serializable> deque = kickCache.get(user.getLoginName());  
        if(deque == null) {  
            deque = Lists.newLinkedList(); 
            deque.push(sessionId);
            kickCache.put(user.getLoginName(), deque);
        }  
      //如果队列里没有此sessionId，且用户没有被踢出；放入队列  
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {  
            deque.push(sessionId);  
        }
        //如果队列里的sessionId数超出最大会话数，开始踢人  
        while(deque.size() > maxSession) {  
            Serializable kickoutSessionId = null;  
            //如果踢出后者  
            if(kickoutAfter) { 
                kickoutSessionId = deque.removeFirst();  
            } else {
                kickoutSessionId = deque.removeLast();  
            }  
            try {  
                Session kickoutSession =  
                    sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));  
                if(kickoutSession != null) {  
                    //设置会话的kickout属性表示踢出了  
                    kickoutSession.setAttribute("kickout", true);  
                }  
            } catch (Exception e) {//ignore exception  
            }  
        }  
        Result<String> result = new Result<>();
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            subject.logout();
            result.setCode(HttpStatus.SC_NOT_ACCEPTABLE);
			result.setMessage("账号在其他地方登陆");
			httpResponse.setStatus(HttpStatus.SC_OK);
			httpResponse.setCharacterEncoding(Consts.UTF_8.toString());
			response.setContentType(ContentType.APPLICATION_JSON.toString());
			Writer w = response.getWriter();
			w.write(JSON.toJSONString(result));
			w.flush();
			w.close();
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	SysUser user = new SysUser();
		user.setId("111");
		Object d = user;
		SysUser s = (SysUser) d;
		System.out.println(s.toString());
	}
}
