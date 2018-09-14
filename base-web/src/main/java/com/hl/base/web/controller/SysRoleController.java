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
import com.hl.base.facade.model.SysRole;
import com.hl.base.facade.service.ISysRoleService;

/**
 * <p>
 * 通用-角色表 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

	@Reference
	private ISysRoleService sysRoleService;
	
	@PostMapping(value = "/listPage")
	public Page<SysRole> listPage(@RequestBody SysRole sysRole) {
		return sysRoleService.selectPage(new Page<SysRole>(1,10), new EntityWrapper<SysRole>(sysRole));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysRole queryById(@PathVariable(value="id") String id) {
		return sysRoleService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysRole sysRole) {
		return sysRoleService.insertOrUpdate(sysRole);
	}
}
