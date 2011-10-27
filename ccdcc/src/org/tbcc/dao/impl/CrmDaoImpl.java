package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CrmDao;
import org.tbcc.entity.TbccCrm;

/**
 * 客户关系实现类
 * @author Administrator
 *
 */
public class CrmDaoImpl extends HibernateDaoSupport implements CrmDao {

	@SuppressWarnings("unchecked")
	public List<TbccCrm> getByHid(String hid) {
		String hql = "from TbccCrm c where c.hid = ?" ;
		return this.getHibernateTemplate().find(hql, new Object[]{hid}) ;
	}

}
