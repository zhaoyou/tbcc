package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccCrm;

/**
 * 这是一个客户关系表、为了之前解决总部查看分支机构的信息，所引用的。
 * @author Administrator
 *
 */
public interface CrmDao {
	
	/**
	 * 根据客户的branchId，获取该机构的客户的branchId
	 * @param hid		客户branchId
	 * @return			客户关系实体
	 */
	List<TbccCrm> getByHid(String hid) ;
	
}
