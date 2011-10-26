package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.AiConfigDao;
import org.fdap.entity.Fdapaiinfo;
import org.hibernate.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 探头配置数据接口
 * @author zhaoyou
 *
 */
public class AiConfigDaoImpl extends HibernateDaoSupport implements AiConfigDao {

	public void deleteAi(String aiGuid) throws Exception {
			String hql = "delete from Fdapaiinfo ai where ai.aiguid = ?" ;
			Query query = this.getSession().createQuery(hql) ;
			query.setString(0, aiGuid) ;
			query.executeUpdate() ;
	}

	public boolean insertAi(Fdapaiinfo aiInfo) {
		try{
			this.getHibernateTemplate().save(aiInfo) ;
			return true;
		}
		catch (DataAccessException e) {
			return false;
		}
	}

	public Fdapaiinfo queryByAiguid(String aiGuid) {
		return (Fdapaiinfo)this.getHibernateTemplate().get(Fdapaiinfo.class, aiGuid);
	}

	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryByRefId(Long refId) {
		String hql = "from Fdapaiinfo ai where ai.fdapref.refId = ? " ;
		Query query  = this.getSession().createQuery(hql) ;
		query.setLong(0, refId) ;
		return query.list();
	}
	
	public void updateAi(Fdapaiinfo aiInfo) throws Exception {
		
		String hql = "update Fdapaiinfo ai set ai.highdelay = ? ,ai.highlimit = ?,ai.lowerdelay = ? ,ai.lowerlimit = ? ," +
				" ai.name=?,ai.selftype = ? where " +
				"ai.aiguid = ?	" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setInteger(0, aiInfo.getHighdelay()) ;
		query.setDouble(1, aiInfo.getHighlimit()) ;
		query.setInteger(2, aiInfo.getLowerdelay()) ;
		query.setDouble(3, aiInfo.getLowerlimit()) ;
		query.setString(4, aiInfo.getName()) ;
		query.setInteger(5, aiInfo.getSelftype()) ;
		query.setString(6, aiInfo.getAiguid()) ;
		query.executeUpdate() ;
	}
	
	
	/*public void updateHumAi(Fdapaiinfo aiInfo) throws Exception {
		
		String hql = "update Fdapaiinfo ai set ai.highdelay = ? ,ai.highlimit = ?,ai.lowerdelay = ? ,ai.lowerlimit = ? ," +
				" ai.name=?,ai.selftype = ? where " +
				"ai.aiguid = ?	" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setInteger(0, aiInfo.getHighdelay()) ;
		query.setDouble(1, aiInfo.getHighlimit()) ;
		query.setInteger(2, aiInfo.getLowerdelay()) ;
		query.setDouble(3, aiInfo.getLowerlimit()) ;
		query.setString(4, aiInfo.getName()) ;
		query.setInteger(5, aiInfo.getSelftype()) ;
		query.setString(6, aiInfo.getAiguid()) ;
		query.executeUpdate() ;
	}*/
	
	/*public void updateTempAi(Fdapaiinfo aiInfo) throws Exception {
		
		String hql = "update Fdapaiinfo ai set ai.highdelay = ? ,ai.highlimit = ?,ai.lowerdelay = ? ,ai.lowerlimit = ? ," +
				" ai.maxhighdelay = ?,ai.maxhighlimit = ?,ai.minlowerdelay = ?,ai.minlowerlimit = ?,ai.name=?,ai.selftype = ? where " +
				"ai.aiguid = ?	" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setInteger(0, aiInfo.getHighdelay()) ;
		query.setDouble(1, aiInfo.getHighlimit()) ;
		query.setInteger(2, aiInfo.getLowerdelay()) ;
		query.setDouble(3, aiInfo.getLowerlimit()) ;
		query.setInteger(4, aiInfo.getMaxhighdelay()) ;
		query.setDouble(5, aiInfo.getMaxhighlimit()) ;
		query.setInteger(6, aiInfo.getMinlowerdelay());
		query.setDouble(7, aiInfo.getMinlowerlimit());
		query.setString(8, aiInfo.getName()) ;
		query.setInteger(9, aiInfo.getSelftype()) ;
		query.setString(10, aiInfo.getAiguid()) ;
		query.executeUpdate() ;
	}*/

	public Integer queryMaxReid(Long oid ,Integer preid) {
		String hql = "select max(reid) from Fdapaiinfo ai where ai.fdapref.fdapproject.fdaporg.oid = ? and ai.reid<>?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		query.setInteger(1, preid);
		return (Integer)query.uniqueResult();
		/*return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select max(reid) from fdapaiinfo" ;
			SQLQuery query = session.createSQLQuery(sql);
			return query.uniqueResult();
		}});*/
	}

	@Override
	public void deleteAiByOid(Long oid) {
		String sql = "delete from fdapaiinfo  where refId in " +
				" (select refid from fdapref where projectid in (select projectid from fdapproject where oid = ?))" ;
		
//		String hql = "delete from Fdapaiinfo ai where ai.fdapref.refId in " +
//				"(select r.refId from Fdapref r where r.fdapproject.projectid in (select p.projectid from Fdapproject where p.fdaporg.oid = ?))";
		
		Query query = this.getSession().createSQLQuery(sql);	
		query.setLong(0, oid);
		query.executeUpdate() ;
	}

	@Override
	public void deleteAiByProjectId(Long projectId) {
		String sql = "delete from Fdapaiinfo  where refId in " +
				"(select refid from fdapref where projectid = ?)" ;
		
//		String hql = "delete from Fdapaiinfo ai where ai.fdapref.refId in " +
//				"(select r.refId from   Fdapref r where r.fdapproject.projectid = ? )";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setLong(0, projectId );
		query.executeUpdate() ;
	}

	@Override
	public void deleteAiByRefId(Long refId) {
			String hql = "delete from Fdapaiinfo ai  where ai.fdapref.refId = ? " ;
			Query query = this.getSession().createQuery(hql) ;
			query.setLong(0, refId) ;
			query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryAiByOid(Long oid) {
		String hql = "from Fdapaiinfo ai where ai.fdapref.fdapproject.fdaporg.oid = ?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		return query.list();
	}
	
}
