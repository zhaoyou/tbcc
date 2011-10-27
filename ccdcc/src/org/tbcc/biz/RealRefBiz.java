package org.tbcc.biz;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;

/**
 * ��������ʵʱ����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface RealRefBiz {
	/**
	 * ������������״̬Ϊֹͣ 1
	 */
	public static final int STOP  = 1 ;
	/**
	 * ������������״̬Ϊ���� 2
	 */
	public static final int RUN = 2 ;
	/**
	 * ��������״̬ΪԤ�� 0
	 */
	public static final int PREALARM = 0 ;
	/**
	 * ��������״̬Ϊ���� 1
	 */
	public static final int ALARM = 1 ;
	/**
	 * ��������״̬Ϊ���� 2
	 */
	public static final int NORMAL = 2 ;
	
	
	/** 
	 * ������������״̬Ϊ�Ͽ� 1
	 */
	public static final int  DISCONNECTION = 1 ;
	
	
	/**
	 * ������������״̬Ϊ������ 2
	 */
	public static final int  CONNECTION = 2 ;
	
	/**
	 * ����һ����⹤�̵ı�ʶId����ȡ������ʵʱ����
	 * @param projectId		����ʶ
	 * @return
	 */
	public List<TbccBaseRealRef> getRealRefData(String projectId);
	
	/**
	 * ���ݻ����ı�ʶId����ȡ��⹤�̵ı�ʶId������ԭ�����һ����֧��Ĭ��ֻ��һ����⹤�̵������
	 * @param branchId		��֧�ı�ʶId
	 * @return				��⹤�̵ı�ʶId
	 */
	public String getRefPrjId(Long branchId);
	
	/**
	 * 	���ݻ����ı�ʶId����ȡ������֧����
	 * @param branchId		�����ı�ʶId
	 * @return			 	���ػ�������
	 */
	public TbccBranchType getById(Long branchId) ;
	
	/**
	 * ���ݲ�ͬ�Ĳֿ⹤�̣������вֿ�¥����Ϣ
	 * @param list		�ֿ⹤���б�
	 * @return			���ع����б�¥���š�¥�����͡�¥������
	 */
	public List<Map<String, Object>> getRefFloorInfo(List<TbccPrjType> list) ;
	
	/**
	 * ͨ��¥����Ϣ��ȡ����б�
	 * @param projectId			���̱�ʾ
	 * @param floorType			¥������
	 * @param floorNo			¥����
	 * @return					����б�
	 */
	public List<TbccRefInfo> getRefByFloorInfo(String projectId,Integer floorType,Integer floorNo) ;
	
	
	/**
	 * ������⼯��
	 * @param list		��⼯��
	 * @return			����Ӧ��̽ͷ����
	 */
	public List<TbccAiInfo> getAiByRef(List<TbccRefInfo> list) ;
	
	/**
	 * ͨ�����̱�ʾId����ȡ�ֿ��ϵͳʵʱ����
	 * @param projectId		�ֿ⹤�̱�ʾ
	 * @return				����ϵͳʵʱ���ݼ��� ����״̬���ϵ籨�������ⱨ��
	 */
	public String[] getRealRefSystemData(String projectId) ;
	
	
	
}
