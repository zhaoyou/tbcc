package org.fdap.biz;

import org.fdap.entity.Fdaporg;

/**
 * 默认显示方式配置接口
 * @author zhoukuanwei
 *
 */
public interface DefaultDisplayBiz {
	/**
	 * 获取机构的树形菜单 
	 * @return			树形菜单的字符串
	 */
	public String getTree();
	
	/**
	 * 根据标识获取机构信息
	 * @param oid			标识Id
	 * @return
	 */
	public Fdaporg getByOid(Long oid) ;
	
	
	/**
	 * 更新一个机构信息
	 * @param oid			机构企业标识id
	 * @param isshowmap		是否地图显示标识
	 * @return
	 */
	public boolean updateOrg(Long oid,Integer isshowmap);
}
