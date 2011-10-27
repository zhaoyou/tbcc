package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisCarAlarm;

public interface HisCarAlarmDao {
	/**
	 * ������ͣ��¼��ʾId��ʱ�䷶Χ��ʱ��������ȡ������ʷ��������
	 * @param tableName		������ʷ���ݱ�
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param sid			��ͣ��¼��ʾ
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarAlarmBySidAndTime(String tableName,String startTime,String endTime,Long sid) ;
	
	/**
	 * ������ͣ��¼��ʾId����ʼʱ�䣬���̱�š���ȡ������ʷ����������Ϣ
	 * @param projectId		���̱��
	 * @param startTime		��ʼʱ��
	 * @param sid			��ͣ��¼��ʾ
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> getHisAlarm(String projectId,Long sid);
	
	/**
	 * ������ʷ����������Ϣ
	 * @param caralarm		��ʷ����������Ϣ
	 * @return
	 */
	public boolean insertHisAlarm(TbccBaseHisCarAlarm caralarm);
	
	/**
	 * ������ʷ����������Ϣ
	 * @param id		��ʷ����������Ϣ��ʶID
	 * @param cause		��ʷ����������Ϣ�ı���ԭ��
	 * @param measure	��ʷ����������Ϣ�Ĵ����ʩ
	 * @param dutier	��ʷ����������Ϣ�ĸ�����
	 * @param remark	��ʷ����������Ϣ�ı�ע
	 * @return
	 */
	public boolean updateHisAlarm(Long id,String cause,String measure,String dutier,String remark);
}
