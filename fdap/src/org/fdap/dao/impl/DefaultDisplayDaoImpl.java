package org.fdap.dao.impl;

import org.fdap.dao.DefaultDisplayDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DefaultDisplayDaoImpl extends HibernateDaoSupport implements DefaultDisplayDao {

	@Override
	public void updateOrg(Long oid, Integer isShowMap) {
		String hql = "update Fdaporg org set org.isshowmap = ? where org.oid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setInteger(0, isShowMap);
		query.setLong(1, oid) ;
		query.executeUpdate() ;
	}

}
