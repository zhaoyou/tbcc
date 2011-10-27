package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RealBoxBiz;
import org.tbcc.dao.BranchDao;
import org.tbcc.dao.RealBoxDao;
import org.tbcc.entity.TbccBaseRealBox;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;

/**
 * 实时小批零的业务实现类
 * @author Administrator
 *
 */
public class RealBoxBizImpl implements RealBoxBiz {
	
	private RealBoxDao realboxDao = null ;
	
	private BranchDao branchDao = null ;
	
	public void setRealboxDao(RealBoxDao realboxDao) {
		this.realboxDao = realboxDao;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	
	public List<TbccBaseRealBox> getRealBox(Long branchId) {
		
		if(branchId==null || branchId.equals(""))
			return null ;
		
		TbccBranchType branchType = this.branchDao.get(branchId);
		
		
		if(branchType!=null){
			
			String condition = "(" ;     //定义查询的字符串
			
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//把该机构下的所有移动车载加入到集合
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX))
						condition += "'"+tbccPrjType.getProjectId()+"'," ;
				}
				
				condition = condition.substring(0, condition.length()-1) ;  //去掉最后一个,
				condition +=")" ;											//加上最后一个括号)
				
				//获取所有的车载数据
				
				return this.realboxDao.getRealboxData(condition);
				
			}
		}
		return null;
	}

}
