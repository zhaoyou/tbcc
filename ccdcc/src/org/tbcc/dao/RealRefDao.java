package org.tbcc.dao;

import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccRefInfo;

/**
 * �������ʵʱ���ݵķ��ʽӿ�
 * @author Administrator
 *
 */
public interface RealRefDao {
	/**
	 * ������⹤�̱�ʶId����ȡ�ù��̵�ʵʱ����
	 * @param projectId		��⹤�̱�ʶId
	 * @return
	 */
	public List<TbccBaseRealRef> getRealRefData(String projectId);
	
	/**
	 * ������⹤�̱�ţ���ȡ��Ӧ��⹤�̶�Ӧ��¥����Ϣ
	 * @param projects	��⹤�̱�ż���
	 * @return
	 */
	public List<Map<String, Object>> getFloorInfo(List<String> projects);
	
	/**
	 * ���ݹ��̱�š�¥�����͡�¥���Ż�ȡ����б�
	 * @param projectId			���̱��
	 * @param floorType			¥������
	 * @param floorNo			¥����
	 * @return					�������̡�¥�������б�
	 */
	public List<TbccRefInfo> getRefByPidAndFloorInfo(String projectId,Integer floorType,Integer floorNo); 
	
	/**
	 * ��������ʾ���ϣ���ȡ��Ӧ��������̽ͷ�б�
	 * @param refIds		����ʾ����
	 * @return
	 */
	public List<TbccAiInfo> getAiByRefId(List<Long> refIds);
	
	
	/**
	 * ͨ�����̱�ʾ��ȡ�ù��̵�ϵͳ����,Ĭ�������ڵ�һ���豸�� netId = 1 
	 * @param projectId			���̱�ʾId
	 * @return					���ʵʱ���ݼ���
	 */
	public List<TbccBaseRealRef	> getRealRefSysData(String projectId);
}
