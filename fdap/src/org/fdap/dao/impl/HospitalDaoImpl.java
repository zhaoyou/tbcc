package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.HospitalDao;
import org.fdap.entity.FdapHospital;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HospitalDaoImpl extends HibernateDaoSupport implements HospitalDao {

	@Override
	public void addHospital(FdapHospital obj) {
		this.getHibernateTemplate().save(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FdapHospital> getAll() {
		return (List<FdapHospital>)this.getHibernateTemplate().find("from FdapHospital");
	}

	@Override
	public void updateHospital(FdapHospital updateObj) {
		this.getHibernateTemplate().update(updateObj);
	}

	@Override
	public void deleteHospital(FdapHospital deleteObj) {
		this.getHibernateTemplate().delete(deleteObj);
	}

	@Override
	public FdapHospital get(Integer id) {
		return (FdapHospital)this.getHibernateTemplate().get(FdapHospital.class, id);
	}

}
