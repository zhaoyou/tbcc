package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCompressorSet;
import org.tbcc.entity.cool.TbccMultiCompressorRealData;
import org.tbcc.entity.cool.TbccSingleCompressorRealData;

/**
 * 压缩机组的业务实现接口
 * @author Administrator
 *
 */
public interface CompressorSetBiz {
	
	/**
	 * 并联机组 1 
	 */
	public static final Integer MULTICOMPRESSOR = 1 ;
	
	/**
	 * 冷凝机组 0 
	 */
	public static final Integer SINGLECOMPRESSOR = 0 ;
	
	/**
	 * 根据项目标识projectId获取所有的压缩机组
	 * @param projectId		冷库工程标识Id
	 * @return
	 */
	public List<TbccCompressorSet> getByProjectId(String projectId);
	
	/**
	 * 根据机构分支标识Id，获取所有机组集合
	 * @param projects	工程标识集合
	 * @return
	 */
	public List<TbccCompressorSet> getByBranchId(Long branchId) ;
	
	/**
	 * 根据机组的标识Id，获取该机组的信息
	 * @param id		机组的标识Id
	 * @return
	 */
	public TbccCompressorSet 	getById(Integer id) ;
	
	
	/**
	 * 根据机组的标识Id，获取并联机组的实时数据
	 * @param id		机组的标识Id
	 * @return
	 */
	 public TbccMultiCompressorRealData getMultiRealById(Integer id) ;
	 
	 /**
	  * 根据机组的标识Id，获取冷凝机组的实时数据
	  * @param id		机组的标识Id
	  * @return
	  */
	 public TbccSingleCompressorRealData getSingleRealById(Integer id);
	 
	 
}
