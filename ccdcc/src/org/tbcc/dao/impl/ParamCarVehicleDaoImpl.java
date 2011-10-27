package org.tbcc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ParamCarVehicleDao;
import org.tbcc.entity.config.TbccParaVehicleAlarm;

/**
 * 车载参数操作的数据实现类
 * @author zhaoyou
 *
 */
public class ParamCarVehicleDaoImpl extends HibernateDaoSupport implements
		ParamCarVehicleDao {

	@SuppressWarnings("unchecked")
	public List<TbccParaVehicleAlarm> getByPid(Long parentId) {
		String hql = "from TbccParaVehicleAlarm p where p.parentId = ?" ;
		return this.getHibernateTemplate().find(hql, parentId);
		
	}

	public Integer updateParamVehicle(final String sql) {	
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			Connection conn = session.connection() ;
			PreparedStatement pre = conn.prepareStatement(sql);
			Integer r = pre.executeUpdate() ;
			pre.close() ;
			return r ;
		}}) ;
		
	}

}
