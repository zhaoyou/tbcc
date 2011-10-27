package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RefInfoDao;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库信息的数据访问类
 * @author Administrator
 *
 */
public class RefInfoDaoImpl extends HibernateDaoSupport implements RefInfoDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccRefInfo> getRefByProId(String projectId) {
		String hql = "from TbccRefInfo r where r.projectId = '"+projectId+"' order by r.floorType,r.floorNo,r.refType,r.realRefid " ;
		return this.getHibernateTemplate().find(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TbccRefInfo> getRef(String proId, String netId, String refId) {
		String hql = "from TbccRefInfo r where r.projectId = '"+proId+"' and r.netid = '"+netId
		+"' and r.refid = '"+refId+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
	
	public TbccRefInfo get(Long id) {	
		return (TbccRefInfo)this.getHibernateTemplate().get(TbccRefInfo.class, id);
	}

	

}
