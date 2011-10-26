package org.fdap.biz;

import java.util.List;

import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdapref;


/**
 * 车载历史数据业务访问接口
 * @author zhoukuanwei
 *
 */
public interface CarHisBiz {
	/**
	 * 工程类型为仓库 1
	 */
	public  static final Integer REF_TYPE = 1 ;
	
	/**
	 * 工程类型为车载 2
	 */
	public static final Integer CAR_TYPE = 2 ;
	
	/**
	 * 根据启停记录的Id，获取该启停记录下的所有车载历史数据，来计算相应探头的最大、最小和平均值
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @return
	 */
	public abstract List<Object> getCarHisMMA(String tableName,Integer parentId);
	
	/**
	 * 根据启停记录的Id，分页获取该启停记录下的车载历史数据
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @param startRow         	查询车载历史数据开始位置
	 * @param pagesize			查询车载历史数据每页的条数
	 * @return
	 */
	public abstract List<FdapCarHisData> getCarHisbyStartup(String tableName,Integer parentId,Integer startRow,Integer pagesize);
	
	/**
	 * 根据启停记录的Id，获取所有车载历史数据的总条数
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @return
	 */
	public abstract Integer getCarHisCount(String tableName,Integer parentId);
	
	/**
	 * 根据企业Id，获取该企业下所有车载冷库列表
	 * @param oid         企业oid
	 * @return
	 */
	public abstract List<Fdapref> getCarRefByOid(String oid);
	
	/**
	 * 根据车载冷库的Id，获取车载冷库详细信息
	 * @param refId			车载冷库id
	 * @return
	 */
	public abstract Fdapref getCarRefById(String refId);
	
	
	/**
	 * 根据企业oid和启停记录的Id，获取所有车载历史数据(flex调用)
	 * @param oid         		企业oid
	 * @param sid				车载启停标识id
	 * @return
	 */
	public abstract List<FdapCarHisData> getCarHis(String oid,String sid);
}
