package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.UserBiz;
import org.fdap.dao.OrgDao;
import org.fdap.dao.UserDao;
import org.fdap.entity.Fdapuser;

/**
 * 用户操作的业务实现类
 * @author zhaoyou
 *
 */
public class UserBizImpl implements UserBiz {
	
	private UserDao userDao = null ;

	private OrgDao orgDao = null ;
	
	
	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public Fdapuser getLoginUser(String clientName, String name ,String password) {
		
		//验证参数
		if(clientName==null || clientName.equals("") || name==null || name.equals("")||
				password==null || password.equals("")){
			return null ;
		}
		//获取用户信息
		List<Fdapuser> userList = userDao.queryByClientAndUname(clientName, name);

		if(userList==null || userList.size()==0){
			return null ;
		}
		
		//判断密码
		Fdapuser user = userList.get(0) ;		
		if(!user.getPassword().equals(password)){
			return null ;
		}
		return user;
	}

	
	

}
