package com.hl.shiro.permission;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hl.base.facade.constants.GlobalConstant;

/**
 * 自定义权限认证
 * @author lhy
 * @date 2018年3月26日 下午5:40:32
 */
public class UrlPermission implements Permission,Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UrlPermission.class);
	protected static final String PART_DIVIDER_TOKEN = GlobalConstant.FORWARD_SLASH;    
    protected static final boolean DEFAULT_CASE_SENSITIVE = false;
     
    private String permissionString = "";
    private String[] permissionArr;
     
    public UrlPermission () {
    }
 
    public UrlPermission (String permissionString) {
        this(permissionString, DEFAULT_CASE_SENSITIVE);
    }
     
    public UrlPermission (String permissionString, boolean caseSensitive) {
        logger.info("The constructor MyPermission () was invoke.");
        setParts(permissionString, caseSensitive);
    }
     
    private void setParts(String permissionString, boolean caseSensitive) {
        if (StringUtils.isBlank(permissionString)) {
            permissionString = "";
        }
        if (caseSensitive) {
            permissionString = permissionString.toLowerCase();
        }
        if(permissionString.contains("{")){
        	this.permissionString = permissionString.replaceAll("\\{.*?\\}", "*");
        } else {
        	this.permissionString = permissionString;
        }
        permissionArr = this.permissionString.split(GlobalConstant.FORWARD_SLASH);
    }
 
    @Override
    public boolean implies(Permission p) {
        logger.info("The method implies() was invoke.");
        if (!(p instanceof UrlPermission)) {
            return false;
        }
        UrlPermission that = (UrlPermission) p;
        if(!permissionString.contains("*")){
        	return this.permissionString.equals(that.getPermissionString());
        } else {
        	if(StringUtils.isBlank(that.getPermissionString())){
        		return false;
        	}
        	int i = 0;
        	for (String str : this.permissionArr) {
				if(str.contains("*")){
					i++;
					continue;
				}
				if(i == that.getPermissionArr().length - 1){
					return true;
				}
				if(i > that.getPermissionArr().length - 1 || !str.equals(that.getPermissionArr()[i])){
					return false;
				}
				i++;
			}
        }
        return true;
    }

	public String getPermissionString() {
		return permissionString;
	}

	public String[] getPermissionArr() {
		return permissionArr;
	}
    
}
