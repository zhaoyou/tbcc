package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseRealBox;

/**
 * ʵʱС�����ҵ����ʽӿ�
 * @author Administrator
 *
 */
public interface RealBoxBiz {
	
	/**
	 * ������γ
	 */
	public static int SOUTH = 0 ;
	/**
	 * ����γ
	 */
	public static int NORTH = 1 ;
	
	/**
	 * ������
	 */
	public static int EAST = 0 ;
	/**
	 * ��������
	 */
	public static int WEST = 1 ;
	
	/**
	 * ����С���������״̬Ϊֹͣ
	 */
	public static final int STOP  = 1 ;
	/**
	 * ����С���������״̬Ϊ����
	 */
	public static final int RUN = 2 ;
	
	/**
	 * ����С�����״̬ΪԤ��
	 */
	public static final int PREALARM = 0 ;
	/**
	 * ����С�����״̬Ϊ����
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * ����С�����״̬Ϊ����
	 */
	public static final int NORMAL = 2 ;
	
	
	
	/**
	 * ����С���������״̬Ϊ�Ͽ�
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	
	/**
	 * ����С���������״̬Ϊ������
	 */
	public static final int  CONNECTION = 2 ;

	/**
	 * ���ݷ�֧��ʶId����ȡ�÷�֧������С����ʵʱ����
	 * @param branchId		��֧��ʶId
	 * @return
	 */
	public List<TbccBaseRealBox> getRealBox(Long branchId) ;
}
