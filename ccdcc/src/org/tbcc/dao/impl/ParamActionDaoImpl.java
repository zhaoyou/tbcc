package org.tbcc.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ParamActionDao;
import org.tbcc.entity.config.TbccParamAction;

/**
 * 这是参数操作的数据实现类
 * @author zhaoyou
 *
 */
public class ParamActionDaoImpl extends HibernateDaoSupport implements ParamActionDao {

	public Long addAction(final String projectId,final Byte functype,final Byte cmdTYpe,final Byte optStatus ) {
		return (Long)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			SQLQuery query = session.createSQLQuery(ADDACTION) ;
			query.setString(0, projectId) ;
			query.setByte(1, functype) ;
			query.setByte(2, cmdTYpe) ;
			query.setByte(3, optStatus) ;
			return new Long(query.uniqueResult().toString()) ;
		}}) ;
	}

	public TbccParamAction getAction( Long id) {
		return (TbccParamAction)this.getHibernateTemplate().get(TbccParamAction.class, id);
	}

	public	Integer updateActionStatus( Long id, Byte optStatus) {
			Query query = this.getSession().createQuery(UPDATESTATUS_HQL);
			query.setByte(0, optStatus);
			query.setLong(1, id);
			return query.executeUpdate();	
	}

}
