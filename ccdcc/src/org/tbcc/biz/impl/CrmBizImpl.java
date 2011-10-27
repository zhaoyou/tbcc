package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.CrmBiz;
import org.tbcc.dao.CrmDao;
import org.tbcc.entity.TbccCrm;

/**
 * 客户关系业务实现类
 * @author Administrator
 *
 */
public class CrmBizImpl implements CrmBiz {

	private CrmDao crmDao = null ;
	
	/**
	 * 由spring 注入
	 * @param crmDao
	 */
	
	public void setCrmDao(CrmDao crmDao) {
		this.crmDao = crmDao;
	}


	/**
	 * 根据客户的机构Id 获取客户关系实体
	 */
	public List<TbccCrm> getByHid(String hid) {
		return  this.crmDao.getByHid(hid) ;
	}

}
