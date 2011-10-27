package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HqBiz;
import org.tbcc.dao.HqDao;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccHqType;

/**
 * 总部的业务操作实现类
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
		//加载该总部下的直属
		addTreeLeaf(hqtype.getHqId(), sb);
		//加载该总部下的总部分支
		addTree(hqlist, hqtype.getHqId(), sb);
		return sb.toString();
	}
	
	/**
	 * 增加一个总部级的节点
	 * @param list		所有的总部集合
	 * @param id		当前总部的标识Id
	 * @param sb		保存树形节点的结果集	d.add(nodeId,nodeParentId,nodeName)
	 * @return
	 */
	public StringBuffer addTree(List<TbccHqType> list ,Long id,StringBuffer sb){
		
		if(list==null || list.size()==0 || id==null || id.equals("")|| sb==null)
			return sb ;
		
		
		for (int i = 0 ;i<list.size();i++) {
			TbccHqType hqtype = list.get(i) ;
			if(hqtype.getHqParentId().equals(id)){
				
				sb.append("d.add("+hqtype.getHqId()+","+id+",'"+hqtype.getHqName()+"');\n");
				//递归调用总部
				addTree(list, hqtype.getHqId(), sb);
				//依次掉用分支
				addTreeLeaf(hqtype.getHqId(), sb);
			}
		}
			
		return sb ;
	}
	
	
	
	/**
	 * 增加一个总部下面所有的叶节点
	 * @param id	当前总部的标识Id
	 * @param sb	保存树形节点的结果集	d.add(nodeId,parentNodeId,nodeName,url);
	 * @return
	 */
	public StringBuffer addTreeLeaf(Long id ,StringBuffer sb){
		if(id==null || id.equals(""))
			return sb ;
		
		List<TbccBranchType> list = hqDao.getBranchByHqId(id) ;
		
		if(list==null || list.size()==0)
			return sb ;
		
		//不允许直接调用JavaScript方法: +"','branch.do?ope=toListByHqAndBranch&branchId="+tbccBranchType.getBranchId()+"');\n"
		for (TbccBranchType tbccBranchType : list) {
			sb.append("d.add("+tbccBranchType.getBranchId()*2000+","+id+",'"+tbccBranchType.getBranchName()+"',\"javascript:gobranch('branch.do','toHqMain',"+tbccBranchType.getBranchId()+")\");\n");
		}
		return sb ;
	}

}
