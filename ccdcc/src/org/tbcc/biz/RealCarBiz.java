package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseRealCar;

/**
 * �����ƶ�����ʵʱ���ݵ�ҵ������ӿ�
 * @author Administrator
 *
 */
public interface RealCarBiz {
	
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
	 * �����ص�����״̬Ϊֹͣ
	 */
	public static final int STOP  = 1 ;
	/**
	 * �����ص�����״̬Ϊ����
	 */
	public static final int RUN = 2 ;
	
	/**
	 * �����ص�״̬ΪԤ��
	 */
	public static final int PREALARM = 0 ;
	/**
	 * �����ص�״̬Ϊ����
	 */
	public static final int ALARM = 1;
	/**
	 * �����ص�״̬Ϊ����
	 */
	public static final int NORMAL = 2 ;
	
	/**
	 * �����ص�����״̬Ϊ�Ͽ�
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	/**
	 * �����ص�����״̬Ϊ������
	 */
	public static final int  CONNECTION = 2 ;
	
	/**
	 * ���ݷ�֧������branchId ����ȡ�û����µ����г���ʵʱ����
	 * @param branchId		��֧����Id
	 * @return				����ʵʱ���ݼ���
	 */
	public List<TbccBaseRealCar> getRealData(Long branchId) ;
	
	/**
	 * ������Ŀ���̵ı�ţ���ȡ�ó��ص�ʵʱ���� ,���ڵ�ͼ��ʾ
	 * @param projectId			���̱��
	 * @return					�������ص�ʵʱ����
	 */
	public TbccBaseRealCar getDataToMap(String projectId) ;
	
}
