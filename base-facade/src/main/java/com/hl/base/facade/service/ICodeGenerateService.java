package com.hl.base.facade.service;

import java.util.List;

public interface ICodeGenerateService {
	
	/**
	 * 列出数据库所有表
	 * @return
	 */
	List<String> listTable();
}

