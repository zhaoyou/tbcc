package org.fdap.biz.impl;

import java.util.ArrayList;
import java.util.List;
import org.fdap.biz.CarRealBiz;
import org.fdap.biz.ProjectBiz;
import org.fdap.dao.CarRealDataDao;
import org.fdap.dao.ProjectDao;
import org.fdap.entity.Fdapcarrealdata;
import org.fdap.entity.Fdapproject;

/**
 * 车载实时数据业务实现接口
 * @author zhaoyou
 *
 */
public class CarRealBizImpl implements CarRealBiz {
	
	private ProjectDao projectDao = null ;
	
	private CarRealDataDao carRealDataDao = null ;
	
	
	
	public CarRealDataDao getCarRealDataDao() {
		return carRealDataDao;
	}

	public void setCarRealDataDao(CarRealDataDao carRealDataDao) {
		this.carRealDataDao = carRealDataDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	

	public List<Fdapproject> getCarProjectByOid(Long oid) {
		
		//验证参数
		if(oid==null || oid.equals("")){
			return null ;
		}
				
		return this.projectDao.queryByOidAndType(oid, ProjectBiz.CAR_TYPE);
	}

	public List<Fdapcarrealdata> getByProjectids(String projectIds) {
		
		//验证参数
		if(projectIds==null || projectIds.equals("")){
			return null ;
		}
		String[] ids = projectIds.split(",");
		
		List<Long> list = new ArrayList<Long>();
		for (String str : ids) {
			list.add(Long.parseLong(str));
		}
		return this.carRealDataDao.queryByProjectIds(list);
	}

}
