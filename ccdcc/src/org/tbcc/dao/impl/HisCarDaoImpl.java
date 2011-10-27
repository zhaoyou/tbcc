package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisCarDao;
import org.tbcc.entity.TbccBaseHisCar;

/**
 * 这是操作移动车载历史数据的访问类
 * @author Administrator
 *
 */
public class HisCarDaoImpl extends HibernateDaoSupport implements HisCarDao {

	
	
	public List getHisCarData(String tableName, String startTime,String endTime, int value) {
		
		String sql =
		"select * from "+tableName+" where updateTime between '"+ startTime +
		"' and  '" + endTime +"'"+
		" and (unloadStorageType = 0 or (((datepart(hour, updatetime)*3600+ datepart(minute,updatetime)*60+datepart(second,updatetime)) " + 
		" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0 " +
		"and (histDataStorageType = 0 or (HistDataStorageType = -1 and histAlarmStorageType = -1 and gpsStorageType = -1 and unloadStorageType = -1)" +
		" or (HistDataStorageType = 1 and histAlarmStorageType = 1 and gpsStorageType = 1 and unloadStorageType = 1))))" +
		" order by updatetime";

		Session session = this.getSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseHisCar> getHisCarByStartUp(final String tableName,
			final Long parentId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select his.id as {car.id},his.ai1 as {car.ai1},his.ai2 as {car.ai2},his.ai3 as {car.ai3},his.ai4 as {car.ai4}," +
					"his.latitude as {car.latitude},his.latitude_dir as {car.latitude_dir},his.longitude as {car.longitude},his.longitude_dir as {car.longitude_dir}," +
					"his.parentId as {car.parentId} ,his.updateTime as {car.updateTime},his.AlarmStatus as {car.alarmStatus},his.unloadStatus as {car.unloadStatus}," +
				"his.histAlarmStorageType as {car.histAlarmStorageType},his.gpsStorageType as {car.gpsStorageType},his.histDataStorageType as {car.histDataStorageType}," +
				"his.unloadStorageType as {car.unloadStorageType}, his.histAlarmData as {car.histAlarmData} from "+tableName+"  his where parentId = "+parentId +" and" +
				" (his.gpsStorageType = 0 or (his.HistDataStorageType = -1 and his.histAlarmStorageType = -1 and his.gpsStorageType = -1 and his.unloadStorageType = -1)" +
				" or (HistDataStorageType = 1 and histAlarmStorageType = 1 and gpsStorageType = 1 and unloadStorageType = 1)) order by his.updateTime ";
			return session.createSQLQuery(sql).addEntity("car",TbccBaseHisCar.class).list();
		}});
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseHisCar> getHisCarByStartUpAndId(final String tableName,
			final Long parentId,final Integer id) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String sql = "select his.id as {car.id},his.ai1 as {car.ai1},his.ai2 as {car.ai2},his.ai3 as {car.ai3},his.ai4 as {car.ai4}," +
				"his.latitude as {car.latitude},his.latitude_dir as {car.latitude_dir},his.longitude as {car.longitude},his.longitude_dir as {car.longitude_dir}," +
				"his.parentId as {car.parentId} ,his.updateTime as {car.updateTime},his.AlarmStatus as {car.alarmStatus},his.unloadStatus as {car.unloadStatus}," +
				"his.histAlarmStorageType as {car.histAlarmStorageType},his.gpsStorageType as {car.gpsStorageType},his.histDataStorageType as {car.histDataStorageType}," +
				"his.unloadStorageType as {car.unloadStorageType}, his.histAlarmData as {car.histAlarmData} from "+tableName+"  his where parentId = "+parentId +" and id >"+id+
				" and (his.histDataStorageType = 0 or (his.HistDataStorageType = -1 and his.histAlarmStorageType = -1 and his.gpsStorageType = -1 and his.unloadStorageType = -1)" +
				" or (HistDataStorageType = 1 and histAlarmStorageType = 1 and gpsStorageType = 1 and unloadStorageType = 1))" +
				" order by his.updateTime ";
		  return session.createSQLQuery(sql).addEntity("car",TbccBaseHisCar.class).list();
	   }});
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccBaseHisCar> getHisCarDataBySidAndTime(final String tableName,
			final String startTime,final  String endTime, final int value,final  Long sid) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql =
		"select {hiscar.*} from "+tableName+" hiscar where hiscar.parentId = "+sid+" and  hiscar.updateTime  between '"+ startTime +
		"' and  '" + endTime +"'"+
		" and (unloadStorageType = 0 or (((datepart(hour, hiscar.updateTime)*3600+ datepart(minute,hiscar.updateTime)*60+datepart(second,hiscar.updateTime)) " + 
		" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0" +
		" and (hiscar.histDataStorageType = 0 or (hiscar.HistDataStorageType = -1 and hiscar.histAlarmStorageType = -1 and hiscar.gpsStorageType = -1 and hiscar.unloadStorageType = -1)" +
		" or (HistDataStorageType = 1 and histAlarmStorageType = 1 and gpsStorageType = 1 and unloadStorageType = 1))))" +
		" order by hiscar.updateTime";
			return session.createSQLQuery(sql).addEntity("hiscar",TbccBaseHisCar.class).list();
		}});
	}


}
