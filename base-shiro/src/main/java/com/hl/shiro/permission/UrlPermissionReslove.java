/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: UrlPermissionReslove.java 
 * @Prject: emdc-shiro
 * @Package: com.hl.shiro.permission 
 * @Description: TODO
 * @author: lhy   
 * @date: 2018年7月11日 上午11:33:51 
 * @version: V1.0   
 */
package com.hl.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hl.base.facade.constants.GlobalConstant;

/** 
 * 自定义权限解析器
 * @author lhy
 * @date 2018年7月11日 上午11:33:51  
 */
public class UrlPermissionReslove implements PermissionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(UrlPermissionReslove.class);
    
    /**
     * @param permissionString
     * @return
     */
    @Override
    public Permission resolvePermission(String permissionString) {
        logger.info("The method resolvePermission(" + permissionString + ") was invoke.");
        if(permissionString.contains(GlobalConstant.FORWARD_SLASH)) {
            return new UrlPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
