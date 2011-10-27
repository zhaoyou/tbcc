package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseRealBox;

/**
 * 实时小批零的业务访问接口
 * @author Administrator
 *
 */
public interface RealBoxBiz {
	
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
	 * 代表小批零的运行状态为停止
	 */
	public static final int STOP  = 1 ;
	/**
	 * 代表小批零的运行状态为运行
	 */
	public static final int RUN = 2 ;
	
	/**
	 * 代表小批零的状态为预警
	 */
	public static final int PREALARM = 0 ;
	/**
	 * 代表小批零的状态为报警
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * 代表小批零的状态为正常
	 */
	public static final int NORMAL = 2 ;
	
	
	
	/**
	 * 代表小批零的链接状态为断开
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	
	/**
	 * 代表小批零的链接状态为已连接
	 */
	public static final int  CONNECTION = 2 ;

	/**
	 * 根据分支标识Id，获取该分支下所有小批零实时数据
	 * @param branchId		分支标识Id
	 * @return
	 */
	public List<TbccBaseRealBox> getRealBox(Long branchId) ;
}
