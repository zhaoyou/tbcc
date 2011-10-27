package org.tbcc.biz.impl;

import org.tbcc.biz.DevTypeBiz;
import org.tbcc.dao.DevTypeDao;
import org.tbcc.entity.TbccDevType;

public class DevTypeBizImpl implements DevTypeBiz {

	private DevTypeDao devTypeDao = null  ;
	
	
	
	public void setDevTypeDao(DevTypeDao devTypeDao) {
		this.devTypeDao = devTypeDao;
	}



	public TbccDevType getById(Long id) {
		//验证参数
		if(id==null || id.equals("")){
			return null ;
		}
		return devTypeDao.getById(id);
	}

}
