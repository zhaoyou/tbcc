package org.fdap.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.fdap.dao.RefConfigDao;
import org.fdap.entity.Fdapref;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 冷库配置数据接口
 * @author zhaoyou
 *
 */
public class RefConfigDaoImpl extends HibernateDaoSupport implements
		RefConfigDao {

	@Override
	public void deleteRef(Long refId) {
		String hql = "delete Fdapref ref where ref.refId = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, refId) ;
		query.executeUpdate() ;
	}

	@Override
	public void insertRef(Fdapref ref) {
		this.getHibernateTemplate().save(ref) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fdapref> queryByProjectId(Long projectId) {
		String hql = "from Fdapref ref where ref.fdapproject.projectid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, projectId) ;
		return query.list();
	}

	@Override
	public Fdapref queryByRefId(Long refId) {
		return (Fdapref)this.getHibernateTemplate().get(Fdapref.class, refId);
	}

	@Override
	public void updateRef(Fdapref ref) {
			String hql = "update Fdapref ref set ref.fdapstoretype.storeid = ? ,ref.floorNo = ? ,ref.floorType = ?" +
					" ,ref.isactive = ? ,ref.name = ?,ref.remark = ? where ref.refId = ? 	" ;
			Query query = this.getSession().createQuery(hql) ;
			query.setInteger(0, ref.getFdapstoretype().getStoreid()) ;
			query.setInteger(1, ref.getFloorNo()) ;
			query.setInteger(2, ref.getFloorType()) ;
			query.setInteger(3, ref.getIsactive()) ;
			query.setString(4, ref.getName()) ;
			query.setString(5, ref.getRemark()) ;
			query.setLong(6, ref.getRefId()) ;
			query.executeUpdate() ;
	}

	@Override
	public void deleteRefByIds(List<Long> ids) {
		String hql = "delete Fdapref ref where ref.refId in(:ids) " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", ids);
		query.executeUpdate() ;
		
	}

	@Override
	public void deleteByOid(Long oid) {
		String sql = "delete from Fdapref  where  projectid  in (select projectid from fdapproject where oid = ? )";
		
//		String hql = "delete from Fdapref ref where ref.fdapproject.projectid in " +
//				"(select p.projectid from Fdapproject  p where p.fdaporg.oid = ?)";
		
		Query query = this.getSession().createSQLQuery(sql) ;
		query.setLong(0, oid) ;
		query.executeUpdate() ;
	}

	@Override
	public void deleteByProjectId(Long projectId) {
		String hql = "delete from Fdapref ref where ref.fdapproject.projectid = ? 	" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, projectId) ;
		query.executeUpdate() ;
	}

	@Override
	public Long queryExistsRef(String name, String projectid) {
		String hql = "select count(ref.name) from Fdapref ref where ref.name = ? and ref.fdapproject.projectid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, name) ;
		query.setBigInteger(1,new BigInteger(projectid));
		return (Long)query.uniqueResult() ;
	}

}
