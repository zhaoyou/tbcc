package org.tbcc.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;

/**
 *  这个是启停操作的数据访问实现类
 * @author Administrator
 *
 */
public class HisStartUpDaoImpl extends HibernateDaoSupport implements
		HisStartUpDao {

	
	public List getStartUpList(String tableName,String startTime,String endTime) {
		String sql = "select Id   , BeginTime   , EndTime   , PageIndex   , PageCount  , RecordInterval   , LastPageIndex"+ 
 ", LastPageRecNum , BeginAddress , EndAddress  , Shipper , Carrier  , Receiver "+
  ", LastRecordTime , UpdateStatus,tuplimit,tdwlimit  from " +tableName+" where beginTime between '"+startTime 
		+"' and '"+endTime+"' or endTime between '"+startTime+"' and '"+endTime+"' order by beginTime";
		Session  session =  this.getSessionFactory().getCurrentSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		return query.list() ;	
	}
	
	
	public List getStartUp(String tableName, long id) {
		String sql = "select Id   , BeginTime   , EndTime   , PageIndex   , PageCount  , RecordInterval   , LastPageIndex"+ 
 ", LastPageRecNum , BeginAddress , EndAddress  , Shipper , Carrier  , Receiver "+
  ", LastRecordTime , UpdateStatus,tuplimit,tdwlimit  from "+tableName+"  where id = "+id ;
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List<TbccBaseHisStartUp> getStartUpById(String tableName, long id) {
		String sql = "select {startup.*} from "+tableName+" startup where startup.id = "+id ;
		return this.getSession().createSQLQuery(sql).addEntity("startup", TbccBaseHisStartUp.class).list() ;
	}


	@SuppressWarnings("unchecked")
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,
			String startTime, String endTime) {
		String sql = "select {startup.*} from "+tableName+" startup where startup.beginTime " +
		" between '"+startTime +"' and '"+endTime+"'" +
		" or startup.endTime between '"+startTime+"' and '"+endTime+"' order by startup.beginTime";
		return this.getSession().createSQLQuery(sql).addEntity("startup", TbccBaseHisStartUp.class).list() ;
	}


	@SuppressWarnings("unchecked")
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime) {
		String sql = "select Id from "+tableName+" where beginTime = '"+beginTime+"'";
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

}
