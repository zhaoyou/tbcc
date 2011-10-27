package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBranchUserAlarmLogs;

/**
 * ��֧�û���¼������־ҵ��ӿ�
 * @author zhaoyou
 *
 */
public interface BranchUserAlarmLogsBiz {
	
	/**
	 * �����־
	 * @param logs
	 */
	public void addLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * ������־
	 * @param logs
	 */
	public void updateLogs(TbccBranchUserAlarmLogs logs) ;
	
	/**
	 * ��ȡ������֧�£��ֿ�ı���״̬
	 * @param branchId	��֧��ʶId
	 * @return
	 */
	public Integer getStoreSysAlarmState(Long branchId) ;
	
	/**
	 * ��ȡ������������־��������
	 * @param clientName		�˻���
	 * @param userName			�û���
	 * @return
	 */
	public Integer 	getAllLogsNumber(String clientName ,String userName) ;
	
	/**
	 * ��ҳ��ȡĳ���û�����־��Ϣ
	 * @param clientName		�˻���
	 * @param userName			�û���
	 * @param index				��ǰҳ��
	 * @param number			ÿҳ������
	 * @return
	 */
	public List<TbccBranchUserAlarmLogs> getLogs(String clientName,String userName ,Integer index ,Integer number) ;
}
