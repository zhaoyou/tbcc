package org.fdap.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.fdap.dao.ProjectConfigDao;
import org.fdap.entity.Fdapproject;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 工程配置数据实现类
 * @author zhaoyou
 *
 */
public class ProjectConfigDaoImpl extends HibernateDaoSupport implements
		ProjectConfigDao {

	public void deleteById(Long projectId) {
		String hql = "delete from Fdapproject p where p.projectid = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, projectId) ;
		query.executeUpdate() ;	
	}

	public void insertFdapProject(Fdapproject project) {
			this.getHibernateTemplate().save(project);
	}

	public Fdapproject queryById(Long projectId) {
		return (Fdapproject)this.getHibernateTemplate().get(Fdapproject.class, projectId);
	}

	@SuppressWarnings("unchecked")
	public List<Fdapproject> queryByOid(Long oid) {
		String hql = "from Fdapproject p where p.fdaporg.oid = ? order by p.type" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, oid) ;
		return query.list();
	}

	public void updateFdapProject(Fdapproject project) {
		String hql = "update Fdapproject p set p.name = ? ,p.type= ? ,p.remark = ?,p.projectNO = ? where p.projectid = ?" ;
		Query query = this.getSession().createQuery(hql );
		query.setString(0, project.getName()) ;
		query.setInteger(1, project.getType()) ;
		query.setString(2, project.getRemark()) ;
		query.setLong(4, project.getProjectid()) ;
		query.setString(3, project.getProjectNO());
		query.executeUpdate() ;
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		String hql = "delete from Fdapproject p where p.projectid in (:ids) " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", ids);
		query.executeUpdate() ;	
	}

	@Override
	public void deleteByOid(Long oid) {
		String hql = "delete from Fdapproject p where p.fdaporg.oid = ? ";
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, oid) ;
		query.executeUpdate() ;
	}

	@Override
	public Long queryExistsProject(String name,String oid) {
		String hql = "select count(pro.name) from Fdapproject pro where pro.name = ? and pro.fdaporg.oid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, name) ;
		query.setBigInteger(1,new BigInteger(oid));
		return (Long)query.uniqueResult() ;
	}

	@Override
	public Long queryExistsProjectNO(String projectNO) {
		String hql = "select count(pro.name) from Fdapproject pro where pro.projectNO = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, projectNO) ;
		return (Long)query.uniqueResult() ;
	}
	
}
