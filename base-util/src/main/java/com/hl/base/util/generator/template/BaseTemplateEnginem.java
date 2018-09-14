package com.hl.base.util.generator.template;

import java.util.Map;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.hl.base.util.generator.config.ContextConfig;

public class BaseTemplateEnginem extends FreemarkerTemplateEngine {

	private ContextConfig config;
	
	public BaseTemplateEnginem(ContextConfig config) {
		this.config = config;
	};
	
	@Override
	public AbstractTemplateEngine batchOutput() {
		return super.batchOutput();
	}

	@Override
	public AbstractTemplateEngine mkdirs() {
		Map<String, String> paths = getConfigBuilder().getPathInfo();
		if(!config.getControllerSwitch()) {
			paths.remove(ConstVal.CONTROLLER_PATH);
		}
		if(!config.getServiceSwitch()) {
			paths.remove(ConstVal.SERVICE_PATH);
			paths.remove(ConstVal.SERVICEIMPL_PATH);
		}
		if(!config.getEntitySwitch()) {
			paths.remove(ConstVal.ENTITY_PATH);
		}
		if(!config.getDaoSwitch()) {
			paths.remove(ConstVal.MAPPER_PATH);
		}
		//生成系统资源表的insert sql
		if(config.getSqlSwitch()) {
			paths.put("sql_path", "/");
		}
		return super.mkdirs();
	}
	
	
}

