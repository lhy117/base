package com.hl.base.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hl.base.facade.model.SysUser;
import com.hl.base.facade.service.ISysUserService;
import com.hl.base.service.dao.SysUserDao;

/**
 * <p>
 * 云调度-用户 服务实现类
 * </p>
 * @author liheyu
 * @since 2018-07-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

}
