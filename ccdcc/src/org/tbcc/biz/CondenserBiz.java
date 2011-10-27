package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCondenserRealData;

/**
 * 冷凝器业务接口
 * @author Administrator
 *
 */
public interface CondenserBiz {
		/**
		 * 根据机组的标识Id，获取冷凝器的实时数据
		 * @param csId
		 * @return
		 */
		List<TbccCondenserRealData> getBycsId(Integer csId) ;
		
		/**
		 * 根据冷凝器的标识id集合，获取冷凝器的实时数据
		 * @param str	eg: 12,13,15
		 * @return
		 */
		List<TbccCondenserRealData> getByCids(String str) ;
		
		/**
		 * 根据压缩机组的标识Id，获取该机组下的所有冷凝器的标识Id集合
		 * @param csId		冷凝器的标识id
		 * @return
		 */
		List<Integer> getIdsByCsid(Integer csId) ;
}
