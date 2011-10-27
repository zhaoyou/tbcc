package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RealSingleCompressorDao;
import org.tbcc.entity.cool.TbccSingleCompressorRealData;

public class RealSingleCompressorDaoImpl extends HibernateDaoSupport implements
		RealSingleCompressorDao {

	@SuppressWarnings("unchecked")
	public List<TbccSingleCompressorRealData> get(Integer id) {
		String hql = "from TbccSingleCompressorRealData where csId = "+id ;
		return this.getHibernateTemplate().find(hql);
	}

}
