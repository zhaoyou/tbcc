package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.UserRegisterDao;
import org.tbcc.entity.TbccUserRegister;
/**
 * 用户注册数据访问实现类
 * @author zhoukuanwei
 *
 */
public class UserRegisterDaoImpl extends HibernateDaoSupport implements UserRegisterDao {

	
	public boolean addregister(TbccUserRegister ruser) {
		try {
			getHibernateTemplate().save(ruser);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccUserRegister> queryUserRegisterByName(String rname ,String companyname) {
		String hql = "from TbccUserRegister where rname='"+rname+"' and companyname = '"+companyname+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
}
