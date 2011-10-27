package org.tbcc.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.DevTypeDao;
import org.tbcc.entity.TbccDevType;

/**
 * �豸����ʵ����
 * @author zhaoyou
 *
 */
public class DevTypeDaoImpl extends HibernateDaoSupport implements DevTypeDao {

	public TbccDevType getById(Long id) {
		return (TbccDevType)this.getHibernateTemplate().get(TbccDevType.class, id);
	}

}
