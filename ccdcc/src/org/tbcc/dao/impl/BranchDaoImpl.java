package org.tbcc.dao.impl;

import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.BranchDao;
import org.tbcc.entity.TbccBranchType;


public class BranchDaoImpl extends HibernateDaoSupport implements BranchDao {


	public TbccBranchType get(Long branchId) {
		return (TbccBranchType)this.getHibernateTemplate().get(TbccBranchType.class, branchId);
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccBranchType> getByIds(String condition) {
		String hql = "from TbccBranchType b where b.branchId in "+condition ;
		return this.getHibernateTemplate().find(hql) ;
	}


}
