package org.fdap.biz;

import java.util.List;

import org.fdap.entity.FdapConfigInfo;
import org.fdap.entity.Fdaporg;

public interface ConfigInfoBiz {
	
	/**
	 * 获取所有企业信息集合(不包括机构)
	 * @return
	 */
	public List<Fdaporg> getAllOrg();
	
	
	/**
	 * 根据企业id和工程类型，获取对应企业下的配置信息集合
	 * @param oid			企业标识ID
	 * @param type			工程类型(1代表仓库，2代表车载)
	 * @return
	 */
	public List<FdapConfigInfo> getConfigInfo(Long oid,Integer type);
	
}
