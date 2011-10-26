package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.RefDao;
import org.fdap.entity.Fdapref;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 冷库的数据访问接口
 * @author zhaoyou
 *
 */
public class RefDaoImpl extends HibernateDaoSupport implements RefDao {

	
	@SuppressWarnings("unchecked")
	public List<Fdapref> queryByProjectId(Long projectId) {
		String hql = "from Fdapref ref where ref.fdapproject.projectid = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, projectId);
		return query.list();
	}

	public Fdapref queryByRefId(Long refId) {
		return (Fdapref)this.getHibernateTemplate().get(Fdapref.class, refId);
	}

	@SuppressWarnings("unchecked")
	public List<Fdapref> queryRefByOidAndType(String oid,Integer type) {
		String hql="from Fdapref fr where fr.fdapproject.type=? and fr.fdapproject.fdaporg.oid=?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, type);
		query.setString(1, oid);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fdapref> queryByProjectIds(List<Long> projectIds) {
		String hql = "from Fdapref ref where ref.fdapproject.projectid in(:ids) ";
		Query query = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", projectIds) ;
		return query.list();
	}

}
