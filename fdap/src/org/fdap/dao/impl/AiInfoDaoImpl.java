package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.AiInfoDao;
import org.fdap.entity.Fdapaiinfo;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 探头的数据访问接口
 * @author zhaoyou
 *
 */
public class AiInfoDaoImpl extends HibernateDaoSupport implements AiInfoDao {

	public Fdapaiinfo queryByGuid(String aiGuid) {
		return (Fdapaiinfo)this.getHibernateTemplate().get(Fdapaiinfo.class, aiGuid);
	}

	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryByRefId(Long refId) {
		String hql = "from Fdapaiinfo ai where ai.fdapref.refId = ? " ;
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, refId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryRefAiByProjectIds(List<Long> projectIds) {
		String hql = "from Fdapaiinfo ai where ai.fdapref.fdapproject.projectid in (:ids) order by ai.selftype";
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", projectIds);
		return query.list();
	}
	
	

}
