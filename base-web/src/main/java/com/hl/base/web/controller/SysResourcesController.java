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
import com.hl.base.facade.model.SysResources;
import com.hl.base.facade.service.ISysResourcesService;

/**
 * <p>
 * 通用-功能资源表 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysResources")
public class SysResourcesController {

	@Reference
	private ISysResourcesService sysResourcesService;
	
	@PostMapping(value = "/listPage")
	public Page<SysResources> listPage(@RequestBody SysResources sysResources) {
		return sysResourcesService.selectPage(new Page<SysResources>(1,10), new EntityWrapper<SysResources>(sysResources));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysResources queryById(@PathVariable(value="id") String id) {
		return sysResourcesService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysResources sysResources) {
		return sysResourcesService.insertOrUpdate(sysResources);
	}
}
