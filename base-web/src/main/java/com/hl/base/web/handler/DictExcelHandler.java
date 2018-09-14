package com.hl.base.web.handler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hl.base.facade.model.SysDic;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DictExcelHandler implements IExcelDictHandler {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private final Cache<String, String> dicCache = CacheBuilder.newBuilder()
    		.maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();
	
	@Override
	public String toName(String dict, Object obj, String name, Object value) {
		if(StringUtils.isBlank(value + "")) {
			return "";
		}
		final String copyValue = (String) value;
		try {
			value = dicCache.get((String)value, new Callable<String>() {
				@Override
				public String call() throws Exception {
					for (Object obj : redisTemplate.opsForHash().values(dict)) {
						SysDic dic= JSON.parseObject(JSON.toJSONString(obj), SysDic.class);
						if(dic.getId().equals(copyValue)) {
							return dic.getName();
						}
					}
					return "";
				}
				
			});
		} catch (ExecutionException e) {
			log.error("导出字典解析失败", e);
		}
		return "";
	}

	@Override
	public String toValue(String dict, Object obj, String name, Object value) {
		if(StringUtils.isBlank(value + "")) {
			return "";
		}
		final String copyValue = (String) value;
		try {
			value = dicCache.get((String)value, new Callable<String>() {
				@Override
				public String call() throws Exception {
					for (Object obj : redisTemplate.opsForHash().values(dict)) {
						SysDic dic= JSON.parseObject(JSON.toJSONString(obj), SysDic.class);
						if(dic.getName().equals(copyValue)) {
							return dic.getId();
						}
					}
					return "";
				}
				
			});
		} catch (ExecutionException e) {
			log.error("导入字典解析失败", e);
		}
		return "" + value; 
	}
}
