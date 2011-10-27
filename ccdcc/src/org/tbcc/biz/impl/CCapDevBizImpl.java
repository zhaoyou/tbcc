package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CCapDevBiz;
import org.tbcc.dao.CcapDevDao;
import org.tbcc.entity.cool.TbccCcapDevType;

/**
 * 制冷设备业务实现类
 * @author Administrator
 *
 */
public class CCapDevBizImpl implements CCapDevBiz {

	
	private CcapDevDao ccapDevDao = null ;
	
	public void setCcapDevDao(CcapDevDao ccapDevDao) {
		this.ccapDevDao = ccapDevDao;
	}



	public List<TbccCcapDevType> getByProjectId(String projectId) {
		if(projectId==null || projectId.equals(""))
			return null ;
		 return ccapDevDao.getByProjectId(projectId);
	}



	public List<TbccCcapDevType> getByProjects(String str) {
		
		if(str==null || str.equals(""))
			return null ;
		
		return ccapDevDao.getByCondition(str);
		
	}

}
