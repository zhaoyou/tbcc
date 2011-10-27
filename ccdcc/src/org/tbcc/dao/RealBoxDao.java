package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealBox;

/**
 * 实时小批零数据访问接口
 * @author Administrator
 *
 */
public interface RealBoxDao {
	
	/**
	 * 根据小批零标识Id集合，获取小批零实时数据集合
	 * @param condition	eg: 	(12,13,13)
	 * @return
	 */
	public  List<TbccBaseRealBox> getRealboxData(String condition) ;
}
