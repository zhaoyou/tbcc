package org.tbcc.biz;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;

/**
 * ���������ʷ����ҵ����ʽӿ�
 * @author Administrator
 *
 */
public interface HisRefBiz {
	/**
	 * �������ΪԤ��״̬
	 */
	public static final int PREALARM = 0 ;
	/**
	 * ���Ϊ����״̬
	 */
	public static final int ALARM = 1 ;
	
	/**
	 * ���Ϊ����״̬
	 */
	public static final int NORMAL = 2 ;
	
	
	
	/**
	 * ���ݸ��ӷ�֧������ʶId����ȡ���������Ϣ�б����������������ڵ�����⹤�̶��Ե�
	 * @param branchId	������֧��ʶ
	 * @return			��֧��ʶ�µ�����⹤���µ���������б�
	 */
	public List<TbccRefInfo> getRefListByBranchId(Long branchId);
	
	/**
	 * ��������ʶId����ȡ��Ӧ��̽ͷ
	 * @param id
	 * @return
	 */
	public List<TbccAiInfo>	getAiListByProperty(Long id);
	
	/**
	 * ����������ȡ�����ʷ���ݣ����ڻ�������
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	public List<Map> getHisRefInfo(Long id,String startTime,String endTime,String type,String value); 
	
	
	
	
	
	

	
	
	/**
	 * ��ȡ�ֿ���ʷ���ݣ�������֮ǰ�İ汾���豸��չ�汾
	 * @param id			����ʶId
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param type			ʱ������
	 * @param value			ʱ����
	 * @return		
	 */
	public List<Object> getRefHisData(Long id,String startTime,String endTime,String type,String value);
	

	
	/***�����չ�豸����ʷ����***/

	
	
}
