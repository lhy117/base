package com.hl.base.service.impl;

import com.hl.base.facade.model.SysResources;
import com.hl.base.service.dao.SysResourcesDao;
import com.hl.base.facade.service.ISysResourcesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用-功能资源表 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysResourcesServiceImpl extends ServiceImpl<SysResourcesDao, SysResources> implements ISysResourcesService {

	@Resource
	private SysResourcesDao sysResourcesDao; 
	
	
	@Override
	public List<String> queryPermissions(String userId) {
		return sysResourcesDao.queryPermissions(userId);
	}

}
