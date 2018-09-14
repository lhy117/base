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
import com.hl.base.facade.model.SysUserRole;
import com.hl.base.facade.service.ISysUserRoleService;

/**
 * <p>
 * 通用-用户角色关系表 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:11
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

	@Reference
	private ISysUserRoleService sysUserRoleService;
	
	@PostMapping(value = "/listPage")
	public Page<SysUserRole> listPage(@RequestBody SysUserRole sysUserRole) {
		return sysUserRoleService.selectPage(new Page<SysUserRole>(1,10), new EntityWrapper<SysUserRole>(sysUserRole));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysUserRole queryById(@PathVariable(value="id") String id) {
		return sysUserRoleService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysUserRole sysUserRole) {
		return sysUserRoleService.insertOrUpdate(sysUserRole);
	}
}
