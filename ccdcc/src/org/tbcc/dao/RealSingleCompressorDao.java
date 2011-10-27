package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccSingleCompressorRealData;

/**
 * 冷凝机组实时数据访问接口
 * @author Administrator
 *
 */
public interface RealSingleCompressorDao {
	/**
	 * 根据冷凝机组的标识Id，获取冷凝机组的实时数据
	 * @param cId
	 * @return
	 */
	public List<TbccSingleCompressorRealData>	get(Integer cId) ;
}
