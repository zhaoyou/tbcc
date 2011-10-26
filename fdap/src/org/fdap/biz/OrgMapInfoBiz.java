package org.fdap.biz;

/**
 * 机构地图信息业务接口
 * @author zhoukuanwei
 *
 */
public interface OrgMapInfoBiz {
	
	/**
	 * 查询需要地图的所有机构信息，以字符串形式返回
	 * @return
	 */
	public String getOrgMapStr();
}
