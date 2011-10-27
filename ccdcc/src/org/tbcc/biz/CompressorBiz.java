package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorRealData;

/**
 * 压缩机的业务接口
 * @author Administrator
 *
 */
public interface CompressorBiz {
	/**
	 * 根据机组的标识Id，获取压缩机的实时数据
	 * @param csId		机组的标识Id
	 * @return
	 */
	List<TbccCompressorRealData> getBycsId(Integer csId) ;
	
	/**
	 * 根据压缩机头的标识集合，或压缩机的实时数据
	 * @param str	eg: 12,13,14
	 * @return
	 */
	List<TbccCompressorRealData> getByCids(String str) ;
	
	/**
	 * 根据压缩机组的标识Id，获取该机组下的所有机头的标识Id集合
	 * @param csId		压缩机组的标识Id
	 * @return
	 */
	List<Integer> getIdsByCsId(Integer csId) ;
}
