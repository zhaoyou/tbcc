package org.tbcc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.AiInfoDao;
import org.tbcc.entity.TbccAiInfo;

/**
 * 这是操作探头的数据访问实现类
 * @author Administrator
 *
 */
public class AiInfoDaoImpl extends HibernateDaoSupport implements AiInfoDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccAiInfo> getAiListByproId(String proId) {
		String hql = "from TbccAiInfo a where a.projectId='"+proId+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TbccAiInfo> getRefAiList(String proId, String netId,
			String refId) {
		String hql = "from TbccAiInfo a where a.projectId = '"+proId+"' and a.netid='"+netId
		+"' and a.refid = '"+refId+"'";
		return this.getHibernateTemplate().find(hql);
	}


	@SuppressWarnings("unchecked")
	public List<TbccAiInfo> getAiListByRid(Long rid) {
		String hql = "from TbccAiInfo a where a.rid = ?" ;
		Session session =  this.getSession() ;
		Query query = session.createQuery(hql);
		query.setLong(0, rid);
		return query.list();
	}

}
