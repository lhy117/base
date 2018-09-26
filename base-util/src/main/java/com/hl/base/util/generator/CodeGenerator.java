package com.hl.base.util.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hl.base.util.generator.config.ContextConfig;
import com.hl.base.util.generator.template.BaseTemplateEngine;

/**
 * 代码生成
 * @author lhy
 * @date 2018-08-02 16:15:22
 */
public class CodeGenerator {

	private ContextConfig config;
	private GlobalConfig globalConfig = new GlobalConfig();
	private DataSourceConfig dataSourceConfig = new DataSourceConfig();
	private StrategyConfig strategyConfig = new StrategyConfig();
	private PackageConfig packageConfig = new PackageConfig();
	private TemplateConfig templateConfig = new TemplateConfig();
	
	public CodeGenerator(ContextConfig config) {
		this.config = config;
	}
	
	public void doGenerate() {
	    
		buildConfigs();
        //代码生成
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setTemplateEngine(new BaseTemplateEngine(config));
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.execute();
	}
	
	private void buildConfigs() {
		//数据库配置
	    dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername(config.getDbUserName());
        dataSourceConfig.setPassword(config.getDbPassword());
        dataSourceConfig.setUrl(config.getDbUrl());
        //全局参数
        //写自己项目的绝对路径,注意具体到java目录
		globalConfig.setOutputDir(config.getOutputFilePath());
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor(config.getAuthor());
        globalConfig.setIdType(IdType.UUID);
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        //生成策略
        //此处可以修改为您的表前缀
        strategyConfig.setTablePrefix(config.getTablePrefix());
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(config.getSuperEntity());
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setSuperEntityColumns(new String[]{"id","create_user","create_time","update_user","update_time","status"});
        strategyConfig.setInclude(config.getIncluedTables());
        strategyConfig.setSkipView(true);
        //strategyConfig.setEntityLombokModel(true);
        //包的配置参数
        packageConfig.setParent(null);
        packageConfig.setEntity(config.getModelPackage());
        packageConfig.setMapper(config.getDaoPackage());
        //packageConfig.setXml(config.get);
        packageConfig.setController(config.getContollerPackage());
        packageConfig.setService(config.getServicePackage());
        packageConfig.setServiceImpl(config.getServiceImplPackage());
        //自定义模板
        templateConfig.setController("/template/controller.java");
        templateConfig.setEntity("/template/entity.java");
        templateConfig.setMapper("/template/mapper.java");
        templateConfig.setService("/template/service.java");
        templateConfig.setServiceImpl("/template/serviceImpl.java");
        templateConfig.setXml("/template/mapper.xml");
	}
}
