package com.hl.shiro.session;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hl.base.facade.constants.GlobalConstant;
import com.hl.base.facade.model.SysUser;
import com.hl.base.facade.service.ISysResourcesService;
import com.hl.base.facade.service.ISysUserService;

/**
 * 自定义Realm
 * @author lhy
 * @date 2018年3月26日 下午5:23:52
 */
public class BaseRealm extends AuthorizingRealm{

    @Resource
    private ISysUserService sysUserService;
    //@Resource
    //private SysUserRoleService userRoleService;
    @Resource
    private ISysResourcesService sysResourcesService;

    /**
     * 用户认证授权
     * @param principalCollection principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //查询用户角色
        //List<String> roleList = userRoleService.queryToListMap(params);
        //info.addRoles(roleList);
        //查询用户资源权限
        List<String> resourceUrlList = sysResourcesService.queryPermissions(SessionUtils.getLoginUserId());
        if(resourceUrlList != null && resourceUrlList.size() > 0){
            info.addStringPermissions(resourceUrlList);
        }
        return info;
    }

    /**
     * 用户登录认证
     * @param authenticationToken authenticationToken
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = new SysUser();
        user.setLoginName(token.getUsername());
        user = sysUserService.selectOne(new EntityWrapper<SysUser>(user));
        if (user == null) {
            throw new UnknownAccountException();
        }
        // 第一个参数也可以放user对象，这样在doGetAuthorizationInfo()方法中可以直接使用
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute(GlobalConstant.USER_ID, user.getId());
        session.setAttribute(GlobalConstant.USER, user);
        return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
    }
}
