package org.fdap.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.fdap.dao.ConfigInfoDao;
import org.fdap.entity.FdapConfigInfo;
import org.fdap.entity.Fdaporg;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ConfigInfoDaoImpl extends HibernateDaoSupport implements ConfigInfoDao {
	
	@SuppressWarnings("unchecked")
	public List<FdapConfigInfo> getConfigInfo(Long oid,Integer type) {
		/*return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select ref.name,ai.name ,ai.reid,pro.projectNO" +
					" from fdapaiinfo ai inner join fdapref ref on ai.refid = ref.refid inner join fdapproject pro on pro.projectid=ref.projectid" +
					" where pro.oid=? and pro.type=? order by ai.reid";
			SQLQuery query = session.createSQLQuery(sql);
			query.setLong(0, oid);
			query.setInteger(1, type);
			return query.list();
		}});*/
		
		String hql = "from FdapConfigInfo fci where fci.oid= ? and fci.type = ? order by fci.reid";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		query.setInteger(1, type);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Fdaporg> queryAllOrg() {
		String hql = "from Fdaporg org where org.nodetype=2";
		return this.getSession().createQuery(hql).list();
	}

}
