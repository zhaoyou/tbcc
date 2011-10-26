package org.fdap.biz;

import java.util.List;
import java.util.Map;

import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;

/**
 * 仓库冷库历史数据业务访问接口
 * @author zhoukuanwei
 *
 */
public interface RefHisBiz {
	
	/**
	 * 代表冷库的状态为预警 0
	 */
	public static final int PREALARM = 0 ;
	/**
	 * 代表冷库的状态为报警 1
	 */
	public static final int ALARM = 1 ;
	/**
	 * 代表冷库的状态为正常 2
	 */
	public static final int NORMAL = 2 ;
	
	/**
	 * 工程类型为仓库 1
	 */
	public  static final Integer REF_TYPE = 1 ;
	/**
	 * 工程类型为车载 2
	 */
	public static final Integer CAR_TYPE = 2 ;
	
	/**
	 * 根据时间和仓库冷库Id，获取仓库冷库历史数据(分页查询),并整理成每个冷库的历史数据
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param startrow         	查询历史数据开始位置
	 * @param pagesize			查询历史数据每页的条数
	 * @param aiList			该冷库下的探头列表
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<Object> getRefHisData(String tableName,String startTime,String endTime,Integer startrow,Integer pagesize,List<Fdapaiinfo> AiList,String refId);
	
	/**
	 * 根据时间和仓库冷库Id，获得仓库冷库历史数据的总条数
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract Integer getRefHisCount(String tableName,String startTime,String endTime,String refId);
	
	
	/**
	 * 根据仓库冷库Id，获取该冷库下的探头列表
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<Fdapaiinfo> getAiByRefId(String refId);
	
	/**
	 * 根据时间和仓库冷库Id，获得所有满足条件的仓库冷库历史数据，来计算相应探头的最大、最小和平均值
	 * @param tableName         仓库冷库历史数据表名
	 * @param startTime			查询历史数据的开始时间
	 * @param endTime         	查询历史数据的结束时间
	 * @param aiList			该冷库下的探头列表
	 * @param refId				冷库Id
	 * @return
	 */
	public abstract List<Object> getRefHisMMA(String tableName,String startTime,String endTime,List<Fdapaiinfo> AiList,String refId);
	
	/**
	 * 通过企业标识Id，获取仓库工程集合
	 * @param oid		企业标识Id
	 * @return
	 */
	public abstract List<Fdapproject> getProjectByOid(Long oid);
	
	/**
	 * 通过工程标识Id，获取该工程下的所有冷库集合
	 * @param projectId		工程标识Id
	 * @return
	 */
	public abstract List<Fdapref> getRefByProjectId(Long projectId);
	
	/**
	 * 通过企业标识oid、仓库冷库标识refId、开始时间以及结束时间查询仓库冷库历史数据(FLEX方法调用)
	 * @param oid
	 * @param refId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Map> getRefHisData_flex(String oid,String refId ,String startTime,String endTime);
}
