package org.tbcc.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.BranchUserAlarmLogsDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchUserAlarmLogs;
import org.tbcc.util.MyUtil;

public class BranchUserAlarmLogsDaoImpl extends HibernateDaoSupport implements BranchUserAlarmLogsDao {

	public void insertLogs(TbccBranchUserAlarmLogs logs) {
			this.getHibernateTemplate().save(logs);
	}

	public void updateLogs(TbccBranchUserAlarmLogs logs) {
		String hql = "update TbccBranchUserAlarmLogs logs set logs.logoutTime = ? ,logs.logoutAlarmState = ? where " +
				"logs.clientName = ? and logs.userName = ? and logs.loginTime = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, MyUtil.getToString_hao(logs.getLogoutTime())) ;
		query.setInteger(1, logs.getLogoutAlarmState());
		query.setString(2, logs.getClientName()) ;
		query.setString(3, logs.getUserName()) ;
		query.setString(4, MyUtil.getToString_hao(logs.getLoginTime())) ;
		query.executeUpdate() ;
	}

	public Integer getAllNumber(String clientName, String userName) {
		String hql = "select count(*) from TbccBranchUserAlarmLogs logs where logs.clientName = ? and logs.userName = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, clientName) ;
		query.setString(1, userName) ;
		return (Integer)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,
			String userName, Integer index, Integer number) {
		String hql = "from TbccBranchUserAlarmLogs logs where logs.clientName = ? and logs.userName = ? " +
				"order by logs.loginTime  " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, clientName) ;
		query.setString(1, userName) ;
		query.setFirstResult((index-1)*number);
		query.setMaxResults(number) ;
		return query.list();
	}

	

}
