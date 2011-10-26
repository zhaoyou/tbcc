package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.RealAlarmDao;
import org.fdap.entity.Fdaprealalarm;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 探头实时报警数据访问接口
 * @author zhaoyou
 *
 */
public class RealAlarmDaoImpl extends HibernateDaoSupport implements RealAlarmDao {

	
	@SuppressWarnings("unchecked")
	public List<Fdaprealalarm> queryAlarmByProjectIds(List<Long> projectIds) {
		String hql = "from Fdaprealalarm alarm where alarm.isrealalarm=1 and  alarm.fdapaiinfo.fdapref.fdapproject.projectid in (:ids)" ;
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", projectIds);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Long> queryAlarmOid() {
		String hql = "select distinct alarm.orgId from Fdaprealalarm alarm where alarm.isrealalarm=1" ;
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

}
