package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.RefInfoBiz;
import org.tbcc.dao.RefInfoDao;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是操作冷库的业务实现类
 * @author Administrator
 *
 */
public class RefInfoBizImpl implements RefInfoBiz {
	
	private RefInfoDao refinfoDao = null ; 
	
	public void setRefinfoDao(RefInfoDao refinfoDao) {
		this.refinfoDao = refinfoDao;
	}

	
	
	public List<TbccRefInfo> getRefListByPrj(String proId) {
		if(proId==null || proId.equals(""))
			return null ;
		return this.refinfoDao.getRefByProId(proId);
	}
	
	
	
	public TbccRefInfo getRef(String proId, String netId, String refId) {
		List<TbccRefInfo> refInfoList = this.refinfoDao.getRef(proId, netId, refId);
		if(refInfoList!=null && refInfoList.size()>0)
			return refInfoList.get(0);
		return null;
	}

	
	public TbccRefInfo getById(Long id) {
		return refinfoDao.get(id);
	}

}
