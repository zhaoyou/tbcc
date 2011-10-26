package org.fdap.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.dao.RefHisDao;
import org.fdap.entity.FdapRefHisData;
import org.fdap.entity.Fdapaiinfo;

/**
 * 仓库冷库历史数据实现类
 * @author zhoukuanwei
 *
 */
public class RefHisDaoImpl extends HibernateDaoSupport implements RefHisDao {

	@SuppressWarnings("unchecked")
	public List<FdapRefHisData> queryRefHisAll(final String tableName,final String startTime,
			final String endTime,final String refId) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select f.refhisId as {frh.refHisId},f.aiGuid as {frh.aiGuid},f.data as {frh.data}," +
				"f.isAlarm as {frh.isAlarm},f.time as {frh.time} from "+tableName+" f where " +
				"Time>='"+startTime+"' and Time<='"+endTime+"' and f.aiGuid in " +
				"(select fa.aiGuid from fdapaiinfo fa where fa.refId='"+refId+"') " +
				"order by f.Time,f.aiGuid";
			SQLQuery query = session.createSQLQuery(sql);
			return query.addEntity("frh", FdapRefHisData.class).list();
		}});
	}
	
	//根据冷库ID查询探头信息
	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryAiByRefId(String refId) {
		String hql="from Fdapaiinfo fa where fa.fdapref.refId='"+refId+"' order by fa.selftype";
		return this.getHibernateTemplate().find(hql);
	}
	
	/*//根据Id查询冷库信息
	public Fdapref getRefInfoById(String Id) {
		return (Fdapref)this.getHibernateTemplate().get(Fdapref.class, Id);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<BigInteger> queryRefHisCount(final String tableName,final String startTime,
			final String endTime,final String refId) {
			return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
					/*String sql = "select count(*) from "+tableName+" f where f.time>=? and f.time<=? " +*/
					String sql = "select count_big(*) from "+tableName+" f where f.time>=? and f.time<=? " +
							"and f.aiGuid in (select fa.aiGuid from fdapaiinfo fa where fa.refId='"+refId+"') " +
						"group by f.time" ;
					SQLQuery query = session.createSQLQuery(sql);
					query.setString(0, startTime) ;
					query.setString(1, endTime);
					return query.list();
				}});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<FdapRefHisData> queryRefHisData(final String tableName,final String startTime,
			final String endTime,final String refId) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String sql = "select f.refhisId as {frh.refHisId},f.aiGuid as {frh.aiGuid},f.data as {frh.data}," +
				"f.isAlarm as {frh.isAlarm},f.time as {frh.time} from "+tableName+" f where " +
						"time>='"+startTime+"' and time<='"+endTime+"' " +
						"and f.aiGuid in (select fa.aiGuid from fdapaiinfo fa where fa.refId='"+refId+"') " +
						"order by time,aiGuid" ;
				SQLQuery query = session.createSQLQuery(sql);
				return query.addEntity("frh", FdapRefHisData.class).list();
		}});
	}
	
	@SuppressWarnings("unchecked")
	public List<Date> queryTimeScope(final String tableName, final String startTime,
			final String endTime, final Integer startrow,final Integer pagesize,final String refId){
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String sql = "select f.time from "+tableName+" f where time>='"+startTime+"' and time<='"+endTime+
				"' and f.aiGuid in (select fa.aiGuid from fdapaiinfo fa where fa.refId='"+refId+"') " +
				"group by time order by time" ;
				SQLQuery query = session.createSQLQuery(sql);
				query.setFirstResult(startrow);
				query.setMaxResults(pagesize);
				return query.list();
		}});
	}
	
	@SuppressWarnings("unchecked")
	public List<Date> queryTimeScope_All(final String tableName,final String startTime,
			final String endTime, final String refId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String sql = "select f.time from "+tableName+" f where time>='"+startTime+"' and time<='"+endTime+
				"' and f.aiGuid in (select fa.aiGuid from fdapaiinfo fa where fa.refId='"+refId+"') " +
				"group by time order by time" ;
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
		}});
	}
	
}
