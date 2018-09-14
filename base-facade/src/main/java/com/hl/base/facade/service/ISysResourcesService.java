package com.hl.base.facade.service;

import com.hl.base.facade.model.SysResources;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 通用-功能资源表 服务类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
public interface ISysResourcesService extends IService<SysResources> {

	/**
	 * 查询用户资源权限
	 * @param loginUserId
	 * @return
	 */
	List<String> queryPermissions(String userId);

}
