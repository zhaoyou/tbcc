package org.fdap.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.dao.StartUpDao;
import org.fdap.entity.FdapStartUp;

/**
 * 车载启停记录数据实现类
 * @author zhoukuanwei
 *
 */
public class StartUpDaoImpl extends HibernateDaoSupport implements StartUpDao {

	@SuppressWarnings("unchecked")
	public List<FdapStartUp> queryStartUp(final String tableName,final String refid,final String startTime,
			final String endTime,final Integer startRow,final Integer pagesize) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select s.startUpId as {ss.startUpId},s.refId as {ss.refId},s.startTime as {ss.startTime}," +
					"s.endTime as {ss.endTime},s.Carrier as {ss.carrier},s.IntervalValue as {ss.intervalValue}" +
					" from " +tableName+" s where ((startTime>= ? and startTime<= ?) or (endTime>= ? and endTime<= ?)) " +
							"and refid= ? order by startTime" ;
			SQLQuery query = session.createSQLQuery(sql) ;	
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setString(4, refid);
			query.setFirstResult(startRow);
			query.setMaxResults(pagesize);
			return query.addEntity("ss", FdapStartUp.class).list();
		}});
	}

	public Integer getStartupCounts(final String tableName,final String refid,
			final String startTime, final String endTime) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) from " +tableName+" s where " +*/
			String sql = "select count_big(*) from " +tableName+" s where " +
					"((startTime>= ? and startTime<= ?) or (endTime>= ? and endTime<= ?)) and refid= ?" ;
			SQLQuery query = session.createSQLQuery(sql) ;	
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setString(4, refid);
			return query.uniqueResult();
		}})).intValue();
	}

	
	public FdapStartUp queryBySid(final String tableName,final long sid) {
		return (FdapStartUp)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select s.startUpId as {ss.startUpId},s.refId as {ss.refId},s.startTime as {ss.startTime}," +
					"s.endTime as {ss.endTime},s.Carrier as {ss.carrier},s.IntervalValue as {ss.intervalValue}" +
					" from " +tableName+" s where s.startUpId=?" ;
			SQLQuery query = session.createSQLQuery(sql) ;	
			query.setLong(0, sid);
			return query.addEntity("ss", FdapStartUp.class).uniqueResult();
		}});
	}
	
}
