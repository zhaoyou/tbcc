package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CompressorSetDao;
import org.tbcc.entity.cool.TbccCompressorSet;

public class CompressorSetDaoImpl extends HibernateDaoSupport implements
		CompressorSetDao {

	@SuppressWarnings("unchecked")
	public List<TbccCompressorSet> getByDevId(Integer devId) {
		String hql = "from TbccCompressorSet p where p.tbccCcapDevType.id = "+devId ;
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<TbccCompressorSet> getByCondition(String str) {
		String hql = "from TbccCompressorSet p where p.tbccCcapDevType.id in  "+str ;
		return this.getHibernateTemplate().find(hql);
	}

	public TbccCompressorSet getById(Integer id) {
		return (TbccCompressorSet)this.getHibernateTemplate().get(TbccCompressorSet.class, id);
	}

}
