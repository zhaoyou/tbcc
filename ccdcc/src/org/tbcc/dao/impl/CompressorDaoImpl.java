package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CompressorDao;
import org.tbcc.entity.cool.TbccCompressorRealData;

public class CompressorDaoImpl extends HibernateDaoSupport implements
		CompressorDao {

	@SuppressWarnings("unchecked")
	public List<TbccCompressorRealData> getByCid(final Integer cid) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select c.id as {compressor.id},c.name as {compressor.name},c.highpresAlarm as {compressor.highpresAlarm}" +
					",c.highpresState as {compressor.highpresState},c.lowpresState as {compressor.lowpresState},c.lowpresAlarm as {compressor.lowpresAlarm}" +
					",c.oilpresState as {compressor.oilpresState},c.oilpresAlarm as {compressor.oilpresAlarm}" +
					",c.exhaustValue as {compressor.exhaustValue},c.exhaustAlarm as {compressor.exhaustAlarm} " +
					",c.overloadAlarm as {compressor.overloadAlarm},c.overloadState as {compressor.overloadState} " +
					",c.activeState as {compressor.activeState} from tbcccompressorview c where c.id = "+cid ;
			SQLQuery query  = session.createSQLQuery(sql) ;
			return query.addEntity("compressor", TbccCompressorRealData.class).list();
		}}) ;
	}

	@SuppressWarnings("unchecked")
	public List<TbccCompressorRealData> getByCondition(final String str) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
	String sql = "select c.id as {compressor.id},c.name as {compressor.name},c.highpresAlarm as {compressor.highpresAlarm}" +
			",c.highpresState as {compressor.highpresState},c.lowpresState as {compressor.lowpresState},c.lowpresAlarm as {compressor.lowpresAlarm}" +
			",c.oilpresState as {compressor.oilpresState},c.oilpresAlarm as {compressor.oilpresAlarm}" +
			",c.exhaustValue as {compressor.exhaustValue},c.exhaustAlarm as {compressor.exhaustAlarm} " +
			",c.overloadAlarm as {compressor.overloadAlarm},c.overloadState as {compressor.overloadState}" +
			",c.activeState as {compressor.activeState} from tbcccompressorview c where c.id in "+str ;
	SQLQuery query  = session.createSQLQuery(sql) ;
	return query.addEntity("compressor", TbccCompressorRealData.class).list();
}}) ;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getIdsByCsId(Integer csId) {
		String hql = "select c.id from TbccCompressor c where c.tbccCompressorSet.id = "+csId ;	
		return this.getHibernateTemplate().find(hql);
	}

}
