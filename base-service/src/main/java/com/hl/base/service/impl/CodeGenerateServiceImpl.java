package com.hl.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.hl.base.facade.service.ICodeGenerateService;
import com.hl.base.service.dao.CodeGenerateDao;

/**
 * <p>
 * 云调度-配置 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:40:36
 */
@Service
public class CodeGenerateServiceImpl implements ICodeGenerateService {

	@Resource
	private CodeGenerateDao generateDao;
	
	@Override
	public List<String> listTable() {
		return generateDao.listTable();
	}

}
