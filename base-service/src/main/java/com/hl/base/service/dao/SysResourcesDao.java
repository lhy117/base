package com.hl.base.service.dao;

import com.hl.base.facade.model.SysResources;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 通用-功能资源表 Mapper 接口
 * </p>
 * @author liheyu
 * @date 2018-08-01
 */
public interface SysResourcesDao extends BaseMapper<SysResources> {

	/**
	 * 查询当前用户资源权限
	 * @param userId userId
	 * @return
	 */
	List<String> queryPermissions(String userId);

}
