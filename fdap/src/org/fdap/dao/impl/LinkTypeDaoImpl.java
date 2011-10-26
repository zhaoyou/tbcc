package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.LinkTypeDao;
import org.fdap.entity.Fdaplinktype;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LinkTypeDaoImpl extends HibernateDaoSupport implements LinkTypeDao {
	
	@Override
	public void DelLinktype(Long delId) throws Exception {
		String hql = "delete from Fdaplinktype fl where fl.ltid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setLong(0, delId) ;
		query.executeUpdate() ;
	}

	@Override
	public boolean insertLinktype(Fdaplinktype linktype) {
		try{
			this.getHibernateTemplate().save(linktype) ;
			return true;
		}
		catch (DataAccessException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fdaplinktype> queryAllLinktype() {
		String hql = "from Fdaplinktype";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Fdaplinktype queryById(Long ltid) {
		return (Fdaplinktype)this.getHibernateTemplate().get(Fdaplinktype.class, ltid);
	}

	@Override
	public void updateLinktype(Fdaplinktype linktype) throws Exception {
		String hql = "update Fdaplinktype fl set fl.ltname = ? where fl.ltid = ?" ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, linktype.getLtname()) ;
		query.setLong(1, linktype.getLtid());
		query.executeUpdate() ;
	}

}
