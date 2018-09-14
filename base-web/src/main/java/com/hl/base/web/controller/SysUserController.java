package com.hl.base.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hl.base.facade.model.SysUser;
import com.hl.base.facade.service.ISysUserService;

/**
 * <p>
 * 云调度-用户 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-07-26 14:22:23
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

	@Reference
	private ISysUserService userService;
	
	@PostMapping(value = "/listPage")
	public Page<SysUser> listPage(@RequestBody SysUser user) {
		return userService.selectPage(new Page<SysUser>(1,10), new EntityWrapper<SysUser>(user));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysUser queryById(@PathVariable(value="id") String id) {
		return userService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysUser user) {
		return userService.insertOrUpdate(user);
	}
	
}

