package org.tbcc.biz;


/**
 * ���ǲ���������ҵ��ӿ�
 * @author zhaoyou
 *
 */
public interface ParamActionBiz {
	
	/**
	 * ��������״̬
	
	public final Byte REQUEST_STATUS = 1 ;
	 */
	/**
	 * �����ɹ�״̬
	 
	public final Byte SUCCESS_STATUS = 2 ;
	*/
	/**
	 * ����ʧ��״̬
	
	public final Byte FAIL_STATUS = 3 ;
	 */
	/**
	 * ����ȡ��״̬
	
	public final Byte CANCEL_STATUS = 4 ;
	 */
	/**
	 * �����ɹ���״̬
	
	public final Integer	OPERATE_SUCCESS = 2 ;
	 */
	/**
	 * ����ʧ�ܵ�״̬
	 
	public final Integer 	OPERATE_FAIL = 3 ;
	*/
	
	/**
	 *  ���ܲ���Ϊ����ʱ�� 1
	 */
	public final Byte 	FUNCTYPE_CLOCK =  1 ;
	
	/**
	 * ���ܲ���Ϊ�������ز��� 2
	 */
	public final Byte	FUNCTYPE_CARPARAM =2 ;
	
	/**
	 * ���ܲ���Ϊ��������������Ϣ 3
	 */
	public final Byte 	FUNCTYPE_TRANS = 3 ;
	
	
	/**
	 * ����һ����������
	 * @param projectId		�ò�����Ӧ�ĳ��ع��̱�ʾ
	 * @param funcType		�����Ĺ�������
	 * @param cmdType		��������������
	 * @param optStatus		������״̬
	 * @return				�����ı�ʾId
	 */
	public Long addOperate(String projectId,Byte funcType,Byte cmdType,Byte optStatus) ;
	
	/**
	 * ���ݲ��������ı�ʾId����ȡ�ö������е�״̬
	 * @param id	����������ʾId
	 * @return		����״̬
	 */
	public Byte getOperateStatus(Long id) ;
	
	/**
	 * ����ĳ��������״̬
	 * @param id	�����ı�ʾid
	 * @param optStatus		������״̬
	 * @return				�����ĳɹ�״̬��
	 */
	public Integer updateOperateStatus(Long id ,Byte optStatus);
}
