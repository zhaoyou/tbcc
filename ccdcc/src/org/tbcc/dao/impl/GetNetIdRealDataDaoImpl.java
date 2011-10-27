package org.tbcc.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.IgetNetIdRealDataDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;

public class GetNetIdRealDataDaoImpl  extends HibernateDaoSupport implements IgetNetIdRealDataDao{
	
	
	public TbccBaseRealRef getRealData(final TbccImageControls control) {
		
		return (TbccBaseRealRef) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery("from TbccBaseRealRef r where r.projectId = ? and r.neiId = ?");
				query.setParameter(0, control.getProjectId());
				query.setParameter(1, control.getNetid());
				
				if(query.list().size()==0)return null;
				return query.list().get(0);
			}

		});
	}
	
}
