package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.OrgBiz;
import org.fdap.dao.OrgDao;
import org.fdap.entity.Fdaporg;

/**
 * 机构业务数据实现类
 * @author zhaoyou
 *
 */
public class OrgBizImpl implements OrgBiz {

	
	private OrgDao orgDao = null ;
	
	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public Fdaporg getByOid(Long oid) {
		
		//验证参数
		if(oid==null || oid.equals("")){
			return null ;
		}
		return this.orgDao.queryByOid(oid);
	}

	public List<Fdaporg> getByParentId(Long parentId) {
		
		//验证参数
		if(parentId==null || parentId.equals("")){
			return null ;
		}
		return this.orgDao.queryByParentId(parentId);
	}

	public List<Long> getLowerOrg(Long parentId) {
		
		//验证参数
		if(parentId==null || parentId.equals("")){
			return null ;
		}
		return this.getOrgDao().queryLowerOrgByParentId(parentId);
	}
	
	/*public List<Fdaporg> getLowerOrgList(Long parentId) {
		//验证参数
		if(parentId==null || parentId.equals("")){
			return null ;
		}
		return this.getOrgDao().queryLowerOrgListByParentId(parentId);
	}*/

}
