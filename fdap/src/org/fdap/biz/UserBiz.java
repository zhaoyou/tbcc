package org.fdap.biz;

import org.fdap.entity.Fdapuser;

/**
 * 用户操作业务接口
 * @author zhaoyou
 *
 */


public interface UserBiz {
	
			/**
			 * 根据账户名和用户名获取登录用户信息
			 * @param clientName		账户名
			 * @param name					用户名
			 * @return
			 */
			public Fdapuser 	getLoginUser(String clientName,String name,String password);
			
			
}
