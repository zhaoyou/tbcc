package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RealBoxDao;
import org.tbcc.entity.TbccBaseRealBox;

/**
 * ����ʵʱС��������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public class RealBoxDaoImpl extends HibernateDaoSupport implements RealBoxDao{

	
	@SuppressWarnings("unchecked")
	public List<TbccBaseRealBox> getRealboxData(String condition) {
		String hql = "from TbccBaseRealBox b where b.projectId in"+condition ;
		return this.getHibernateTemplate().find(hql);
	}
	
}
