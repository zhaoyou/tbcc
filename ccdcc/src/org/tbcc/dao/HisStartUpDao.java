package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * �������ͣ���������ݽӿ�
 * @author Administrator
 *
 */
public interface HisStartUpDao {
	/**
	 * �����ƶ��ն���ʷ��ͣ����ʼʱ�䡢����ʱ�䣬��ȡ��ͣ��¼
	 * @param tableName		�ն���ʷ��ͣ��
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	public List getStartUpList(String tableName,String startTime,String endTime);
	
	/**
	 * �����ƶ��ն˵���ʷ��ͣ���ͱ�ʶ������ȡ��ͣ��¼
	 * @param tableName		�ն���ʷ��ͣ��
	 * @param id			��ͣ��¼�ı�ʶid
	 * @return
	 */
	public List getStartUp(String tableName,long id) ;
	
	
	/**
	 * �����ƶ��ն˵ı���������ʼʱ���ȡ��ͣ��¼ version 2
	 * @param tableName		�ƶ��ն˱���
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,String startTime,String endTime) ;
	
	/**
	 * �����ƶ��ն˵ı��� ������ͣ��¼�ı�ʶId��ȡ��ͣ��¼
	 * @param tableName
	 * @param id
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpById(String tableName,long id) ;
	
	
	/**
	 * ���ݿ�ʼʱ���ȡ��ͣ��¼��ʶID(��ĿFDAP�ڵ�ͼ�Ͽ��鿴��ʷ�켣ʱ��Ҫ�õ���)
	 * @param tableName			�����ƶ��ն˱�����
	 * @param beginTime			��ͣ��¼��ʼʱ��
	 * @return
	 */
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime);
}
