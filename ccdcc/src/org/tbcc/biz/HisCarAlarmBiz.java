package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCarAlarm;

public interface HisCarAlarmBiz {
	/**
	 * ���ݹ��̱�ʶ��ʱ�䷶Χ����ͣ��¼Id��ȡ������ʷ��������
	 * @param proId			���̱�ʾ
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param sid			��ͣ��¼�ı�ʾ
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> insertAndGetHisCarAlarm(String proId,String startTime,String endTime ,Long sid) ;
	
	
	/**
	 * ���ݹ��̱�ʶ����ͣ��¼Id����ȡ������ʷ��������
	 * @param proId			���̱�ʾ
	 * @param sid			��ͣ��¼�ı�ʾ
	 * @return
	 */
	public List<TbccBaseHisCarAlarm> getHisCarAlarm(String proId,Long sid) ;
	
	
	/**
	 * ���±��泵����ʷ��������
	 * @param caralarmStr			��Ҫ�����޸ĵļ���������ʷ�����������ݶ���ƴ�ӵ��ַ���
	 * @return
	 */
	public boolean updateHisCarAlarm(String caralarmStr) ;
}
