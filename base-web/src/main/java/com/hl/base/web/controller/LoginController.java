package com.hl.base.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hl.base.util.model.Result;
import com.hl.shiro.session.SessionUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

	@PostMapping("/login")
	public Result<String> login(){
		try {
			SessionUtils.login("lhy", "123456");
			return new Result<>(true, "success!");
		} catch (Exception e) {
			log.error("登陸失敗！", e);
		}
		return new Result<>(false, "failed!");
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
