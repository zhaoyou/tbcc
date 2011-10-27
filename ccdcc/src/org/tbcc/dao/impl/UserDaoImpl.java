package org.tbcc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.UserDao;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDataRoles;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqRoles;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccUser;

/**
 * 用户操作的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccUser> getByName(String name ) {
		String hql = "from TbccUser u where u.uname = '"+name+"'" ;
		return this.getHibernateTemplate().find(hql);
	}

	
	public TbccUser get(Long id) {
		return (TbccUser)this.getHibernateTemplate().get(TbccUser.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<TbccHqUser> getByHqName(String uname) {
		String hql = "from TbccHqUser u where u.uname = '"+uname+"'" ;
		return this.getHibernateTemplate().find(hql);
	}


	public TbccHqUser getHqUser(Long id) {
		return (TbccHqUser)this.getHibernateTemplate().get(TbccHqUser.class, id);
	}
	

	@SuppressWarnings("unchecked")
	public List<TbccHqUser> getHqByClientId(Long clientId) {
		String hql = "from TbccHqUser hq where hq.client.id = "+clientId	 ;
		return this.getHibernateTemplate().find(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TbccUser> getBranchByClientId(Long clientId) {
		String hql = "from TbccUser u where u.client.id="+clientId ;
		return this.getHibernateTemplate().find(hql);
	}
	
	
	/***ouyang implement starting***********************************************************/
	public boolean delUser(final Long[] id) {
		try {
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("delete from TbccUser u where u.id = ?");
					for(int i=0;i<id.length;i++){
						query.setParameter(0, id[i]);
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

	public boolean addUser(TbccUser user) {
		try {
			getHibernateTemplate().save(user);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TbccDataRoles> getDataRolesByBranchId(Long branchId) {
		try {
			List<TbccDataRoles> list = getHibernateTemplate().find("from TbccDataRoles dr where dr.branchId = ?",branchId);
			return list;
		} catch (DataAccessException e) {
			return null;
		}
	}

	public boolean upUser(TbccUser user) {
		try {
			getHibernateTemplate().merge(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace() ;
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TbccUser> getUserByClientId(Long clientId) {
		try {
			return getHibernateTemplate().find("from TbccUser u where u.client.id = ?", clientId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TbccFunction> getUserFunction(final TbccBaseUser user) {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("from TbccFunction f where f.id in ( select functionId from TbccRoleAndFunction rf where rf.roleId = ?)");
					query.setParameter(0, user.getRoleId());
					return query.list();
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public TbccDataRoles getDataRolesById(int id) {
		try {
			List<TbccDataRoles> list = getHibernateTemplate().find("from TbccDataRoles dr where dr.roleId = ?", id);
			if(list.size()==1){
				return list.get(0);
			}else{
				return null;
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TbccUser> getSysAdmin() {
		try {
			return getHibernateTemplate().find("from TbccUser u where u.dataRoles.roleName = ?", "管理员");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public TbccBranchType getBranchById(Long id) {
		try {
			List<TbccBranchType> list = getHibernateTemplate().find("from TbccBranchType bt where bt.branchId = ?", id);
			if(list.size()==1){
				return list.get(0);
			}else{
				return null;
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	
	/**
	 * hqUser
	 */
	@SuppressWarnings("unchecked")
	public TbccHqRoles getHqRoleById(Long HqRoleId) {
		try {
			List<TbccHqRoles> list = this.getHibernateTemplate().find("from TbccHqRoles hr where hr.id = ?",HqRoleId);
			if(list.size()==1){
				return list.get(0);
			}else{
				return null;
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	public boolean addHqUser(TbccHqUser user) {
		try {
			this.getHibernateTemplate().save(user);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}


	public boolean delHqUser(final Long[] id) {
		try {
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("delete from TbccHqUser u where u.id = ?");
					for(int i=0;i<id.length;i++){
						query.setParameter(0, id[i]);
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


	@SuppressWarnings("unchecked")
	public List<TbccHqRoles> getHqRolesByHqId(Long HqId) {
		try {
			List<TbccHqRoles> list = getHibernateTemplate().find("from TbccHqRoles hr where hr.hqId = ?",HqId);
			return list;
		} catch (DataAccessException e) {
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public TbccHqUser getHqUserByClientIdAndUserName(final Long clientId, final String name) {
		try {
			return  (TbccHqUser) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("from TbccHqUser u where u.client.id = ? and u.uname = ?");
					query.setParameter(0, clientId);
					query.setParameter(1,name);
					
					if(query.list().size()==1){
						return query.list().get(0);
					}else{
						return null;
					}
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean upHqUser(TbccHqUser user) {
		try {
			getHibernateTemplate().merge(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace() ;
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	public List<TbccHqUser> getHqUserbyClientId(Long clientId) {
		return getHibernateTemplate().find("from TbccHqUser u where u.client.id = ?", clientId);
	}


	@SuppressWarnings("unchecked")
	public List<TbccFunction> getHqUserFunction(final TbccBaseUser user, final Long branchId) {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query = session.createQuery("from TbccFunction f where f.id in (select rf.functionId from TbccRoleAndFunction rf where rf.roleId = (select d.roleId from TbccDataRoles d where d.branchId = ? and d.roleId in (select r.branchRoleId from TbccHqBranchRolesRelation r where r.hqRoleId = ?)))");
					query.setParameter(0, branchId);
					query.setParameter(1,user.getRoleId().longValue());
					
					return query.list();
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public TbccHqUser getHqUserById(Long id) {
		return (TbccHqUser) this.getHibernateTemplate().get(TbccHqUser.class, id);
	}

	/***ouyang implement ended ***********************************************************/

}
