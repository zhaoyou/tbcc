package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapcarrealdata;
import org.fdap.entity.Fdapproject;

/**
 * 车载实时数据业务访问接口
 * @author zhaoyou
 *
 */
public interface CarRealBiz {
	
	/**
	 * 通过企业标识Id，获取所有的车载工程
	 * @param oid			企业标识id
	 * @return
	 */
	public List<Fdapproject>	getCarProjectByOid(Long oid) ;
	
	/**
	 * 通过车载工程标识Id集合
	 * @param projectIds		工程标识Id集合
	 * @return					车载实时数据
	 */
	public List<Fdapcarrealdata> getByProjectids(String projectIds);
	
	
}
