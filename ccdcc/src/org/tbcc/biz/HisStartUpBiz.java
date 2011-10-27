package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * 历史启停业务操作接口
 * @author Administrator
 *
 */
public interface HisStartUpBiz {
	/**
	 * 没有上传
	 */
	public static final int NOUP = 0 ;
	
	/**
	 * 没有完成
	 */
	public static final int NOFINISH =1 ;
	
	/**
	 * 已经完成
	 */
	public static final int FINISH = 2 ;
	
	/**
	 * 没有停止
	 */
	public static final int NOSTOP = 3 ;
	
	List<TbccBaseHisStartUp> getStartUpList(String proId,String beginTime,String endTime);
	
	TbccBaseHisStartUp getStartUp(String proId,long id);
	
	/**
	 * 根据工程标识、起始时间获取起停记录
	 * @param projectId		工程标识
	 * @param beginTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,String beginTime,String endTime) ;
	
	/**
	 * 根据工程标识，起停标识获取起停记录
	 * @param projectId		工程标识
	 * @param id			起停标识
	 * @return
	 */
	TbccBaseHisStartUp getStartUpById(String projectId,Long id) ;
	
	/**
	 * 根据开始时间获取启停记录标识ID(项目FDAP在地图上看查看历史轨迹时需要用到的)
	 * @param projectId		工程标识ID
	 * @param beginTime		启停记录开始时间
	 * @return
	 */
	Long getStartupidByBeginTime(String projectId,String beginTime);
}
