package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.config.TbccParaVehicleAlarm;

/**
 * 这是车载参数设置的数据接口
 * @author zhaoyou
 *
 */
public interface ParamCarVehicleDao {
	
	
	
	/**
	 * 根据参数操作的标示Id，获取车载参数配置信息
	 * @param parentId	动作标示Id
	 * @return			车载参数配置
	 */
	public List<TbccParaVehicleAlarm> getByPid(Long parentId) ;	
	
	/**
	 * 更新车载的配置信息
	 * @param sql		需要执行的更新语句
	 * @return			操作的状态
	 */
	public	Integer updateParamVehicle(String sql);
}
