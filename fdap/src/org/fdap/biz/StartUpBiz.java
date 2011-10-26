package org.fdap.biz;

import java.util.List;

import org.fdap.entity.FdapStartUp;

/**
 * 车载启停记录业务访问接口
 * @author zhoukuanwei
 *
 */
public interface StartUpBiz {
	
	/**
	 * 根据时间和冷库标识Id，获得车载历史启停记录
	 * @param tableName         车载历史启停数据表格
	 * @param startTime			查询启停记录的开始时间
	 * @param endTime         	查询启停记录的结束时间
	 * @param refid				冷库标识Id
	 * @param startRow         	查询启停记录开始位置
	 * @param pagesize			查询启停记录每页的条数
	 * @return
	 */
	public abstract List<FdapStartUp> getStartup(String tableName,String refid,String startTime,String endTime,Integer startRow,Integer pagesize);
	
	/**
	 * 根据时间和冷库标识Id，获得车载历史启停记录的总条数
	 * @param tableName         车载历史启停数据表格
	 * @param startTime			查询启停记录的开始时间
	 * @param endTime         	查询启停记录的结束时间
	 * @param refid				冷库标识Id
	 * @return
	 */
	public abstract Integer getStartupCounts(String tableName,String refid,String startTime,String endTime);
	
	/**
	 * 根据企业标识ID和车载历史启停记录标识ID，获取车载历史启停记录详细信息
	 * @param oid			企业标识ID
	 * @param sid			车载历史启停标识ID
	 * @return
	 */
	public abstract FdapStartUp getByOidAndSid(String oid,long sid);
}
