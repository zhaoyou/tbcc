package org.tbcc.dao;

import org.tbcc.entity.config.TbccParamAction;

/**
 * 这是参数操作的数据访问接口
 * @author zhaoyou
 *
 */
public interface ParamActionDao {
	
	public final String GETSTAUS = "select p.optStatus from TbccParaAction p where p.id = ?" ;
	
	public final String UPDATESTATUS_HQL = "update TbccParamAction p set p.optStatus = ? where p.id = ?" ;
	
	public final String ADDACTION = "insert into TbccParaAction (projectId,functype,cmdType,optStatus) values (?,?,?,?);select @@identity ;" ;
	
	/**
	 * 增加一个动作操作
	 * @param paramAction	动作操作实例
	 */
	public Long addAction(String projectId,Byte functype,Byte cmdTYpe,Byte optStatus ) ;
	
	/**
	 * 根据动作的标示id，获取该动作的实体信息
	 * @param id	动作标示id
	 * @return		动作实体		
	 */	
	public TbccParamAction 	getAction(Long id) ;
	
	
	/**
	 * 更新动作的状态
	 * @param id			动作的标示id
	 * @param optStatus		操作的状态码
	 * @return 				操作的状态码
	 */
	public Integer updateActionStatus(Long id ,Byte optStatus) ;
}
