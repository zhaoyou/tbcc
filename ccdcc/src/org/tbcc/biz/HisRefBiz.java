package org.tbcc.biz;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;

/**
 * 这是冷库历史数据业务访问接口
 * @author Administrator
 *
 */
public interface HisRefBiz {
	/**
	 * 代表冷库为预警状态
	 */
	public static final int PREALARM = 0 ;
	/**
	 * 冷库为报警状态
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * 冷库为正常状态
	 */
	public static final int NORMAL = 2 ;
	
	
	
	/**
	 * 根据更加分支机构标识Id，获取所有冷库信息列表，这个方法还是相对于单个冷库工程而言的
	 * @param branchId	机构分支标识
	 * @return			分支标识下单个冷库工程下的所有冷库列表
	 */
	public List<TbccRefInfo> getRefListByBranchId(Long branchId);
	
	/**
	 * 根据冷库标识Id，获取相应的探头
	 * @param id
	 * @return
	 */
	public List<TbccAiInfo>	getAiListByProperty(Long id);
	
	/**
	 * 根据条件获取冷库历史数据，用于绘制曲线
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	public List<Map> getHisRefInfo(Long id,String startTime,String endTime,String type,String value); 
	
	
	
	
	
	

	
	
	/**
	 * 获取仓库历史数据，兼容了之前的版本和设备扩展版本
	 * @param id			冷库标识Id
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param type			时间类型
	 * @param value			时间间隔
	 * @return		
	 */
	public List<Object> getRefHisData(Long id,String startTime,String endTime,String type,String value);
	

	
	/***冷库扩展设备的历史数据***/

	
	
}
