package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccMultiCompressorRealData;

/**
 * 并联机组实时数据访问接口
 * @author Administrator
 *
 */
public interface RealMulCompressorDao {
	/**
	 * 根据机组的标识获取该机组的实时数据
	 * @param csId		机组的标识ID
	 * @return
	 */
	List<TbccMultiCompressorRealData> getByCsId(Integer csId) ;
}
