package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBranchUserAlarmLogs;

/**
 * ��֧�û���¼������Ϣ���ݽӿ�
 * @author zhaoyou
 *
 */
public interface BranchUserAlarmLogsDao {
	
	/**
	 * ����һ��������־
	 * @param logs
	 */
	public void insertLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * ���±�����־��Ϣ
	 * @param logs
	 */
	public void updateLogs(TbccBranchUserAlarmLogs logs);
	
	/**
	 * ��ȡ������־������
	 * @param clientName
	 * @param userName
	 * @return
	 */
	public Integer getAllNumber(String clientName ,String userName ) ;
	
	/**
	 * ÿ�λ�ȡ��־������
	 * @param clientName		�˻���
	 * @param userName			�û���
	 * @param index				��ǰҳ��
	 * @param number			ÿҳ������
	 * @return
	 */
	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,String userName,Integer index ,Integer number) ;
	

}
