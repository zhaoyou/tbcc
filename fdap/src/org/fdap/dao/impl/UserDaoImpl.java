package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.UserDao;
import org.fdap.entity.Fdapuser;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 用户访问数据实现接口
 * @author zhaoyou
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

		
	@SuppressWarnings("unchecked")
	public List<Fdapuser> queryByClientAndUname(String clientName, String uname) {
		String hql = "from Fdapuser user where user.fdaporg.flag = 0 and  user.fdaporg.account = ? and user.name = ? " ;
		Query query = this.getSession().createQuery(hql);
		query.setString(0, clientName);
		query.setString(1, uname);
		return query.list();
	}

}
