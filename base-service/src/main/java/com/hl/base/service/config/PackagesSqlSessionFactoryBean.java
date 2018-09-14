package com.hl.base.service.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import com.hl.base.facade.constants.GlobalConstant;

/** 
 * Spring Mybatis整合 
 * 通过通配符方式配置typeAliasesPackage 
 * @author liheyu
 * @date 2017-09-20    
 */
public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {
	
	private static final Logger logger = LoggerFactory.getLogger(PackagesSqlSessionFactoryBean.class);
	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	 
    @Override  
    public void setTypeAliasesPackage(String typeAliasesPackage) {  
    	ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<String> packages = new ArrayList<>();
        for (String alias : typeAliasesPackage.split(GlobalConstant.COMMA)) {
        	packages.add(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(alias) + "/" + DEFAULT_RESOURCE_PATTERN);
        }
        //将加载多个绝对匹配的所有Resource    
        //将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分    
        //然后进行遍历模式匹配    
        try {    
            List<String> result = new ArrayList<String>();    
            for (String pack : packages) {
            	Resource[] resources =  resolver.getResources(pack);    
            	if(resources != null && resources.length > 0){    
            		MetadataReader metadataReader = null;    
            		for(Resource resource : resources){    
            			if(resource.isReadable()){    
            				metadataReader =  metadataReaderFactory.getMetadataReader(resource);    
            				try {    
            					result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());    
            				} catch (ClassNotFoundException e) {    
            					e.printStackTrace();    
            				}    
            			}    
            		}    
            	}    
			}
            if(result.size() > 0) {    
                super.setTypeAliasesPackage(StringUtils.join(new HashSet<String>(result).toArray(), ","));    
            }else{    
                logger.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");    
            }    
        } catch (IOException e) {    
        	logger.error(e.getMessage());   
        }    
    }  
}
