package com.hl.base.service.impl;

import com.hl.base.facade.model.SysConfig;
import com.hl.base.service.dao.SysConfigDao;
import com.hl.base.facade.service.ISysConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 云调度-配置 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:40:36
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements ISysConfigService {

}
