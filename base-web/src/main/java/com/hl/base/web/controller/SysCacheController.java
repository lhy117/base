package com.hl.base.web.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 云调度-用户 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-07-26 14:22:23
 */
@RestController
@RequestMapping("/sysCache")
public class SysCacheController extends BaseController{

	@GetMapping(value = "/listPage")
	public List<SysCache> listPage(@RequestParam String key) {
		
		List<SysCache> caches = new ArrayList<>();
		/*Set<String> keySets = CacheKeyUtil.keys("*" + key + "*");
		for (String str : keySets) {
			switch (CacheKeyUtil.keyType(str)) {
			case "string" :
				caches.add(new SysCache(str, "string", CacheStringUtil.get(str)));
				break;
			case "list" :
				caches.add(new SysCache(str, "list", CacheListUtil.lrange(str, 0 , -1).toString()));
				break;
			case "hash" :
				caches.add(new SysCache(str, "hash", CacheHashUtil.hgetAll(str).toString()));
				break;
			case "set" :
				caches.add(new SysCache(str, "set", CacheSetUtil.smembers(str).toString()));
				break;
			case "zset" :
				caches.add(new SysCache(str, "sortset", CacheSortSetUtil.zrange(str, 0, -1).toString()));
				break;
			default:
				break;
			}
		}*/
		Set<String> keySets = redisTemplate.keys("*" + key + "*");
		for (String str : keySets) {
			String type = redisTemplate.type(str).code();
			caches.add(new SysCache(str, type, getCacheValue(str, type)));
		}
		return caches;
	}
	
	@GetMapping(value = "/delete")
	public void delete(@RequestParam List<String> keys) {
		long result = redisTemplate.delete(keys);
		System.out.println(result);
	}
	
	@Data
	@AllArgsConstructor
	class SysCache {
		
		private String key;
		
		private String type;
		
		private Object value;
	}
	
}

