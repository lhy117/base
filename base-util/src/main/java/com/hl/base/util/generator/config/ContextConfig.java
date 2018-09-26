package com.hl.base.util.generator.config;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 代码生成自定义配置模型
 * @author liheyu
 * @date 2018-08-02
 */
@Data
@NoArgsConstructor
public class ContextConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 输出目录  */
	@NonNull
	private String outputFilePath;
	/** 生成文件包含的数据表  */
	@NonNull
	private String[] incluedTables;
	/** controller包的路径  */
	private String contollerPackage;
	/** service包的路径  */
	private String servicePackage;
	/** service包的路径  */
	private String serviceImplPackage;
	/** model包的路径  */
	private String modelPackage;
	/** 父model的路径  */
	private String superEntity;
	/** dao包的路径  */
	private String daoPackage;
	/** 数据库账号 */
	@NonNull
	private String dbUserName;
	/** 数据库账号 */
	@NonNull
	private String dbPassword;
	/** 数据库账号 */
	@NonNull
	private String dbUrl;
	/** 作者 */
	@NonNull
	private String author;
	/** 作者 */
	private String[] tablePrefix;
	/** controller包的路径  */
	private Boolean controllerSwitch = true;
	/** controller包的路径  */
	private Boolean daoSwitch = true;
	/** controller包的路径  */
	private Boolean serviceSwitch = true;
	/** 输出目录  */
	private Boolean entitySwitch = true;
	/** 输出目录  */
	private Boolean sqlSwitch = false;

}
