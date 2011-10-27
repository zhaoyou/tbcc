package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccCrm;

/**
 * 客户关系的业务接口
 * @author Administrator
 *
 */
public interface CrmBiz {
		/**
		 * 根据机构的标识Id，获取相关联的客户
		 * @param hid		客户机构标识Id
		 * @return
		 */
		public List<TbccCrm> getByHid(String hid) ;
}
