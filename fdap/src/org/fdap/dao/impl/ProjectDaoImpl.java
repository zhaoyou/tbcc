package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.ProjectDao;
import org.fdap.entity.Fdapproject;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 工程的数据访问接口
 * @author zhaoyou
 *
 */
public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	
	
	@SuppressWarnings("unchecked")
	public List<Fdapproject> queryByOidAndType(Long oid, Integer type) {
		String hql = "from Fdapproject p where p.fdaporg.oid =  ? and p.type = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, oid);
		query.setInteger(1, type);
		return query.list();
	}

	public Fdapproject queryByProjectId(Long projectId) {
		return (Fdapproject)this.getHibernateTemplate().get(Fdapproject.class, projectId);
	}

	

}
