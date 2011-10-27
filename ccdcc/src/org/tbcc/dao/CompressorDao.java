package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorRealData;

/**
 * 压缩机实时数据访问接口
 * @author Administrator
 *
 */
public interface CompressorDao {
	/**
	 *  根据压缩机的标识Id，获取该压缩机的实时数据
	 * @param cid		压缩机的标识Id
	 * @return
	 */
	public List<TbccCompressorRealData> getByCid(Integer cid) ;
	
	/**
	 * 根据一个压缩机标识集合，获取该压缩机集合的数据 
	 * @param str	符合条件的字符串 eg: (12,13,14)
	 * @return
	 */
	public List<TbccCompressorRealData> getByCondition(String str) ;
	
	/**
	 * 根据压缩机组的标识Id，获取该压缩机组下的所有压缩机头的标识Id集合
	 * @param csId			压缩机的标识Id
	 * @return
	 */
	public List<Integer>	getIdsByCsId(Integer csId) ;
}
