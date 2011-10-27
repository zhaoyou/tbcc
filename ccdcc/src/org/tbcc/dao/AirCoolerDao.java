package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccAirCoolerRealData;


/**
 *  冷风机实时数据访问接口
 * @author Administrator
 *
 */
public interface AirCoolerDao {
		/**
		 * 根据冷风机的标识Id，获取冷风机的实时数据
		 * @param cid		冷风机的标识Id
		 * @return
		 */
		public List<TbccAirCoolerRealData>	getByCid(Integer cid);
		
		/**
		 * 根据一组符合条件的冷风机标识Id，获取一组冷风机实时数据
		 * @param str	eg:(12,13,14)
		 * @return
		 */
		public List<TbccAirCoolerRealData> getByCondition(String str);
		
		
		/**
		 * 根据压缩机组的标识Id，获取该制冷设备下的所有冷风机的标识
		 * @param csId			压缩机组的标识Id
		 * @return
		 */
		public List<Integer> getIdsByCsId(Integer csId) ;
}
