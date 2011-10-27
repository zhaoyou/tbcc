package org.tbcc.biz;

import org.tbcc.log.entity.TbccLog;



/**
 * ���ز�������ҵ��ӿ�
 * @author zhaoyou
 *
 */
public interface ParamCarVehicleBiz {
	/**
	 * ���ݶ��������ı�ʾid����ȡ�ó��ض�Ӧ�Ĳ�����Ϣ
	 * @param id		��������id
	 * @return			����������Ϣ eg: name,valid,value;name,valid,value
	 * name:��ʾ�ֶα�ʾ
	 * valid:��ʾ�Ƿ���Ч(0:��Ч 1:��Ч)
	 * value:��ʾ�ֶ�ֵ ( �����Ч����N/A����)
	 */
	public String getCarVehicleByParentId(Long pid);
	
	/**
	 * ���³��صĲ�����Ϣ
	 * @param projectId		��Ҫ���µĳ��ر�ʾ
	 * @param source		���ز�����Ϣ: eg: name,valid,value;name,valid,value
	 * @param log			���Ӳ���������־
	 * @return				���²����ı�ʾ 
	 */
	public Integer updateCarVehicle(String projectId,String source,TbccLog log);
	
	/**
	 * �������Ӷ������������־
	 * @param log			���������־
	 * @return				������״̬
	 */
	public Integer addUpdateResultLog(TbccLog log);
	
	
	
	
}
