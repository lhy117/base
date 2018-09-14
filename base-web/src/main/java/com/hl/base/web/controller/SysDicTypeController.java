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
import com.hl.base.facade.model.SysDicType;
import com.hl.base.facade.service.ISysDicTypeService;

/**
 * <p>
 * 通用字典类型表 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysDicType")
public class SysDicTypeController {

	@Reference
	private ISysDicTypeService sysDicTypeService;
	
	@PostMapping(value = "/listPage")
	public Page<SysDicType> listPage(@RequestBody SysDicType sysDicType) {
		return sysDicTypeService.selectPage(new Page<SysDicType>(1,10), new EntityWrapper<SysDicType>(sysDicType));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysDicType queryById(@PathVariable(value="id") String id) {
		return sysDicTypeService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysDicType sysDicType) {
		return sysDicTypeService.insertOrUpdate(sysDicType);
	}
}
