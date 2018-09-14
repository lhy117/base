package com.hl.base.service.impl;

import com.hl.base.facade.model.SysUserRole;
import com.hl.base.service.dao.SysUserRoleDao;
import com.hl.base.facade.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用-用户角色关系表 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:11
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements ISysUserRoleService {

}
