package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.UserConfigDao;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdaprole;
import org.fdap.entity.Fdapuser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 用户配置数据实现类
 * @author zhoukuanwei
 *
 */
public class UserConfigDaoImpl extends HibernateDaoSupport implements UserConfigDao {
	
	@SuppressWarnings("unchecked")
	public List<Fdapuser> getOrgUser(String oid) {
		String hql="from Fdapuser fu where fu.fdaporg.oid = '"+oid+"' and fu.fdaprole.rid = 3";
		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fdaporg> getAllOrg() {
		String hql="from Fdaporg";
		return this.getHibernateTemplate().find(hql);
	}

	public boolean addUser(Fdapuser fuser) {
		try{
			this.getHibernateTemplate().save(fuser);
			return true;
		}
		catch (DataAccessException e) {
			return false;
		}
	}

	public Fdaprole getRoleByRid(String rid) {
		return (Fdaprole)this.getHibernateTemplate().get(Fdaprole.class, Long.valueOf(rid));
	}

	public boolean delUser(final String[] delId) {
		try {
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("delete from Fdapuser u where u.userid = ?");
					for(int i=0;i<delId.length;i++){
						query.setParameter(0, Long.valueOf(delId[i]));
						query.executeUpdate();
					}
					return 1;
				}
			});
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	public Fdapuser getUserById(String userid) {
		return (Fdapuser)this.getHibernateTemplate().get(Fdapuser.class, Long.valueOf(userid));
	}

	public boolean updateUser(Fdapuser fuser) {
		try {
			getHibernateTemplate().merge(fuser);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace() ;
			return false;
		}
	}
	
}
