package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.CoolerSystemDao;
import org.tbcc.entity.cool.TbccCcapSystemRealData;

/**
 * 制冷系统数据访问实现类
 * @author Administrator
 *
 */
public class CoolerSystemDaoImpl extends HibernateDaoSupport implements
		CoolerSystemDao {

	@SuppressWarnings("unchecked")
	public List<TbccCcapSystemRealData> getByProjectIds(String str) {
		String hql = "from TbccCcapSystemRealData where projectId in"+str ;
		return this.getHibernateTemplate().find(hql);
	}

}
