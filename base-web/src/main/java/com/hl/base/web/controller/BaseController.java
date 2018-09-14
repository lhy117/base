package com.hl.base.web.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hl.base.facade.model.SysOrg;
import com.hl.base.facade.service.ISysOrgService;
import com.hl.base.util.cache.LocalLoadingCache;
import com.hl.base.web.handler.DictExcelHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	@Reference
	@Lazy
	private ISysOrgService orgService;
	
	@Resource
	@Lazy
	protected RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	@Lazy
	protected DictExcelHandler dictHandler;
	
	protected LocalLoadingCache<String, String> orgCache;
	
	public BaseController() {
		orgCache = new LocalLoadingCache<String, String>(30, TimeUnit.MINUTES, 100) {
			@Override
			protected String loadData(String k) {
				SysOrg org = new SysOrg();
				org.setOrgName(k);
				return orgService.selectOne(new EntityWrapper<SysOrg>(org)).getId();
			}
		};
	}
	
	public Object getCacheValue(String key, String type) {
		if(StringUtils.isBlank(type)) {
			type = redisTemplate.type(key).code();
		}
		try {
			switch (type) {
			case "string":
				return redisTemplate.opsForValue().get(key);
			case "list" :
				return redisTemplate.opsForList().range(key, 0, -1);
			case "hash" :
				return redisTemplate.opsForHash().entries(key);
			case "set" :
				return redisTemplate.opsForSet().members(key);
			case "zset" :
				return redisTemplate.opsForZSet().range(key, 0, -1);
			default:
			}
		} catch (Exception e) {
			log.error("查询value失败:" + key, e);
		}
		return null;
	}
	
}
