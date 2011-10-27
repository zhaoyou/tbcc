package org.tbcc.biz;

import org.tbcc.entity.TbccDevType;

/**
 * 这个是描述设备的业务接口
 * @author Administrator
 *
 */
public interface DevTypeBiz {
	/**
	 * 大板设备 1
	 */
	public static final int RI300 = 1 ;
	/**
	 * 小板设备 2
	 */
	public static final int RI300E = 2 ;
	
	/**
	 * RI300S车载版 0
	 */
	public static final int RI300S = 0 ;
	
	/**
	 * 设备扩展应用 3
	 */
	public static final int EXAPP = 3 ;
	
	/**
	 * 通过标识Id，获取设备
	 * @param id		标识Id
	 * @return
	 */
	public TbccDevType getById(Long id) ;
}
