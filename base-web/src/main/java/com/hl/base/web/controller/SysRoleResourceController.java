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
import com.hl.base.facade.model.SysRoleResource;
import com.hl.base.facade.service.ISysRoleResourceService;

/**
 * <p>
 * 通用-角色资源关系 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceController {

	@Reference
	private ISysRoleResourceService sysRoleResourceService;
	
	@PostMapping(value = "/listPage")
	public Page<SysRoleResource> listPage(@RequestBody SysRoleResource sysRoleResource) {
		return sysRoleResourceService.selectPage(new Page<SysRoleResource>(1,10), new EntityWrapper<SysRoleResource>(sysRoleResource));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysRoleResource queryById(@PathVariable(value="id") String id) {
		return sysRoleResourceService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysRoleResource sysRoleResource) {
		return sysRoleResourceService.insertOrUpdate(sysRoleResource);
	}
}
