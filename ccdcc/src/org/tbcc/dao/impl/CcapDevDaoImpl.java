package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CcapDevDao;
import org.tbcc.entity.cool.TbccCcapDevType;

public class CcapDevDaoImpl extends HibernateDaoSupport implements CcapDevDao {

	@SuppressWarnings("unchecked")
	public List<TbccCcapDevType> getByProjectId(String projectId) {
		String hql = "from TbccCcapDevType d where d.projectId = '"+projectId+"'" ;
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<TbccCcapDevType> getByCondition(String str) {
		String hql = "from TbccCcapDevType d where d.projectId in "+str ;
		return this.getHibernateTemplate().find(hql);
	}

}
