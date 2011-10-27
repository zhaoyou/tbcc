package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.cool.TbccCcapDevType;

/**
 * 制冷设备业务接口
 * @author Administrator
 *
 */
public interface CCapDevBiz {
	
	/**
	 * 根据工程标识Id获取制冷设备记录
	 * @param projectId
	 * @return
	 */
	public List<TbccCcapDevType> getByProjectId(String projectId) ;
	
	/**
	 * 根据多个冷库工程获取多个制冷设备
	 * @param str	eg: ('1001','1002')
	 * @return
	 */
	public List<TbccCcapDevType> getByProjects(String str) ;
	
}
