package org.fdap.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.dao.SysadminDao;
import org.fdap.entity.Fdapuser;

public class SysadminDaoImpl extends HibernateDaoSupport implements SysadminDao {
	
	@SuppressWarnings("unchecked")
	public List<Fdapuser> getSysadmin() {
		String hql="from Fdapuser fu where fu.fdaprole.rid=2";
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
