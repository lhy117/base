package com.hl.base.service.impl;

import com.hl.base.facade.model.SysRoleResource;
import com.hl.base.service.dao.SysRoleResourceDao;
import com.hl.base.facade.service.ISysRoleResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用-角色资源关系 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceDao, SysRoleResource> implements ISysRoleResourceService {

}
