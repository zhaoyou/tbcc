package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;

/**
 * ������ʷ�������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface HisRefDao {
	/**
	 * 	���������ʷ���ݱ���ʼʱ�䡢����ʱ�䡢�Լ�ʱ������ѯ�����ʷ����
	 * @param tableName			�����ʷ���ݱ���
	 * @param startTime			��ʼʱ��
	 * @param endTime			����ʱ��
	 * @param value				ʱ����
	 * @return
	 */
	public List getHisRefData(String tableName,String startTime,String endTime,int value);

	/**
	 * ���������ʷ���ݱ���ʼʱ�䡢����ʱ�䡢�Ѿ�ʱ������ѯ�����ʷ����
	 * @param tableName		�����ʷ���ݱ�
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param value			ʱ����
	 * @return
	 */
	public List<TbccBaseHisRef> getHisDataByTime(String tableName,String startTime,String endTime,int value) ;
	
	/**
	 * ���������չ��ʷ���ݱ���ʼʱ�䡢����ʱ�䡢ʱ������ѯ�����ʷ����
	 * @param tableName		�����չ��ʷ���ݱ�
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param value			ʱ����ֵ
	 * @return
	 */
	public List<TbccBaseHisRef_Ex> getExHisDataByTime(String tableName,String startTime,String endTime,int value);
}
