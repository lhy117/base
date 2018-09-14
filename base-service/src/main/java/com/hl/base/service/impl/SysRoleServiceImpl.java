package com.hl.base.service.impl;

import com.hl.base.facade.model.SysRole;
import com.hl.base.service.dao.SysRoleDao;
import com.hl.base.facade.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用-角色表 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

}
