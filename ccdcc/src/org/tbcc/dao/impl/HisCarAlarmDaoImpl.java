package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisCarAlarmDao;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisCarAlarm;

public class HisCarAlarmDaoImpl extends HibernateDaoSupport implements HisCarAlarmDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccBaseHisCar> getHisCarAlarmBySidAndTime(final String tableName,
			final String startTime,final String endTime,final Long sid) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql =
		"select {hiscar.*} from "+tableName+" hiscar where hiscar.parentId = "+sid+" and  hiscar.updateTime  between '"+ startTime +
		"' and  '" + endTime +"' and (hiscar.histAlarmStorageType = 0 or (hiscar.HistDataStorageType = -1 and hiscar.histAlarmStorageType = -1 " +
		" and hiscar.gpsStorageType = -1 and hiscar.unloadStorageType = -1) or (hiscar.HistDataStorageType = 1 and hiscar.histAlarmStorageType = 1 " +
		" and hiscar.gpsStorageType = 1 and hiscar.unloadStorageType = 1))" +
		" order by hiscar.updateTime";
			return session.createSQLQuery(sql).addEntity("hiscar",TbccBaseHisCar.class).list();
		}});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TbccBaseHisCarAlarm> getHisAlarm(final String projectId,final Long sid) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql =
		"select {caralarm.*} from TbccHistCarAlarm caralarm where caralarm.parentId = "+sid+
		" and caralarm.projectId = '"+projectId+"' order by caralarm.startTime";
			return session.createSQLQuery(sql).addEntity("caralarm",TbccBaseHisCarAlarm.class).list();
		}});
	}

	
	public boolean insertHisAlarm(TbccBaseHisCarAlarm caralarm) {
		try {
			this.getHibernateTemplate().save(caralarm);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean updateHisAlarm(Long id, String cause, String measure,
			String dutier, String remark) {
			String hql="update TbccBaseHisCarAlarm set cause='"+cause+"',measure='"+measure+"',dutier='"+dutier+"',remark='"+remark+"' where id="+id;
			int i=this.getSession().createQuery(hql).executeUpdate();
			if(i==1){
				return true;
			}else{
				return false;
			}
	}
	
}
