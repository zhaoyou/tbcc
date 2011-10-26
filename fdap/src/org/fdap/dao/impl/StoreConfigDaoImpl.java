package org.fdap.dao.impl;

import java.util.List;

import org.fdap.dao.StoreConfigDao;
import org.fdap.entity.Fdapstoretype;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 存储类型配置数据实现类
 * @author zhaoyou
 *
 */
public class StoreConfigDaoImpl extends HibernateDaoSupport implements
		StoreConfigDao {

	@Override
	public void deleteStoreType(Integer storeId) {
		String hql = "delete from Fdapstoretype storetype where storetype.storeid = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setInteger(0, storeId) ;
		query.executeUpdate() ;
	}

	@Override
	public void insertStoreType(Fdapstoretype storeType) {
		this.getHibernateTemplate().save(storeType) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fdapstoretype> queryAllStoreType() {
		return this.getSession().createQuery("from Fdapstoretype" ).list();
	}

	@Override
	public Fdapstoretype queryById(Integer storeId) {
		return (Fdapstoretype)this.getHibernateTemplate().get(Fdapstoretype.class, storeId);
	}

	@Override
	public void updateStoreType(Fdapstoretype storeType) {
		/*String hql = "update Fdapstoretype storetype set storetype.name = ? ,storetype.humhighdelay = ? ,storetype.humhighlimit = ?  " +
				",storetype.humlowerdelay = ? ,storetype.humlowerlimit = ? ,storetype.temphighdelay = ?,storetype.temphighlimit = ? " +
				",storetype.templowerdelay = ? ,storetype.templowerlimit = ? ,storetype.tempmaxhighdelay = ? ,storetype.tempmaxhighlimit = ? " +
				",storetype.tempminlowerdelay = ? ,storetype.tempminlowerlimit = ? where storetype.storeid = ? " ;*/
		String hql = "update Fdapstoretype storetype set storetype.name = ? ,storetype.humhighdelay = ? ,storetype.humhighlimit = ?  " +
		",storetype.humlowerdelay = ? ,storetype.humlowerlimit = ? ,storetype.temphighdelay = ?,storetype.temphighlimit = ? " +
		",storetype.templowerdelay = ? ,storetype.templowerlimit = ? where storetype.storeid = ? " ;
		Query query = this.getSession().createQuery(hql) ;
		query.setString(0, storeType.getName()) ;
		query.setInteger(1, storeType.getHumhighdelay()) ;
		query.setDouble(2, storeType.getHumhighlimit()) ;
		query.setInteger(3, storeType.getHumlowerdelay()) ;
		query.setDouble(4, storeType.getHumlowerlimit()) ;
		query.setInteger(5,storeType.getTemphighdelay()	);
		query.setDouble(6, storeType.getTemphighlimit()) ;
		query.setInteger(7, storeType.getTemplowerdelay()) ;
		query.setDouble(8, storeType.getTemplowerlimit()) ;
		
		
		/*******************有下下限和上上限时的代码************************/
		/*query.setInteger(9, storeType.getTempmaxhighdelay()) ;
		query.setDouble(10, storeType.getTempmaxhighlimit()) ;
		query.setInteger(11, storeType.getTempminlowerdelay()) ;
		query.setDouble(12, storeType.getTempminlowerlimit()) ;
		query.setInteger(13, storeType.getStoreid()) ;*/
		query.setInteger(9, storeType.getStoreid()) ;
		query.executeUpdate() ;	
	}

	@Override
	public void deleteStoreTypeByIds(List<Integer> ids) {
		String hql =  "delete from Fdapstoretype storetype where storetype.storeid in (:ids)";
		Query query  = this.getSession().createQuery(hql) ;
		query.setParameterList("ids", ids);
		query.executeUpdate() ;	
	}

	@Override
	public Long queryRefCountByStoreTypeId(List<Integer> ids) {
		String hql = "select count(*) from Fdapref ref where ref.fdapstoretype.storeid in (:ids)" ;
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (Long)query.uniqueResult();
	}

}
