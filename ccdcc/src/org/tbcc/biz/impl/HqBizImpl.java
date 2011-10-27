package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HqBiz;
import org.tbcc.dao.HqDao;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccHqType;

/**
 * �ܲ���ҵ�����ʵ����
 * @author Administrator
 *
 */
public class HqBizImpl implements HqBiz {
	
	private HqDao hqDao = null ;
	
	
	
	public void setHqDao(HqDao hqDao) {
		this.hqDao = hqDao;
	}

	public TbccHqType getById(Long id) {
		if(id==null || id.equals(""))
			return  null ;
		return hqDao.get(id);
	}

	public List<TbccHqType> getByParentId(Long parentId) {
		if(parentId==null || parentId.equals(""))
				return null ;
		return hqDao.getByParentId(parentId);
	}

	public List<TbccHqType> getAllHqType() {
		return hqDao.getAll();
	}

	public String getHqBranchTree(Long id) {	
		if(id==null || id.equals(""))
			return "";
			
		TbccHqType hqtype = hqDao.get(id) ;
		
		if(hqtype==null)
			return "" ;
		
		List<TbccHqType> hqlist = hqDao.getAll() ;
		
		if(hqlist==null || hqlist.size()==0)
			return "" ;
		
		StringBuffer sb = new StringBuffer("");
		
		
		sb.append("d.add("+hqtype.getHqId()+",0,'"+hqtype.getHqName()+"');\n");
		//���ظ��ܲ��µ�ֱ��
		addTreeLeaf(hqtype.getHqId(), sb);
		//���ظ��ܲ��µ��ܲ���֧
		addTree(hqlist, hqtype.getHqId(), sb);
		return sb.toString();
	}
	
	/**
	 * ����һ���ܲ����Ľڵ�
	 * @param list		���е��ܲ�����
	 * @param id		��ǰ�ܲ��ı�ʶId
	 * @param sb		�������νڵ�Ľ����	d.add(nodeId,nodeParentId,nodeName)
	 * @return
	 */
	public StringBuffer addTree(List<TbccHqType> list ,Long id,StringBuffer sb){
		
		if(list==null || list.size()==0 || id==null || id.equals("")|| sb==null)
			return sb ;
		
		
		for (int i = 0 ;i<list.size();i++) {
			TbccHqType hqtype = list.get(i) ;
			if(hqtype.getHqParentId().equals(id)){
				
				sb.append("d.add("+hqtype.getHqId()+","+id+",'"+hqtype.getHqName()+"');\n");
				//�ݹ�����ܲ�
				addTree(list, hqtype.getHqId(), sb);
				//���ε��÷�֧
				addTreeLeaf(hqtype.getHqId(), sb);
			}
		}
			
		return sb ;
	}
	
	
	
	/**
	 * ����һ���ܲ��������е�Ҷ�ڵ�
	 * @param id	��ǰ�ܲ��ı�ʶId
	 * @param sb	�������νڵ�Ľ����	d.add(nodeId,parentNodeId,nodeName,url);
	 * @return
	 */
	public StringBuffer addTreeLeaf(Long id ,StringBuffer sb){
		if(id==null || id.equals(""))
			return sb ;
		
		List<TbccBranchType> list = hqDao.getBranchByHqId(id) ;
		
		if(list==null || list.size()==0)
			return sb ;
		
		//������ֱ�ӵ���JavaScript����: +"','branch.do?ope=toListByHqAndBranch&branchId="+tbccBranchType.getBranchId()+"');\n"
		for (TbccBranchType tbccBranchType : list) {
			sb.append("d.add("+tbccBranchType.getBranchId()*2000+","+id+",'"+tbccBranchType.getBranchName()+"',\"javascript:gobranch('branch.do','toHqMain',"+tbccBranchType.getBranchId()+")\");\n");
		}
		return sb ;
	}

}
