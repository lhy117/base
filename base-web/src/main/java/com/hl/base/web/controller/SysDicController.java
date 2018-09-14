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
import com.hl.base.facade.model.SysDic;
import com.hl.base.facade.service.ISysDicService;

/**
 * <p>
 * 通用字典表 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@RequestMapping("/sysDic")
public class SysDicController {

	@Reference
	private ISysDicService sysDicService;
	
	@PostMapping(value = "/listPage")
	public Page<SysDic> listPage(@RequestBody SysDic sysDic) {
		return sysDicService.selectPage(new Page<SysDic>(1,10), new EntityWrapper<SysDic>(sysDic));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysDic queryById(@PathVariable(value="id") String id) {
		return sysDicService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody SysDic sysDic) {
		return sysDicService.insertOrUpdate(sysDic);
	}
}
