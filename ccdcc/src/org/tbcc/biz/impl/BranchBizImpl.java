package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.BranchBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.entity.TbccBranchType;

/**
 * 这是分支操作业务实现类
 * @author Administrator
 *
 */
public class BranchBizImpl implements BranchBiz {
	
	private BranchDao branchDao = null ;

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	
	
	public TbccBranchType getById(Long branchId) {
		TbccBranchType type =  this.branchDao.get(branchId);
		if(type==null)
			throw new RuntimeException("该机构不存在");
		return type ;
	}
	
	
	public List<TbccBranchType> getByIds(List<String> ids) {
		StringBuffer str = new StringBuffer("(") ;
		for (int i = 0; i < ids.size(); i++) {
			str.append(ids.get(i)) ;
			
			if(i!=ids.size()-1)
				str.append(",") ;
		}
		str.append(")");
		return this.branchDao.getByIds(str.toString());
	}
}
