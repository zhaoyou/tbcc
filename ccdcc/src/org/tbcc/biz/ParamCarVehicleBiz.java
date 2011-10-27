package org.tbcc.biz;

import org.tbcc.log.entity.TbccLog;



/**
 * 车载参数设置业务接口
 * @author zhaoyou
 *
 */
public interface ParamCarVehicleBiz {
	/**
	 * 根据动作操作的表示id，获取该车载对应的参数信息
	 * @param id		动作操作id
	 * @return			车载数据信息 eg: name,valid,value;name,valid,value
	 * name:表示字段表示
	 * valid:表示是否有效(0:无效 1:有效)
	 * value:表示字段值 ( 如果无效则用N/A代替)
	 */
	public String getCarVehicleByParentId(Long pid);
	
	/**
	 * 更新车载的参数信息
	 * @param projectId		需要更新的车载标示
	 * @param source		车载参数信息: eg: name,valid,value;name,valid,value
	 * @param log			增加操作动作日志
	 * @return				更新操作的标示 
	 */
	public Integer updateCarVehicle(String projectId,String source,TbccLog log);
	
	/**
	 * 单独增加动作操作结果日志
	 * @param log			操作结果日志
	 * @return				操作的状态
	 */
	public Integer addUpdateResultLog(TbccLog log);
	
	
	
	
}
