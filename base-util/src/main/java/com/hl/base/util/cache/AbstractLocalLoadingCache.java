package com.hl.base.util.cache;
 
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
 
/**
 * 本地缓存抽象类
 * @Author liheyu
 * @date 2018-08-28
 */
public abstract class AbstractLocalLoadingCache<K, V>
{
    private LoadingCache<K,V> cache;
 
    public AbstractLocalLoadingCache()
    {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }
 
    /**
     * 超时缓存：数据写入缓存超过一定时间自动刷新
     * @param duration
     * @param timeUtil
     */
    public AbstractLocalLoadingCache(long duration, TimeUnit timeUtil, int maxSize)
    {
        cache = CacheBuilder.newBuilder()
        		.maximumSize(maxSize)
                .expireAfterWrite(duration, timeUtil)
                .refreshAfterWrite(duration / 2, timeUtil)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception
                    {
                    	System.out.println("LocalLoadingCache");
                        return loadData(k);
                    }
                });
    }
     
    /**
     * 限容缓存：缓存数据个数不能超过maxSize
     * @param maxSize
     */
    public AbstractLocalLoadingCache(long maxSize)
    {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }
     
    /**
     * 权重缓存：缓存数据权重和不能超过maxWeight
     * @param maxWeight
     * @param weigher：权重函数类，需要实现计算元素权重的函数
     */
    public AbstractLocalLoadingCache(long maxWeight, Weigher<K, V> weigher)
    {
        cache = CacheBuilder.newBuilder()
                .maximumWeight(maxWeight)
                .weigher(weigher)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
        
        cache.invalidateAll();
    }
 
 
    /**
     *
     * 缓存数据加载方法
     * @param k
     * @return
     */
    protected abstract V loadData(K k);

	public LoadingCache<K, V> getCache() {
		return cache;
	}
}
