package com.hl.base.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hl.base.facade.service.ICodeGenerateService;
import com.hl.base.util.generator.CodeGenerator;
import com.hl.base.util.generator.config.ContextConfig;
import com.hl.base.util.model.Result;

import lombok.NonNull;

/**
 * <p>
 * 云调度-用户 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-07-26 14:22:23
 */
@RestController
@RequestMapping("/genernate")
public class CodeGenerateController {
	
	@Value("${dbUserName}")
	@NonNull private String dbUserName;
	@Value("${dbPassword}")
	@NonNull private String dbPassword;
	@Value("${dbUrl}")
	@NonNull private String dbUrl;
	
	@Reference
	private ICodeGenerateService generateService;

	@GetMapping("/listTable")
	public Result<List<String>> listTable(){
	
		return new Result<List<String>>(true, generateService.listTable());
	}
	
	@PostMapping(value = "/doGen")
	public Result<String> doGen(@RequestBody ContextConfig config) {
		config.setDbUserName(dbUserName);
		config.setDbPassword(dbPassword);
		config.setDbUrl(dbUrl);
		CodeGenerator gen = new CodeGenerator(config);
		gen.doGenerate();
		return new Result<>(true, "success!");
	}
}

