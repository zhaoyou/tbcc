package org.tbcc.dao.impl;

import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RealRefDao;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库实时数据的数据访问类
 * @author Administrator
 *
 */
public class RealRefDaoImpl extends HibernateDaoSupport implements RealRefDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccBaseRealRef> getRealRefData(String projectId) {
		String hql = "from TbccBaseRealRef r where r.projectId = '"+projectId+"'";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFloorInfo(List<String> projects) {
		String hql = "select new map(ref.projectId as project,ref.floorNo as floorNo ,ref.floorType as floorType ) from TbccRefInfo ref where projectid in (:p) group by ref.projectId,ref.floorNo ,ref.floorType" ;
		Query query = this.getSession().createQuery(hql);	
		query.setParameterList("p", projects);	
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbccRefInfo> getRefByPidAndFloorInfo(String projectId,
			Integer floorType, Integer floorNo) {
		String hql = "from TbccRefInfo ref where ref.projectId = ? and ref.floorNo = ? and ref.floorType = ?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, projectId);
		query.setInteger(1, floorNo);
		query.setInteger(2, floorType);
		return query.list();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<TbccAiInfo> getAiByRefId(List<Long> refIds) {
		String hql = "from TbccAiInfo ai where ai.rid in (:prid)" ;
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("prid", refIds);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseRealRef> getRealRefSysData(String projectId) {
		String hql = "from TbccBaseRealRef real where real.projectId = ? order by real.neiId asc";	
		Query query = this.getSession().createQuery(hql);
		query.setString(0, projectId);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.list();
	}

}
