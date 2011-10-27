package org.tbcc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.RealCarDao;
import org.tbcc.entity.TbccBaseRealCar;


/**
 * 这是移动车载实时数据的数据访问的实现类
 * @author Administrator
 *
 */
public class RealCarDaoImpl extends HibernateDaoSupport implements RealCarDao {

	
	@SuppressWarnings("unchecked")
	public List<TbccBaseRealCar> getRealCars(String condition) {
		String hql = "from TbccBaseRealCar r where r.projectId in "+condition +" order by r.projectCode desc ";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<TbccBaseRealCar> getRealCar(String projectId) {
		String hql = "from TbccBaseRealCar r where r.projectId  = '" +projectId+"'" ;
		return this.getHibernateTemplate().find(hql);
	}

}
