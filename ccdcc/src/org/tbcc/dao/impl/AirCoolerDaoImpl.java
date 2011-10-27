package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.AirCoolerDao;
import org.tbcc.entity.cool.TbccAirCoolerRealData;

public class AirCoolerDaoImpl extends HibernateDaoSupport implements
		AirCoolerDao {

	@SuppressWarnings("unchecked")
	public List<TbccAirCoolerRealData> getByCid(final Integer cid) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select c.id as {cool.id},c.name as {cool.name},c.defrostAlarm as {cool.defrostAlarm} " +
			",c.defrostState as {cool.defrostState} from tbccaircoolerview c where c.id = "+cid ;
			SQLQuery query = session.createSQLQuery(sql) ;
			return query.addEntity("cool", TbccAirCoolerRealData.class).list() ;	
		}}) ;
	}

	@SuppressWarnings("unchecked")
	public List<TbccAirCoolerRealData> getByCondition(final String str) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select c.id as {cool.id},c.name as {cool.name},c.defrostAlarm as {cool.defrostAlarm} " +
			",c.defrostState as {cool.defrostState} from tbccaircoolerview c where c.id in "+str ;	
			SQLQuery query = session.createSQLQuery(sql) ;
			return query.addEntity("cool", TbccAirCoolerRealData.class).list() ;	
		}}) ;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getIdsByCsId(Integer csId) {
		String hql = "select ac.id from TbccAirCooler ac where ac.tbccCompressorSet.id ="+csId ;
		return this.getHibernateTemplate().find(hql);
	}

}
