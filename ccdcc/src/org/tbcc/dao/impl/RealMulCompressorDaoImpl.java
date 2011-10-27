package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RealMulCompressorDao;
import org.tbcc.entity.cool.TbccMultiCompressorRealData;

public class RealMulCompressorDaoImpl extends HibernateDaoSupport implements
		RealMulCompressorDao {

	@SuppressWarnings("unchecked")
	public List<TbccMultiCompressorRealData> getByCsId(Integer csId) {
		String hql = "from TbccMultiCompressorRealData m where m.csId=" + csId ;
		return this.getHibernateTemplate().find(hql);
	}

}
