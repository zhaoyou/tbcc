package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCondenserRealData;

/**
 * 冷凝机实时数据访问接口
 * @author Administrator
 *
 */
public interface CondenserDao {
	/**
	 * 根据冷凝器的标识Id获取该冷凝器的实时数据
	 * @param cid	冷凝器的标识Id
	 * @return
	 */
	public List<TbccCondenserRealData> getByCid(Integer cid);
	
	/**
	 * 根据条件获取一组冷凝器的实时数据组合
	 * @param str	符合条件的冷凝器的标识 eg: (12,13,14)
	 * @return
	 */
	public List<TbccCondenserRealData> getByCondition(String str) ;
	
	/**
	 *  根据压缩机组的标识Id，获取该机组下的冷凝器的标识集合
	 * @param csId		压缩机的标识Id
	 * @return
	 */
	public List<Integer>	getIdsByCsId(Integer csId);
}
