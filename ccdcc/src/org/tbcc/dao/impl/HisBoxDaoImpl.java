package org.tbcc.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisBoxDao;

/**
 * 这是小批零的历史数据访问实现类
 * @author Administrator
 *
 */
public class HisBoxDaoImpl extends HibernateDaoSupport implements HisBoxDao {

	
	public List getHisBoxData(String tableName, String startTime, String endTime,
			int value) {
		String sql = "select * from "+tableName+" where updateTime between '"+ startTime +
		"' and  '" + endTime +"'"+
		" and ((datepart(hour, updatetime)*3600+ datepart(minute,updatetime)*60+datepart(second,updatetime)) " + 
		" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0";
		Session session = this.getSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		return query.list() ;
	}

}
