package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealCar;

/**
 * 这是一个操作移动车载实时数据的数据访问接口
 * @author Administrator
 *
 */
public interface RealCarDao {
	
	/**
	 * 根据不同的projectid，获取车载实时数据
	 * @param condition	不同的projectId eg: （3000,3001,3002）
	 * @return	符合条件的所有车站实时数据集合
	 */
	public List<TbccBaseRealCar> getRealCars(String condition) ;
	
	/**
	 * 根据项目工程编号，获取车载实时数据
	 * @param projectId		工程编号
	 * @return				包含一辆车载实时数据的集合
	 */
	public List<TbccBaseRealCar>	getRealCar(String projectId) ;
	
}
