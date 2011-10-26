package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.OrgDao;
import org.fdap.entity.Fdaporg;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 机构数据访问接口
 * @author zhaoyou
 *
 */
public class OrgDaoImpl extends HibernateDaoSupport implements OrgDao {

	
	@SuppressWarnings("unchecked")
	public List<Fdaporg> queryByParentId(Long parentId) {
		String hql = "from Fdaporg org where  org.flag = 0 and org.parentId = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, parentId);
		return query.list();
	}

	
	public Fdaporg queryByOid(Long oid) {
		return  (Fdaporg)this.getHibernateTemplate().get(Fdaporg.class, oid);
	}


	@SuppressWarnings("unchecked")
	public List<Long> queryLowerOrgByParentId(Long parentId) {
		String hql = "select org.oid from Fdaporg org where org.flag = 0 and org.parentId = ?" ;
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, parentId);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List<Fdaporg> queryAll() {
		String hql= "from Fdaporg org where org.flag = 0" ;
		return this.getSession().createQuery(hql).list();
	}


	/*@SuppressWarnings("unchecked")
	public List<Fdaporg> queryLowerOrgListByParentId(Long parentId) {
		String hql = "from Fdaporg org where org.flag = 0 and org.nodetype=1 and org.parentId = ?" ;
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, parentId);
		return query.list();
	}*/

}
