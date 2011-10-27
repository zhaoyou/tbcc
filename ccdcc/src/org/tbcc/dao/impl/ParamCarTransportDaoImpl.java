package org.tbcc.dao.impl;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ParamCarTransportDao;
import org.tbcc.entity.config.TbccParamVehicleTransport;

/**
 * ����������Ϣ����ʵ����
 * @author zhaoyou
 *
 */
public class ParamCarTransportDaoImpl extends HibernateDaoSupport implements
		ParamCarTransportDao {

	

	public void insertCarTransporst(TbccParamVehicleTransport transport) {
			this.getHibernateTemplate().save(transport);
	}

	public TbccParamVehicleTransport queryCarTransport(Long id) {
		return (TbccParamVehicleTransport)this.getHibernateTemplate().get(TbccParamVehicleTransport.class, id);
	}

}
