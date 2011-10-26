package org.fdap.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.fdap.dao.OrgConfigDao;
import org.fdap.entity.Fdapauthcode;
import org.fdap.entity.Fdaporg;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 机构配置数据实现类
 * @author zhaoyou
 *
 */
public class OrgConfigDaoImpl extends HibernateDaoSupport implements
		OrgConfigDao {

	@SuppressWarnings("unchecked")
	public List<Fdaporg> queryTopOrg() {
		String hql = "from Fdaporg org where org.oid = 1" ;
		Query query = this.getSession().createQuery(hql) ;
		return query.list() ;
	}

	public void updateOrg(Fdaporg org) {
		String hql = "update Fdaporg org set org.account = ? ,org.email = ? , " +
				"org.name= ? ,org.telephone = ? ,org.nodetype = ? ,org.parentId = ? where org.oid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, org.getAccount());
		query.setString(1, org.getEmail()) ;
		query.setString(2, org.getName()) ;
		query.setString(3, org.getTelephone()) ;
		query.setInteger(4, org.getNodetype()) ;
		query.setLong(5, org.getParentId());
		query.setLong(6, org.getOid()) ;
		query.executeUpdate() ;
	}

	public void deleteOrg(Long oid) {
		String hql = "delete from Fdaporg org where org .oid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, oid);
		query.executeUpdate() ;	
	}

	public void insertOrg(Fdaporg org) {
		this.getHibernateTemplate().save(org) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long queryByName(String account) {
		String hql = "select count(org.name) from Fdaporg org where org.account = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, account) ;
		return (Long)query.uniqueResult() ;
	}

	@Override
	public void deleteOrgList(List<Long> orgIds) {
		String hql = "delete from Fdaporg org where org.oid in (:ids)" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", orgIds);
		query.executeUpdate() ;
	}

	@Override
	public Long queryExistsLowerOrg(List<Long> orgIds) {
		String hql = "select count(*) from Fdaporg org where org.parentId in (:ids)" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", orgIds) ;
		return (Long)query.uniqueResult();
	}

	@Override
	public void createTable(Long oid) {
		Query query  = null ;
		List<String > list =Arrays.asList(this.REFHIS,this.HISALARM,this.CARSTARTUP,this.CARHIS) ;	
		//List<String > list =Arrays.asList(this.HISALARM) ;
		for (String string : list) {
			query = this.getSession().createSQLQuery(string.replaceAll("&&", oid.toString()));
			query.executeUpdate() ;
		}
	}

	@Override
	public void dropTable(Long oid) {
		Query query = null ;
		List<String> list = Arrays.asList(this.DROP_HISALARM,this.DROP_REFHIS,this.DROP_CARHIS,this.DROP_CARSTARTUP);
		//List<String> list = Arrays.asList(this.DROP_HISALARM);
		for (String string : list) {
			query = this.getSession().createSQLQuery(string.replaceAll("&&", oid.toString())) ;
			query.executeUpdate() ;
		}
	}


	@Override
	public void insertAuthCode(Fdapauthcode authCode) {
		this.getHibernateTemplate().save(authCode) ;
	}


	@Override
	public Long queryExistsOrg(String name, String parentOid) {
		String hql="select count(org.name) from Fdaporg org where org.name= ? and org.parentId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, name);
		query.setLong(1, Long.parseLong(parentOid));
		return (Long)query.uniqueResult();
	}

	@Override
	public void delAuthCode(Long oid) {
		String hql = "delete Fdapauthcode code where code.fdaporg.oid = ?  " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, oid) ;
		query.executeUpdate() ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fdaporg> queryOrgList() {
		String hql = "from Fdaporg org where org.flag =0  and nodetype !=2 	 " ;
		Query query = this.getSession().createQuery(hql) ;
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> queryAllCode() {
		String hql = "select fac.code from Fdapauthcode fac" ;
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	
}
