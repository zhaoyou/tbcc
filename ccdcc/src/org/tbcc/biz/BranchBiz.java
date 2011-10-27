package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBranchType;

/**
 * 这是分支操作业务接口
 * @author Administrator
 *
 */
public interface BranchBiz {
	
	/**
	 * 显示分支的Logo 0
	 */
	public final Integer LogoEnable = 0 ;
	
	/**
	 * 不显示分支的Logo(显示默认的Logo) 1 
	 */
	public final Integer LogoDisable = 1 ;
	
	
	/**
	 * 报警系统
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * 制冷系统
	 */
	public static final int COLD = 2 ;
	
	/**
	 * 根据分支标识Id，获取分支对象
	 * @param branchId		分支标识Id
	 * @return				分支对象 
	 */
	public TbccBranchType getById(Long branchId);             //根据branchId获取对象
	
	/**
	 * 根据分支列表集合，获取分支对象集合 （是考虑加入客户关系时所加的东西）
	 * @param ids		分支列表标识集合
	 * @return
	 */
	public List<TbccBranchType> getByIds(List<String> ids) ;

}
