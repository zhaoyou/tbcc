package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CondenserDao;
import org.tbcc.entity.cool.TbccCondenserRealData;

public class CondenserDaoImpl extends HibernateDaoSupport implements
		CondenserDao {

	@SuppressWarnings("unchecked")
	public List<TbccCondenserRealData> getByCid(final Integer cid) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select c.id as {condenser.id},c.name as {condenser.name},c.pressureAlarm as {condenser.pressureAlarm}" +
					",c.pressureState as {condenser.pressureState} from tbcccondenserview c where c.id = "+cid ;
			SQLQuery query = session.createSQLQuery(sql) ;
			return query.addEntity("condenser",TbccCondenserRealData.class).list();
		}}) ;	
	}

	@SuppressWarnings("unchecked")
	public List<TbccCondenserRealData> getByCondition(final String str) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select c.id as {condenser.id},c.name as {condenser.name},c.pressureAlarm as {condenser.pressureAlarm}" +
			",c.pressureState as {condenser.pressureState} from tbcccondenserview c where c.id   in "+str ;
			SQLQuery query = session.createSQLQuery(sql) ;
			return query.addEntity("condenser",TbccCondenserRealData.class).list();
		}}) ;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getIdsByCsId(Integer csId) {
		String hql = "select c.id from TbccCondenser c where c.tbccCompressorSet.id = "+csId ;
		return this.getHibernateTemplate().find(hql);
	}
	
	

}
