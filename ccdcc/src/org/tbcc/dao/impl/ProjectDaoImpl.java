package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ProjectDao;
import org.tbcc.entity.TbccPrjType;


/**
 * 项目操作数据访问类
 * @author Administrator
 *
 */
public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getByBranchId(final Long branchId) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session arg0)
				throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId= "+branchId+" ) ";  
			SQLQuery query = arg0.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
		
	}
	
	
	public TbccPrjType get(String projectId) {
		return (TbccPrjType)this.getHibernateTemplate().get(TbccPrjType.class, projectId);
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getRefProjList(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where (p.projectType = 1 or p.projectType = 5 or p.projectType = 7) and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") ";  
			SQLQuery query = session.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getBoxProjList(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where p.projectType = 3 and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") ";  
			SQLQuery query = session.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
	}


	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getCarProjList(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where p.projectType = 2 and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") order by p.projectCode desc ";  
			SQLQuery query = session.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String>		getCarProjectIds(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = 
				"select p.projectId from TbccPrjType p where (p.projectType = 2) and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+")  order by p.projectCode desc" ;
			return session.createSQLQuery(sql).list();
		}});
	}


	@SuppressWarnings("unchecked")
	public List<String> getRefProjectIds(final Long branchId) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select p.projectId from TbccPrjType p where (p.projectType = 1 or p.projectType = 5 or p.projectType = 7 ) and  p.projectId in " +
			"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") " ;
			return session.createSQLQuery(sql).list();
		}});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getHasImagesProjList(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where (p.projectType = 1 or p.projectType = 5 or p.projectType = 7) and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") and "+
				"p.projectId in (select img.projectId from TbccProjectImages img)";  
			SQLQuery query = session.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
	}


	@SuppressWarnings("unchecked")
	public List<TbccPrjType> getCoolerProjList(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			String sql = "select * from TbccPrjType p where (p.projectType = 1 or p.projectType = 4 or p.projectType=5 or p.projectType=6 or p.projectType=7) and  p.projectId in " +
				"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") ";  
			SQLQuery query = session.createSQLQuery(sql) ;
			query.addEntity(TbccPrjType.class) ;
			return query.list() ;
		}
		})  ;
	}


	@SuppressWarnings("unchecked")
	public List<String> getCoolerProjectIds(final Long branchId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
			//String sql = "select r.projectId from TbccBranchPrjRelation r where r.branchId =  "+branchId ;
			  String sql = 
			"select p.projectId from TbccPrjType p where (p.projectType = 1 or p.projectType = 4 or p.projectType=5 or p.projectType=6 or p.projectType=7) and  p.projectId in " +
			"(select bpr.projectId  from TbccBranchPrjRelation bpr where bpr.branchId="+branchId+") "; 
			  
			return session.createSQLQuery(sql).list();
		}});
	}


	



}
