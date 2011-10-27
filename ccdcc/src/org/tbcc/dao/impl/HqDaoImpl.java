package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HqDao;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccHqType;

/**
 * 总部的数据访问实现类
 * @author Administrator
 *
 */
public class HqDaoImpl extends HibernateDaoSupport implements HqDao {

	public TbccHqType get(Long id) {
		return (TbccHqType)this.getHibernateTemplate().get(TbccHqType.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbccHqType> getByParentId(Long parentId) {
		String hql = "from TbccHqType hq where hq.hqParentId = "+parentId	 ;
		return this.getHibernateTemplate().find(hql) ;
	}

	@SuppressWarnings("unchecked")
	public List<TbccHqType> getAll() {
		return this.getHibernateTemplate().find("from TbccHqType");
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccBranchType> getBranchByHqId(Long hqId) {
		final String sql  = "select b.branchId as {branch.branchId},b.branchName as {branch.branchName} ,b.branchType as {branch.branchType}" +
				",b.branchCode as {branch.branchCode},b.branchRelInfo as {branch.branchRelInfo},b.branchNote as {branch.branchNote}," +
				"b.branchDisplayName as {branch.branchDisplayName},b.branchLogo as {branch.branchLogo},b.branchLogoEnable as {branch.branchLogoEnable} from  TbccBranchType b " +
				"where b.branchId in ( select BranchID from TbccHqBranchRelation where HqID = " +hqId+
		")" ;
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session arg0)
				throws HibernateException, SQLException {
			SQLQuery query  = arg0.createSQLQuery(sql) ;
			return query.addEntity("branch", TbccBranchType.class).list();
		}}) ;
	}

}
