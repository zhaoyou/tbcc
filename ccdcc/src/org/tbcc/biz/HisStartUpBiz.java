package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * ��ʷ��ͣҵ������ӿ�
 * @author Administrator
 *
 */
public interface HisStartUpBiz {
	/**
	 * û���ϴ�
	 */
	public static final int NOUP = 0 ;
	
	/**
	 * û�����
	 */
	public static final int NOFINISH =1 ;
	
	/**
	 * �Ѿ����
	 */
	public static final int FINISH = 2 ;
	
	/**
	 * û��ֹͣ
	 */
	public static final int NOSTOP = 3 ;
	
	List<TbccBaseHisStartUp> getStartUpList(String proId,String beginTime,String endTime);
	
	TbccBaseHisStartUp getStartUp(String proId,long id);
	
	/**
	 * ���ݹ��̱�ʶ����ʼʱ���ȡ��ͣ��¼
	 * @param projectId		���̱�ʶ
	 * @param beginTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,String beginTime,String endTime) ;
	
	/**
	 * ���ݹ��̱�ʶ����ͣ��ʶ��ȡ��ͣ��¼
	 * @param projectId		���̱�ʶ
	 * @param id			��ͣ��ʶ
	 * @return
	 */
	TbccBaseHisStartUp getStartUpById(String projectId,Long id) ;
	
	/**
	 * ���ݿ�ʼʱ���ȡ��ͣ��¼��ʶID(��ĿFDAP�ڵ�ͼ�Ͽ��鿴��ʷ�켣ʱ��Ҫ�õ���)
	 * @param projectId		���̱�ʶID
	 * @param beginTime		��ͣ��¼��ʼʱ��
	 * @return
	 */
	Long getStartupidByBeginTime(String projectId,String beginTime);
}
