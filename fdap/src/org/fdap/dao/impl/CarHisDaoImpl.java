package org.fdap.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.dao.CarHisDao;
import org.fdap.entity.FdapCarHisData;

/**
 * 车载历史数据实现类
 * @author zhoukuanwei
 *
 */
public class CarHisDaoImpl extends HibernateDaoSupport implements CarHisDao {

	@SuppressWarnings("unchecked")
	public List<FdapCarHisData> queryCarHis(final String tableName,final Integer parentId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
	String sql = "select s.id as {fc.id},s.t1 as {fc.t1},s.t2 as {fc.t2}," +
			"s.t3 as {fc.t3},s.startUpId as {fc.startUpId},s.latitude as {fc.latitude},s.latitude_dir as {fc.latitude_dir}" +
			",s.longitude as {fc.longitude},s.longitude_dir as {fc.longitude_dir},s.time as {fc.time},s.isAlarm as {fc.isAlarm}" +
			" from " +tableName+" s where StartupId='"+parentId+"' order by Time" ;
	SQLQuery query = session.createSQLQuery(sql);
	return query.addEntity("fc", FdapCarHisData.class).list();
		}});
	}


	public Integer queryCarHisCount(final String tableName,final Integer parentId) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" where StartupId='"+parentId+"'" ;*/
			String sql = "select count_big(*) as counts from "+tableName+" where StartupId='"+parentId+"'" ;
			SQLQuery query = session.createSQLQuery(sql);
			return query.uniqueResult();
		}})).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<FdapCarHisData> queryCarHisbyStartupPage(final String tableName,
			final Integer parentId,final Integer startRow,final Integer pagesize) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select s.id as {fc.id},s.t1 as {fc.t1},s.t2 as {fc.t2}," +
				"s.t3 as {fc.t3},s.startUpId as {fc.startUpId},s.latitude as {fc.latitude},s.latitude_dir as {fc.latitude_dir}" +
				",s.longitude as {fc.longitude},s.longitude_dir as {fc.longitude_dir},s.time as {fc.time},s.isAlarm as {fc.isAlarm}" +
				" from " +tableName+" s where StartupId='"+parentId+"' order by Time" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setFirstResult(startRow);
			query.setMaxResults(pagesize);
			return query.addEntity("fc", FdapCarHisData.class).list();
		}});
	}
}
