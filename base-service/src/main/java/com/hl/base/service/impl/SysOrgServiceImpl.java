package com.hl.base.service.impl;

import com.hl.base.facade.model.SysOrg;
import com.hl.base.service.dao.SysOrgDao;
import com.hl.base.facade.service.ISysOrgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 机构管理（中心、分站） 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrg> implements ISysOrgService {

}
