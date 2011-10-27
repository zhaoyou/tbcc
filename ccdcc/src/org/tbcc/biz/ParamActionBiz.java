package org.tbcc.biz;


/**
 * 这是参数操作的业务接口
 * @author zhaoyou
 *
 */
public interface ParamActionBiz {
	
	/**
	 * 动作请求状态
	
	public final Byte REQUEST_STATUS = 1 ;
	 */
	/**
	 * 动作成功状态
	 
	public final Byte SUCCESS_STATUS = 2 ;
	*/
	/**
	 * 动作失败状态
	
	public final Byte FAIL_STATUS = 3 ;
	 */
	/**
	 * 动作取消状态
	
	public final Byte CANCEL_STATUS = 4 ;
	 */
	/**
	 * 操作成功的状态
	
	public final Integer	OPERATE_SUCCESS = 2 ;
	 */
	/**
	 * 操作失败的状态
	 
	public final Integer 	OPERATE_FAIL = 3 ;
	*/
	
	/**
	 *  功能参数为操作时间 1
	 */
	public final Byte 	FUNCTYPE_CLOCK =  1 ;
	
	/**
	 * 功能参数为操作车载参数 2
	 */
	public final Byte	FUNCTYPE_CARPARAM =2 ;
	
	/**
	 * 功能参数为操作车载运输信息 3
	 */
	public final Byte 	FUNCTYPE_TRANS = 3 ;
	
	
	/**
	 * 增加一个操作动作
	 * @param projectId		该操作对应的车载工程标示
	 * @param funcType		操作的功能类型
	 * @param cmdType		操作的命令类型
	 * @param optStatus		操作的状态
	 * @return				动作的表示Id
	 */
	public Long addOperate(String projectId,Byte funcType,Byte cmdType,Byte optStatus) ;
	
	/**
	 * 根据操作动作的表示Id，获取该动作进行的状态
	 * @param id	操作动作标示Id
	 * @return		操作状态
	 */
	public Byte getOperateStatus(Long id) ;
	
	/**
	 * 更新某个动作的状态
	 * @param id	动作的表示id
	 * @param optStatus		动作的状态
	 * @return				操作的成功状态码
	 */
	public Integer updateOperateStatus(Long id ,Byte optStatus);
}
