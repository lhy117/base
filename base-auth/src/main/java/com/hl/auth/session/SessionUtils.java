package com.hl.auth.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSON;
import com.hl.base.facade.constants.GlobalConstant;
import com.hl.base.facade.model.SysOrg;
import com.hl.base.facade.model.SysUser;

/**
 * session工具类
 * @author lhy
 * @date 2018年3月27日 下午7:47:39
 */
public class SessionUtils {
	
	/**
	 * 获取登陆的key,即用户名
	 * @return
	 */
	public static String getLoginName() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return (String) subject.getPrincipal();
		}
		return null;
	}
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public static SysUser getLoginUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Session session = subject.getSession();
			SysUser user = JSON.parseObject(JSON.toJSONString(session.getAttribute(GlobalConstant.USER)), SysUser.class);
			//SysUser user = (SysUser) session.getAttribute(GlobalConstant.USER);
			return user == null ? new SysUser() : user;
		}
		return null;
	}
	/**
	 * 获取当前登陆用户id
	 * @return
	 */
	public static String getLoginUserId() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return (String) subject.getSession().getAttribute(GlobalConstant.USER_ID);
		}
		return null;
	}
	/**
	 * 获取当前登陆部门
	 * @return
	 */
	public static SysOrg getLoginOrg() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Session session = subject.getSession();
			SysOrg org = (SysOrg) session.getAttribute(GlobalConstant.ORG);
			return org == null ? new SysOrg() : org;
		}
		return null;
	}
	/**
	 * 获取当前登陆机构id
	 * @return
	 */
	public static String getLoginOrgId() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return (String) subject.getSession().getAttribute(GlobalConstant.ORG_ID);
		}
		return null;
	}
	/**
	 * 获取当前登陆职工id
	 * @return
	 */
	public static String getLoginStaffId() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return (String) subject.getSession().getAttribute(GlobalConstant.STAFF_ID);
		}
		return null;
	}
	/**
	 * 获取当前用户是否登陆
	 * @return
	 */
	public static Boolean isLoggedIn() {
		boolean isLoggedIn = SecurityUtils.getSubject().isAuthenticated();
		return isLoggedIn;
	}
	public static void login(String userName, String password) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(userName, password);
		subject.login(token);
	}
	/**
	 * 用户退出登陆
	 */
	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}


	/**
	 * 获取sessionId
	 * @return sessionId
	 */
	public static String getSessionId(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Session session = subject.getSession();
			return session.getId().toString();
		}
		return null;
	}
}
