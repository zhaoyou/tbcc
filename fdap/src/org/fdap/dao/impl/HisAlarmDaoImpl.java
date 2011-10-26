package org.fdap.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.fdap.dao.HisAlarmDao;
import org.fdap.entity.FdapHisAlarm;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapref;

/**
 * 历史报警数据实现类
 * @author zhoukuanwei
 *
 */
public class HisAlarmDaoImpl extends HibernateDaoSupport implements HisAlarmDao {
	//查询某段时间内历史报警记录
	@SuppressWarnings("unchecked")
	public List<FdapHisAlarm> queryHisAlarm(final String tableName,final String startTime,
			final String endTime,final Integer startrow,final Integer pagesize,final String[] aiIds) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
			String sql = "select s.hisAlarmId as {fh.hisAlarmId},s.aiGuid as {fh.fdapaiinfo},s.alarmData as {fh.alarmData}," +
				"s.startTime as {fh.startTime},s.endTime as {fh.endTime},s.alarmLevel as {fh.alarmLevel},s.alarmType as {fh.alarmType}" +
				",s.alarmStandard as {fh.alarmStandard},s.flag as {fh.flag} from " +tableName+" s where ((startTime>=? " +
				"and startTime<=?) or (endTime>=? and endTime<=? )) and s.aiGuid in (:ais) and s.flag=1 " +
				"order by s.alarmLevel" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setParameterList("ais", aiIds);
			query.setFirstResult(startrow);
			query.setMaxResults(pagesize);
			return query.addEntity("fh", FdapHisAlarm.class).list();
		}});
	}
	
	//查询某企业下的所有探头信息
	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryAiinfoByOid(String oid) {
		String hql = "from Fdapaiinfo fa where fa.fdapref.fdapproject.fdaporg.oid='"+oid+"' order by fa.reid";
		return this.getHibernateTemplate().find(hql);
	}
	
	//根据工程类型，查询某企业下的所有探头信息
	@SuppressWarnings("unchecked")
	public List<Fdapaiinfo> queryAiinfoByOidAndPType(String oid,
			String projectType) {
		String hql = "from Fdapaiinfo fa where fa.fdapref.fdapproject.fdaporg.oid='"+oid
		+"' and fa.fdapref.fdapproject.type='"+projectType+"' order by fa.reid";
		return this.getHibernateTemplate().find(hql);
	}

	//查询某企业下的所有冷库信息
	@SuppressWarnings("unchecked")
	public List<Fdapref> queryRefByOid(String oid) {
		String hql = "from Fdapref fr where fr.fdapproject.fdaporg.oid='"+oid+"'";
		return this.getHibernateTemplate().find(hql);
	}
	//查询某段时间内历史报警的总记录数
	public Integer queryHisAlarmCount(final String tableName,final String startTime,
			final String endTime,final String[] aiIds) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" s where " +*/
			String sql = "select count_big(*) as counts from "+tableName+" s where " +
					"startTime>='"+startTime+"' and (startTime<='"+endTime+"' or " +
					"endTime>='"+startTime+"' and endTime<='"+endTime+"') and s.flag=1 and aiGuid in (:ais)" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameterList("ais", aiIds);
			return query.uniqueResult();
		}})).intValue();
	}
	
	//根据探头类型查询某段时间内历史报警记录
	@SuppressWarnings("unchecked")
	public List<FdapHisAlarm> queryHisAlarmByAiType(final String tableName,final String startTime,
			final String endTime,final Integer startrow,final Integer pagesize,final String aiType,final String[] aiIds) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
			String sql = "select s.hisAlarmId as {fh.hisAlarmId},s.aiGuid as {fh.fdapaiinfo},s.alarmData as {fh.alarmData}," +
				"s.startTime as {fh.startTime},s.endTime as {fh.endTime},s.alarmLevel as {fh.alarmLevel},s.alarmType as {fh.alarmType}" +
				",s.alarmStandard as {fh.alarmStandard},s.flag as {fh.flag} from " +tableName+" s where (startTime>=? " +
				"and startTime<=? or endTime>=? and endTime<=?) and s.flag=1 " +
				"and s.aiGuid in(select a.aiGuid from fdapaiinfo a where a.selftype=? and a.aiGuid in(:ais)) order by s.alarmLevel";
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setString(4, aiType);
			query.setParameterList("ais", aiIds);
			query.setFirstResult(startrow);
			query.setMaxResults(pagesize);
			return query.addEntity("fh", FdapHisAlarm.class).list();
		}});
	}
	
	//根据探头类型查询某段时间内历史报警的总记录数
	public Integer queryHisAlarmCountByAiType(final String tableName,final String startTime,
			final String endTime,final String aiType,final String[] aiIds) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" s where " +*/
			String sql = "select count_big(*) as counts from "+tableName+" s where " +
					"(s.startTime>='"+startTime+"' and s.startTime<='"+endTime+"' or " +
					"s.endTime>='"+startTime+"' and s.endTime<='"+endTime+"') and " +
					"s.aiGuid in(select a.aiGuid from fdapaiinfo a where a.selftype='"+aiType+
					"' and s.flag=1 and s.aiGuid in (:ais))" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameterList("ais", aiIds);
			return query.uniqueResult();
		}})).intValue();
	}
	
	//根据报警级别查询某段时间内历史报警记录
	@SuppressWarnings("unchecked")
	public List<FdapHisAlarm> queryHisAlarmByAlarmLevel(final String tableName,final String startTime,
			final String endTime,final Integer startrow,final Integer pagesize,final String alarmLevel,final String[] aiIds) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
			String sql = "select s.hisAlarmId as {fh.hisAlarmId},s.aiGuid as {fh.fdapaiinfo},s.alarmData as {fh.alarmData}," +
				"s.startTime as {fh.startTime},s.endTime as {fh.endTime},s.alarmLevel as {fh.alarmLevel},s.alarmType as {fh.alarmType}" +
				",s.alarmStandard as {fh.alarmStandard},s.flag as {fh.flag} from " +tableName+" s where (startTime>=? " +
				"and startTime<=? or endTime>=? and endTime<=?) and s.alarmLevel=? and s.flag=1 " +
				"and s.aiGuid in (:ais) order by s.alarmLevel";
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setString(4, alarmLevel);
			query.setParameterList("ais", aiIds);
			query.setFirstResult(startrow);
			query.setMaxResults(pagesize);
			return query.addEntity("fh", FdapHisAlarm.class).list();
		}});
	}
	
	//根据报警级别查询某段时间内历史报警的总记录数
	public Integer queryHisAlarmCountByAlarmLevel(final String tableName,final String startTime,
			final String endTime,final String alarmLevel,final String[] aiIds) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" s where " +*/
			String sql = "select count_big(*) as counts from "+tableName+" s where " +
					"(s.startTime>='"+startTime+"' and s.startTime<='"+endTime+"' or " +
					"s.endTime>='"+startTime+"' and s.endTime<='"+endTime+"') and s.flag=1 and " +
					"s.alarmLevel='"+alarmLevel+"' and s.aiGuid in (:ais)" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameterList("ais", aiIds);
			return query.uniqueResult();
		}})).intValue();
	}
	
	
	//根据探头类型和报警级别，查询某段时间内历史报警记录
	@SuppressWarnings("unchecked")
	public List<FdapHisAlarm> queryHisAlarmByAiTypeAndLevel(final String tableName,final String startTime,
			final String endTime,final Integer startrow,final Integer pagesize,final String aiType,final String alarmLevel,final String[] aiIds) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
			String sql = "select s.hisAlarmId as {fh.hisAlarmId},s.aiGuid as {fh.fdapaiinfo},s.alarmData as {fh.alarmData}," +
				"s.startTime as {fh.startTime},s.endTime as {fh.endTime},s.alarmLevel as {fh.alarmLevel},s.alarmType as {fh.alarmType}" +
				",s.alarmStandard as {fh.alarmStandard},s.flag as {fh.flag} from " +tableName+" s where (startTime>=? " +
				"and startTime<=? or endTime>=? and endTime<=?) and s.alarmLevel=? and s.flag=1 " +
				"and s.aiGuid in(select a.aiGuid from fdapaiinfo a where a.selftype= ? and a.aiGuid in (:ais)) order by s.alarmLevel";
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, startTime);
			query.setString(3, endTime);
			query.setString(4, alarmLevel);
			query.setString(5, aiType);
			query.setParameterList("ais", aiIds);
			query.setFirstResult(startrow);
			query.setMaxResults(pagesize);
			return query.addEntity("fh", FdapHisAlarm.class).list();
		}});
	}
	
	//根据探头类型和报警级别，查询某段时间内历史报警的总记录数
	public Integer queryHisAlarmCountByAiTypeAndLevel(final String tableName,final String startTime,
			final String endTime,final String aiType,final String alarmLevel,final String[] aiIds) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" s where " +*/
			String sql = "select count_big(*) as counts from "+tableName+" s where " +
					"(s.startTime>='"+startTime+"' and s.startTime<='"+endTime+"' or " +
					"s.endTime>='"+startTime+"' and s.endTime<='"+endTime+"') and " +
					"s.alarmLevel='"+alarmLevel+"' and s.flag=1 and " +
					"s.aiGuid in(select a.aiGuid from fdapaiinfo a where a.selftype='"+aiType+"' and a.aiGuid in (:ais))" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameterList("ais", aiIds);
			return query.uniqueResult();
		}})).intValue();
	}

	public Long queryAlarmCounts(final String tableName) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" s where s.flag=1";*/
			String sql = "select count_big(*) as counts from "+tableName+" s where s.flag=1";
			SQLQuery query = session.createSQLQuery(sql);
			return query.uniqueResult();
		}})).longValue();
	}

	public Long queryAlarmCountsByLevel(final String tableName,final Integer alarmLevel) {
		return ((BigInteger)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//mysql数据库count返回值的是bigint类型,但是sqlserver返回值的是int类型
			/*String sql = "select count(*) as counts from "+tableName+" hisalarm where hisalarm.flag=1 and hisalarm.alarmLevel = "+alarmLevel;*/
			String sql = "select count_big(*) as counts from "+tableName+" hisalarm where hisalarm.flag=1 and hisalarm.alarmLevel = "+alarmLevel;
			SQLQuery query = session.createSQLQuery(sql);
			return query.uniqueResult();
		}})).longValue();
	}
	
}
