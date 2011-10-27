package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseRealCar;

/**
 * 这是移动车载实时数据的业务操作接口
 * @author Administrator
 *
 */
public interface RealCarBiz {
	
	/**
	 * 代表南纬
	 */
	public static int SOUTH = 0 ;
	/**
	 * 代表北纬
	 */
	public static int NORTH = 1 ;
	
	/**
	 * 代表东经
	 */
	public static int EAST = 0 ;
	/**
	 * 代表西经
	 */
	public static int WEST = 1 ;
	
	/**
	 * 代表车载的运行状态为停止
	 */
	public static final int STOP  = 1 ;
	/**
	 * 代表车载的运行状态为运行
	 */
	public static final int RUN = 2 ;
	
	/**
	 * 代表车载的状态为预警
	 */
	public static final int PREALARM = 0 ;
	/**
	 * 代表车载的状态为报警
	 */
	public static final int ALARM = 1;
	/**
	 * 代表车载的状态为正常
	 */
	public static final int NORMAL = 2 ;
	
	/**
	 * 代表车载的链接状态为断开
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	/**
	 * 代表车载的链接状态为已连接
	 */
	public static final int  CONNECTION = 2 ;
	
	/**
	 * 根据分支机构的branchId ，获取该机构下的所有车载实时数据
	 * @param branchId		分支机构Id
	 * @return				车载实时数据集合
	 */
	public List<TbccBaseRealCar> getRealData(Long branchId) ;
	
	/**
	 * 根据项目工程的编号，获取该车载的实时数据 ,用于地图显示
	 * @param projectId			工程编号
	 * @return					单辆车载的实时数据
	 */
	public TbccBaseRealCar getDataToMap(String projectId) ;
	
}
