package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.IImgDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;
import org.tbcc.entity.TbccProjectImages;

public class ImgDaoImpl extends HibernateDaoSupport implements IImgDao {

	@SuppressWarnings("unchecked")
	public List<TbccProjectImages> findImageByProjectId(String projectId) {
		 return  this.getHibernateTemplate().find("from TbccProjectImages t where t.projectId = ?",projectId);
	}

	
	@SuppressWarnings("unchecked")
	public List<TbccImageControls> findImageControlsByImgID(final long imageId,final String projectId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from TbccImageControls t where t.imageId = ? " +
						"and t.projectId=?");
				query.setParameter(0, imageId);
				query.setParameter(1, projectId);
				if(query.list().size()==0)return null;
				return query.list();
			}
		});
		//return hibernateTemplate.find("from TbccImageControls t where t.imageId = ?", imageId);
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseRealRef> getImageAllControlRealData(final long imageId, final String projectId) {
		return  (List<TbccBaseRealRef>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final Query query = session
						.createQuery("from TbccBaseRealRef r where r.projectId = ? and " +
								"r.neiId in (select t.netid from TbccImageControls t where t.imageId = ? and t.projectId = ? )");
				query.setParameter(0, projectId);
				query.setParameter(1, imageId);
				query.setParameter(2, projectId);
				
				if(query.list().size()==0)return null;
				return query.list();
			}
		});
	}
	
//	public String findControlNameByImageControl(final TbccImageControls control) {
//		TbccImageControls controls = (TbccImageControls) this.getHibernateTemplate().execute(new HibernateCallback(){
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Query query = session.createQuery("from TbccImageControls f where f.projectId = ? and " +
//						"f.netid = ? and f.refid = ? and f.portNo = ? and f.imageId=? and (f.dataType=1 or f.dataType=2)" );
//				query.setParameter(0, control.getProjectId());
//				query.setParameter(1, control.getNetid());
//				query.setParameter(2, control.getRefid());
//				query.setParameter(3, control.getPortNo());
//				query.setParameter(4, control.getImageId());
//				
//				//System.out.println(query.list().size());
//				
//				if(query.list().size()==0)return null;
//				return query.list().get(0);
//			}
//			
//		});
//		if(controls==null) return null;
//		return controls.getCtop()+"-"+controls.getCleft()+"-"+ controls.getCheight()+"-"+controls.getCwidth()+"-"+controls.getTitleMsg()+"-"+controls.getUnitMsg();
//	}
	
	public String getImagePathByImgID(long imageId) {
		TbccProjectImages temp = (TbccProjectImages) this.getHibernateTemplate().get(TbccProjectImages.class, imageId);
		if(temp==null) return null;
		return temp.getImagePath();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<TbccImageControls> findImageAlarmControlsByImgID(final long imageId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from TbccImageControls t where t.imageId = ? and t.dataType = 3");
				query.setParameter(0, imageId);
				if(query.list().size()==0)return null;
				return query.list();
			}
		});
	}


}
