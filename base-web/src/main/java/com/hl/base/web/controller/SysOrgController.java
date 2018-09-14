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
import com.hl.base.facade.model.SysOrg;
import com.hl.base.facade.service.ISysOrgService;

/**
 * <p>
 * 机构管理（中心、分站） 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysOrg")
public class SysOrgController {

	@Reference
	private ISysOrgService sysOrgService;
	
	@PostMapping(value = "/listPage")
	public Page<SysOrg> listPage(@RequestBody SysOrg sysOrg) {
		return sysOrgService.selectPage(new Page<SysOrg>(1,10), new EntityWrapper<SysOrg>(sysOrg));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysOrg queryById(@PathVariable(value="id") String id) {
		return sysOrgService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysOrg sysOrg) {
		return sysOrgService.insertOrUpdate(sysOrg);
	}
}
