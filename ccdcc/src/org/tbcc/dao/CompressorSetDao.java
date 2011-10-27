package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorSet;

/**
 * 机组数据访问接口
 * @author Administrator
 *
 */
public interface CompressorSetDao {
	/**
	 * 根据devId 获取机组集合
	 * @param devId	设备Id
	 * @return
	 */
	List<TbccCompressorSet> getByDevId(Integer devId);
	
	/**
	 * 根据一组devId 条件想符合的字符串集合、获取所有的机组集合
	 * @param str	eg: (12,13,14)
	 * @return
	 */
	List<TbccCompressorSet> getByCondition(String str) ;
	
	/**
	 * 根据机组的标识Id，获取机组信息
	 * @param id	机组的标识Id
	 * @return
	 */
	TbccCompressorSet getById(Integer id) ;
}
