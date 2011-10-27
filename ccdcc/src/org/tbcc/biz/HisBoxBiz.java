package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccPrjType;

/**
 * 历史小批零的业务访问接口
 * @author Administrator
 *
 */
public interface HisBoxBiz {
	
	/**
	 * 代表南纬
	 */
	public static final int SOUTH = 0 ;
	/**
	 * 代表北纬
	 */
	public static final int NORTH = 1 ;
	
	/**
	 * 代表东经
	 */
	public static final int EAST = 0 ;
	/**
	 * 代表西经
	 */
	public static final int WEST =1 ;
	
	/**
	 * 代表预警状态
	 */
	public static final int PREALARM = 0 ;
	
	/**
	 * 代表报警状态
	 */
	public static final int ALARM = 1 ;
	/**
	 * 代表正常状态
	 */
	public static final int NORMAL =2;
	
	public List<TbccBaseHisBox> getByProperty(String proId,String startTime,String endTime,String type,String value);
	
	public List<TbccPrjType> getBoxPrjByBranchID(Long branchId);
	
	public List<TbccPrjType> getBoxPrjProxy(Long branchId);
	
	public Object[] getCalcValue(List<TbccBaseHisBox> boxList);
}
