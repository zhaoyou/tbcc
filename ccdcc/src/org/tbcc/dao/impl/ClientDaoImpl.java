package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ClientDao;
import org.tbcc.entity.TbccClient;

/**
 * ���ǲ����ͻ����͵����ݷ�����
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
