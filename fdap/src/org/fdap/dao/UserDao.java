package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdapuser;


/**
 * 用户操作的数据访问接口
 * @author zhaoyou
 *
 */

public interface UserDao {
		/**
		 * 通过用户名和用户对应的账户查询用户信息
		 * @param clientName			账户名
		 * @param uname					用户名
		 * @return
		 */
		public List<Fdapuser>			queryByClientAndUname(String clientName,String uname) ;
}
