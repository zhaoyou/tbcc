package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.config.TbccParaVehicleAlarm;

/**
 * ���ǳ��ز������õ����ݽӿ�
 * @author zhaoyou
 *
 */
public interface ParamCarVehicleDao {
	
	
	
	/**
	 * ���ݲ��������ı�ʾId����ȡ���ز���������Ϣ
	 * @param parentId	������ʾId
	 * @return			���ز�������
	 */
	public List<TbccParaVehicleAlarm> getByPid(Long parentId) ;	
	
	/**
	 * ���³��ص�������Ϣ
	 * @param sql		��Ҫִ�еĸ������
	 * @return			������״̬
	 */
	public	Integer updateParamVehicle(String sql);
}
