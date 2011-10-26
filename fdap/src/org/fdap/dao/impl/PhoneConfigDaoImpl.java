package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.PhoneConfigDao;
import org.fdap.entity.Fdapphone;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PhoneConfigDaoImpl extends HibernateDaoSupport implements PhoneConfigDao {
	
	@SuppressWarnings("unchecked")
	public List<Fdapphone> queryPhone() {
		String hql = "from Fdapphone";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public boolean updatePhone(Fdapphone phoneinfo) {
		try{
			this.getHibernateTemplate().update(phoneinfo) ;
			return true;
		}
		catch (DataAccessException e) {
			return false;
		}
	}

	
}
