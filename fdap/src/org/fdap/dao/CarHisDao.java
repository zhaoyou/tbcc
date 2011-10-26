package org.fdap.dao;

import java.util.List;

//import org.fdap.entity.CarHisView;
import org.fdap.entity.FdapCarHisData;

/**
 * 车载历史数据访问接口
 * @author zhoukuanwei
 *
 */
public interface CarHisDao {
	
	/**
	 * 根据启停记录的Id，获取所有车载历史数据的总条数
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停标识id
	 * @return
	 */
	public abstract List<FdapCarHisData> queryCarHis(String tableName,Integer parentId);
	
	/**
	 * 根据启停记录的Id，分页查询该启停记录下的车载历史数据
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停标识id
	 * @param startRow         	查询车载历史数据开始位置
	 * @param pagesize			查询车载历史数据每页的条数
	 * @return
	 */
	public abstract List<FdapCarHisData> queryCarHisbyStartupPage(String tableName,Integer parentId,Integer startRow,Integer pagesize);
	
	/**
	 * 根据启停记录的Id，获取所有车载历史数据的总条数
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停标识id
	 * @return
	 */
	public abstract Integer queryCarHisCount(String tableName,Integer parentId);
}
