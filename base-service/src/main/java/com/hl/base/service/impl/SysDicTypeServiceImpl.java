package com.hl.base.service.impl;

import com.hl.base.facade.model.SysDicType;
import com.hl.base.service.dao.SysDicTypeDao;
import com.hl.base.facade.service.ISysDicTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 通用字典类型表 服务实现类
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Service
public class SysDicTypeServiceImpl extends ServiceImpl<SysDicTypeDao, SysDicType> implements ISysDicTypeService {

}
