package edu.fjnu.cr.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import edu.fjnu.cr.domain.ActiveUser;
import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.service.SysService;


public class CustomRealm extends AuthorizingRealm{
	@Autowired
	private SysService sysService;
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection Pprincipa) {
		//从Pprincip获取主身份信息
	    //将从Pprincip获取主身份信息转换成真实的用户信息
	   ActiveUser activeUser = (ActiveUser) Pprincipa.getPrimaryPrincipal();
	    //根据用户信息获取权限
	   
	   List<SysPermission> permissionList = null;
	    try {
			permissionList = sysService.findUserPermissonById(activeUser.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    //单独定义一个集合对象
	    List<String> permissions = new ArrayList<String>();
	    if(permissionList!=null){
		    for(SysPermission permisson:permissionList){
		    	//将数据库中的权限标签 符放入集合
				permissions.add(permisson.getPercode());
		    }
	    }
	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    
	    //将查询到得权限填充到authorizationInfo
	    authorizationInfo.addStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.从token中获取用户账号
		String userCode = (String) token.getPrincipal();
		//根据用户账号从数据库中取数据
		SysUser sysUser = null;
		
		try {
			sysUser = sysService.findSysUserByCode(userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//如果查询不打就返回null
		if(sysUser== null){
			return null;
		}
		//从数据库获取密码
		String password = sysUser.getPassword();
		
		//从数据库获取盐
		String salt = sysUser.getSalt();
		//根据用户id查询出用户菜单
		List<SysPermission> menus =null;
		try {
			menus = sysService.findUserMenuById(sysUser.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//设置用户信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUserName(sysUser.getUsername());
		activeUser.setMenus(menus);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, password,ByteSource.Util.bytes(salt), this.getName());
		return authenticationInfo;
	}

}
