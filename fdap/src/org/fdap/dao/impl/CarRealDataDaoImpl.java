package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.CarRealDataDao;
import org.fdap.entity.Fdapcarrealdata;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 车载实时数据实现类
 * @author zhaoyou
 *
 */
public class CarRealDataDaoImpl extends HibernateDaoSupport implements
		CarRealDataDao {

	@SuppressWarnings("unchecked")
	public List<Fdapcarrealdata> queryByProjectIds(List<Long> projectIds) {
		String hql = "from Fdapcarrealdata r where r.projectId  in (:ids)" ;
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", projectIds);	
		return query.list();
	}

}
