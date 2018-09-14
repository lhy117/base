package com.hl.base.service.impl;

import com.hl.base.facade.model.SysDic;
import com.hl.base.service.dao.SysDicDao;
import com.hl.base.facade.service.ISysDicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用字典表 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicDao, SysDic> implements ISysDicService {

}
