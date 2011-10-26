package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.RefRealDataDao;
import org.fdap.entity.Fdaprefrealdata;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 冷库实时数据实现类
 * @author zhaoyou
 *
 */
public class RefRealDataDaoImpl  extends HibernateDaoSupport implements RefRealDataDao{

	@SuppressWarnings("unchecked")
	public List<Fdaprefrealdata> queryByProjectIds(List<Long> projectIds) {
		String hql = "from Fdaprefrealdata real where" +
				" real.fdapaiinfo.fdapref.fdapproject.projectid in (:ids)" ;
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", projectIds);
		return query.list();
	}

}
