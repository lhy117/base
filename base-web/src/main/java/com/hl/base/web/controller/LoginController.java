package com.hl.base.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hl.auth.session.SessionUtils;
import com.hl.base.facade.constants.GlobalConstant;
import com.hl.base.facade.model.SysOrg;
import com.hl.base.facade.service.ISysOrgService;
import com.hl.base.util.model.Result;

import lombok.extern.slf4j.Slf4j;
/**
 * 登录控制类
 * @author liheyu
 * @date 2018-09-26
 */
@RestController
@Slf4j
public class LoginController {

	@Reference ISysOrgService orgService;
	
	@PostMapping("/login")
	public Result<String> login(String userName, String password){
		Result<String> result = new Result<>();
		try {
			SessionUtils.login("lhy", "123456");
			SysOrg org = new SysOrg();
			org.setId("26aee413-5ca7-11e8-9914-000c29224763");
			org = orgService.selectOne(new EntityWrapper<>(org));
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute(GlobalConstant.ORG, org);
			session.setAttribute(GlobalConstant.ORG_ID, org.getId());
			result.setSuccess(true);
		} catch (UnknownAccountException ue) {
			log.error("用户不存在：{}", userName, ue);
			result.setMessage(String.format("用户不存在：s%", userName));
		} catch (LockedAccountException le) {
			log.error("用户名重复", le);
			result.setMessage("用户名重复");
		} catch (IncorrectCredentialsException ie) {
			log.error("密码错误", ie);
			result.setMessage("密码错误");
		}
		return result;
	}
	
	@GetMapping("/logout")
	public Result<String> logout(){
		try {
			SessionUtils.logout();
			return new Result<>(true, "success!");
		} catch (Exception e) {
			log.error("登出失敗！", e);
		}
		return new Result<>(false, "failed!");
	}
}
