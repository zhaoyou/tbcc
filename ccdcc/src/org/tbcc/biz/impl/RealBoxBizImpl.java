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
 * ʵʱС�����ҵ��ʵ����
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
			
			String condition = "(" ;     //�����ѯ���ַ���
			
			if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
				//�Ѹû����µ������ƶ����ؼ��뵽����
				for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
					if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX))
						condition += "'"+tbccPrjType.getProjectId()+"'," ;
				}
				
				condition = condition.substring(0, condition.length()-1) ;  //ȥ�����һ��,
				condition +=")" ;											//�������һ������)
				
				//��ȡ���еĳ�������
				
				return this.realboxDao.getRealboxData(condition);
				
			}
		}
		return null;
	}

}
