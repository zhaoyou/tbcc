package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisRefDao;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;
/**
 * 这是冷库的历史数据访问实现类
 * @author Administrator
 *
 */
public class HisRefDaoImpl extends HibernateDaoSupport implements HisRefDao {

	
	public List getHisRefData(String tableName, String startTime,
			String endTime, int value) {
			String sql =
			"select hDate,AI1,AI2,AI3,AI4,AI5,AI6,AI7,AI8,AI9,AI10,AI11,AI12,Ref1_RefAlarmState,Ref2_RefAlarmState,Ref3_RefAlarmState,Ref4_RefAlarmState from "+tableName+" where hDate between '"+ startTime +
			"' and  '" + endTime +"'"+
			" and ((datepart(hour, hDate)*3600+ datepart(minute,hDate)*60+datepart(second,hDate)) " + 
			" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0";
			Session session = this.getSession() ;
			SQLQuery query = session.createSQLQuery(sql);			
			return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TbccBaseHisRef> getHisDataByTime(String tableName,String startTime,String endTime,int value){
		final String hql = "select his.id as {hisref.id} ,his.hDate as {hisref.hdate},his.updateTime as {hisref.updateTime}," +
				" his.ai1 as {hisref.ai1},his.ai2 as {hisref.ai2},his.ai3 as {hisref.ai3},his.ai4 as {hisref.ai4}," +
				" his.ai5 as {hisref.ai5},his.ai6 as {hisref.ai6},his.ai7 as {hisref.ai7},his.ai8 as {hisref.ai8}, " +
				" his.ai9 as {hisref.ai9},his.ai10 as {hisref.ai10},his.ai11 as {hisref.ai11},his.ai12 as {hisref.ai12} ," +
				" his.Ref1_RefAlarmState as {hisref.alarmStatus_ref1},his.Ref2_RefAlarmState as {hisref.alarmStatus_ref2}, " +
				" his.Ref3_RefAlarmState as {hisref.alarmStatus_ref3},his.Ref4_RefAlarmState as {hisref.alarmStatus_ref4} " +
				" from  "+tableName+" his where  his.hDate  between '"+ startTime +
			"' and  '" + endTime +"'  and ((datepart(hour, his.hDate)*3600+ datepart(minute,his.hDate)*60+datepart(second,his.hDate)) " + 
			" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0";
		
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			return session.createSQLQuery(hql).addEntity("hisref",TbccBaseHisRef.class).list();		
		}}) ;	
		
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseHisRef_Ex> getExHisDataByTime(String tableName,
			String startTime, String endTime, int value) {
		
		String hql = "select ex.id as {ex.id} ,ex.hDate as {ex.hdate},ex.updateTime as {ex.updateTime}," +
		" ex.ai1 as {ex.ai1},ex.ai2 as {ex.ai2},ex.ai3 as {ex.ai3},ex.ai4 as {ex.ai4}," +
		" ex.ai5 as {ex.ai5},ex.ai6 as {ex.ai6},ex.ai7 as {ex.ai7},ex.ai8 as {ex.ai8}, " +
		" ex.ai9 as {ex.ai9},ex.ai10 as {ex.ai10},ex.ai11 as {ex.ai11},ex.ai12 as {ex.ai12} ," +
		" ex.ai13 as {ex.ai13},ex.ai14 as {ex.ai14}, ex.ai15 as {ex.ai15},ex.ai16 as {ex.ai16}," +
		" ex.ai17 as {ex.ai17},ex.ai18 as {ex.ai18}, ex.ai19 as {ex.ai19},ex.ai20 as {ex.ai20},ex.ai21 as {ex.ai21}," +
		" ex.ai22 as {ex.ai22} ,ex.ai23 as {ex.ai23},ex.ai24 as {ex.ai24},ex.ai25 as {ex.ai25},ex.ai26 as {ex.ai26}," +
		" ex.ai27 as {ex.ai27},ex.ai28 as {ex.ai28}, ex.ai29 as {ex.ai29},ex.ai30 as {ex.ai30},ex.ai31 as {ex.ai31},ex.ai32 as {ex.ai32} ," +
		" ex.Ref1_RefAlarmState as {ex.ref1_RefAlarmState},ex.Ref2_RefAlarmState as {ex.ref2_RefAlarmState}, " +
		" ex.Ref3_RefAlarmState as {ex.ref3_RefAlarmState},ex.Ref4_RefAlarmState as {ex.ref4_RefAlarmState}, " +
		" ex.Ref5_RefAlarmState as {ex.ref5_RefAlarmState},ex.Ref6_RefAlarmState as {ex.ref6_RefAlarmState}, " +
		" ex.Ref7_RefAlarmState as {ex.ref7_RefAlarmState},ex.Ref8_RefAlarmState as {ex.ref8_RefAlarmState}, " +
		" ex.Ref9_RefAlarmState as {ex.ref9_RefAlarmState},ex.Ref10_RefAlarmState as {ex.ref10_RefAlarmState}, " +
		" ex.Ref11_RefAlarmState as {ex.ref11_RefAlarmState},ex.Ref12_RefAlarmState as {ex.ref12_RefAlarmState}, " +
		" ex.Ref13_RefAlarmState as {ex.ref13_RefAlarmState},ex.Ref14_RefAlarmState as {ex.ref14_RefAlarmState}, " +
		" ex.Ref15_RefAlarmState as {ex.ref15_RefAlarmState},ex.Ref16_RefAlarmState as {ex.ref16_RefAlarmState} " +
				" from " +tableName+" ex where  ex.hDate  between '"+ startTime +
			"' and  '" + endTime +"'  and ((datepart(hour, ex.hDate)*3600+ datepart(minute,ex.hDate)*60+datepart(second,ex.hDate)) " + 
			" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0";
		Session session = this.getSession() ;
		SQLQuery query = session.createSQLQuery(hql);
		return query.addEntity("ex",TbccBaseHisRef_Ex.class).list() ;
	}

}
