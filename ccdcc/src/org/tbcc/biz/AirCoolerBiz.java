package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccAirCoolerRealData;

/**
 * 冷风机业务接口
 * @author Administrator
 *
 */
public interface AirCoolerBiz {
	/**
	 * 根据机组的标识Id，获取冷风机的实时数据
	 * @param csId 机组的标识Id
	 * @return
	 */
	public List<TbccAirCoolerRealData> getBycsId(Integer csId) ;
	
	/**
	 *  根据冷风机的标识集合、获取冷风机的实时数据
	 * @param ids		冷风机的标示集合格式严格匹配 12,13,15
	 * @return
	 */
	public List<TbccAirCoolerRealData> getByAids(String ids)  ;
	
	/**
	 * 根据压缩机组的标识Id，获取该机组下的所有冷风机标识
	 * @param csId		压缩机组的标识
	 * @return
	 */
	public List<Integer> getIdsBycsId(Integer csId) ;
	
}
