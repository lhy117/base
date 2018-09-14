package com.hl.base.facade.constants;

import java.io.File;

/**
 * @ClassName: GlobalConstant 
 * @Description: 全局变量类
 * @author: lhy
 * @date: 2017-10-17
 */
public class GlobalConstant {
	/**
	 * 全局用户名设置
	 */
	public static final String USER_NAME = "userName";
	/**
	 * 全局用户编号
	 */
	public static final String USER_ID = "userId";
	/**
	 * 医疗机构编号
	 */
	public static final String ORG_ID = "orgId";
	/**
	 * 用户模型
	 */
	public static final String USER = "user";
	/**
	 *职工id
	 */
	public static final String STAFF_ID = "staffId";
	/**
	 * 医疗机构模型
	 */
	public static final String ORG = "org";
	/**
	 * 医疗机构名称
	 */
	public static final String ORG_NAME = "orgName";
	/**
	 * 基础服务平台系统别名
	 */
	public static final String BASE_SYS_ALIAS = "base";
	/**
	 * 基础服务平台字典模块别名
	 */
	public static final String BASE_DICT = "dict";
	/**
	 * 数据正常标志
	 */
	public static final String NORMAL_FLAG = "0";
	/**
	 * 数据删除标志
	 */
	public static final String DELETE_FLAG = "1";
	/**
	 * 逗号标志
	 */
	public static final String COMMA = ",";
	/**
	 * 分号标志
	 */
	public static final String SEMICOLON = ";";
	/**
	 * 短横线标志
	 */
	public static final String HYPHEN = "-";
	/**
	 * 冒号标志
	 */
	public static final String COLON = ":";
	/**
	 * 句号或点标志
	 */
	public static final String PERIOD = ".";
	/**
	 * 问号号标志
	 */
	public static final String QUESTION_MARK = "?";
	/**
	 * 下划线标志
	 */
	public static final String UNDERLINE = "_";
	/**
	 * 文件分隔符
	 */
	public static final String FILE_SEPARATOR = File.separator;
	/**
	 * 正斜线
	 */
	public static final String FORWARD_SLASH = "/";
	/**
	 * 通用字典代码
	 */
	public static final int DIC_COMMON = 1;
	/**
	 * 地区字典代码
	 */
	public static final int DIC_AREA = 2;
	/**
	 * IDC10字典代码
	 */
	public static final int DIC_IDC10 = 3;
	/**
	 * 急救物资字典代码
	 */
	public static final int DIC_MATERIAL = 4;
	/**
	 * 急救药品字典代码
	 */
	public static final int DIC_DRUG = 5;
	/**
	 * 救治措施字典代码
	 */
	public static final int DIC_TREATMENT = 6;
}
