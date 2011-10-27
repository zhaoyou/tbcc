package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ClientDao;
import org.tbcc.entity.TbccClient;

/**
 * 这是操作客户类型的数据访问类
 * @author Administrator
 *
 */
public class ClientDaoImpl extends HibernateDaoSupport implements ClientDao {

	
	@SuppressWarnings("unchecked")
	public	List<TbccClient> getByClientName(String clientName) {
		String hql = "from TbccClient c where c.clientName = '"+clientName+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
