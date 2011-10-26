package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdaprefrealdata;

/**
 * 冷库实时数据业务访问接口
 * @author zhaoyou
 *
 */
public interface RefRealBiz {
	
	
	/**
	 * 通过企业标示id，获取该企业下是所有仓库工程
	 * @param oid		企业标识Id
	 * @return
	 */
	public List<Fdapproject> getRefProjectByOid(Long oid);
	
	
	/**
	 * 根据仓库工程的编号集合，获取仓库工程下的所有冷库集合
	 * @param projectIds		仓库工程Id集合
	 * @return
	 */
	public List<Fdapref>	getRefByProjectIds(String projectIds);
	
	/**
	 * 通过仓库工程的编号集合，获取仓库工程下的所有探头集合
	 * @param projectIds		仓库工程id集合
	 * @return
	 */
	public List<Fdapaiinfo> getAiByProjectIds(String projectIds);
	
	/**
	 * 通过仓库工程的编号集合，获取仓库工程下的所有探头的实时数据
	 * @param projectIds		仓库工程Id集合
	 * @return
	 */
	public List<Fdaprefrealdata> getRealByProjectIds(String projectIds);

}
