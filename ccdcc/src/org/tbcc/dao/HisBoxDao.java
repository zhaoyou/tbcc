package org.tbcc.dao;

import java.util.List;

/**
 * С��������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface HisBoxDao {
	/**
	 * ����С�������ʷ���ݱ���ͣ�Ŀ�ʼʱ�䡢����ʱ�䡢�Լ���ѯ�����ȡС������ʷ����
	 * @param tableName			С������ʷ���ݱ�
	 * @param startTime			��ͣ��¼�Ŀ�ʼʱ��
	 * @param endTime			��ͣ��¼�Ľ���ʱ��
	 * @param value				��ѯ�ļ��
	 * @return
	 */
	public List getHisBoxData(String tableName ,String startTime,String endTime,int value ) ;
}
