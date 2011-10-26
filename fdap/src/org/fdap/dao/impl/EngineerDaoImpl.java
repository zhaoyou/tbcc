package org.fdap.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.entity.Fdapuser;
import org.fdap.dao.EngineerDao;

public class EngineerDaoImpl extends HibernateDaoSupport implements EngineerDao {
	@SuppressWarnings("unchecked")
	public List<Fdapuser> getEngineer() {
		String hql="from Fdapuser fu where fu.fdaprole.rid=1";
		return this.getHibernateTemplate().find(hql);
	}

	public boolean upUser(Fdapuser user) {
		try {
			getHibernateTemplate().merge(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace() ;
			return false;
		}
	}

}
