package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccAiInfo;

/**
 *  这是操作探头的业务接口
 * @author Administrator
 *
 */
public interface AiInfoBiz {
	
		/**
		 * 代表温度
		 */
		public final Integer T =  1;
		
		/**
		 *  代表湿度
		 */
		public final Integer RH = 2 ;
		
		/**
		 *  代表压力
		 */
		public final Integer P = 3 ;
		
		/**
		 * 根据冷库工程获取该工程下的所有探头信息
		 * @param proId			工程标识Id
		 * @return
		 */
		public List<TbccAiInfo> getListByProId(String proId);
		
		/**
		 * 获取单个冷库的下的探头
		 * @param proId		冷库工程标识Id
		 * @param netId		网络设备Id
		 * @param refId		冷库编号
		 * @return
		 */
		public List<TbccAiInfo> getRefAiList(String proId,String netId,String refId);
		
		/**
		 * 通过冷库编号获取该冷库的所有探头
		 * @param rid		冷库编号
		 * @return
		 */
		public List<TbccAiInfo> getListByRid(Long rid);
}
