package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisCar;

/**
 * ����һ�������ƶ�������ʷ���ݵĽӿ�
 * @author Administrator
 *
 */
public interface HisCarDao {
	/**
	 * ����������ѯ�ƶ����ص���ʷ����
	 * @param tableName				���ض�Ӧ����ʷ���ݱ�
	 * @param startTime				��ʼʱ��
	 * @param endTime				����ʱ��
	 * @param value					������ʷ���ݼ���
	 * @return
	 */
	public  List getHisCarData(String tableName,String startTime,String endTime,int value);  

	/**
	 * ������ͣ��¼��ʾId��ʱ�䷶Χ��ʱ��������ȡ������ʷ����
	 * @param tableName		������ʷ���ݱ�
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param value			ʱ����
	 * @param sid			��ͣ��¼��ʾ
	 * @return
	 */
	public List<TbccBaseHisCar> getHisCarDataBySidAndTime(String tableName,String startTime,String endTime,int value,Long sid) ;
	
	
	/**
	 * ������ʷ��ͣ��¼Id������ʷ���ݱ�����ȡ��ʷ����
	 * @param tableName		����
	 * @param parentId		��ͣ��¼�ı�ʶid
	 * @return				������ʷ���ݼ���
	 */
	public List<TbccBaseHisCar> getHisCarByStartUp(String tableName,Long parentId);
	
	/**
	 * ������ͣ��¼id����Ŀ���̡��Լ���ǰ������ʷ���ݼ�¼��Ż�ȡ����
	 * @param tableName		����
	 * @param parentId		��ͣ��¼�ı�ʶId
	 * @param id			��ʷ��¼���
	 * @return				������ʷ���ݼ���
	 */
	public List<TbccBaseHisCar> getHisCarByStartUpAndId(String tableName,Long parentId,Integer id) ;

}
