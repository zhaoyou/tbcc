package org.tbcc.dao;

import org.tbcc.entity.config.TbccParamAction;

/**
 * ���ǲ������������ݷ��ʽӿ�
 * @author zhaoyou
 *
 */
public interface ParamActionDao {
	
	public final String GETSTAUS = "select p.optStatus from TbccParaAction p where p.id = ?" ;
	
	public final String UPDATESTATUS_HQL = "update TbccParamAction p set p.optStatus = ? where p.id = ?" ;
	
	public final String ADDACTION = "insert into TbccParaAction (projectId,functype,cmdType,optStatus) values (?,?,?,?);select @@identity ;" ;
	
	/**
	 * ����һ����������
	 * @param paramAction	��������ʵ��
	 */
	public Long addAction(String projectId,Byte functype,Byte cmdTYpe,Byte optStatus ) ;
	
	/**
	 * ���ݶ����ı�ʾid����ȡ�ö�����ʵ����Ϣ
	 * @param id	������ʾid
	 * @return		����ʵ��		
	 */	
	public TbccParamAction 	getAction(Long id) ;
	
	
	/**
	 * ���¶�����״̬
	 * @param id			�����ı�ʾid
	 * @param optStatus		������״̬��
	 * @return 				������״̬��
	 */
	public Integer updateActionStatus(Long id ,Byte optStatus) ;
}
