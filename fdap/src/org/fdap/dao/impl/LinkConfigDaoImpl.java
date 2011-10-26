package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.LinkConfigDao;
import org.fdap.entity.Fdaplink;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LinkConfigDaoImpl extends HibernateDaoSupport implements LinkConfigDao {
	
	@Override
	public void DelLink(Long delId) throws Exception {
		String hql = "delete from Fdaplink fl where fl.lid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, delId) ;
		query.executeUpdate() ;
	}

	@Override
	public boolean insertLink(Fdaplink link) {
		try{
			this.getHibernateTemplate().save(link) ;
			return true;
		}
		catch (DataAccessException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fdaplink> queryLinkByType(Long ltid) {
		String hql = "from Fdaplink fl where fl.ltid= ?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, ltid);
		return query.list();
	}

	@Override
	public Fdaplink queryById(Long lid) {
		return (Fdaplink)this.getHibernateTemplate().get(Fdaplink.class, lid);
	}

	@Override
	public void updateLink(Fdaplink link) throws Exception {
		String hql = "update Fdaplink fl set fl.name = ? ,fl.url = ? where fl.lid = ?	" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, link.getName()) ;
		query.setString(1, link.getUrl()) ;
		query.setLong(2, link.getLid()) ;
		query.executeUpdate() ;
	}

	@Override
	public Long querycouts(Long ltid) {
		String hql = "select count(*) from Fdaplink flt where flt.ltid = ?" ;
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, ltid);
		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Fdaplink> queryAllLink() {
		String hql = "from Fdaplink";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	
}
