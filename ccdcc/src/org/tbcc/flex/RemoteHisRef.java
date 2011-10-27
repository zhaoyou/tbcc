package org.tbcc.flex;

import java.util.List;
import java.util.Map;

import org.tbcc.biz.HisRefBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.MySpringFactory;

/**
 * 这是为了解决flex 与spring集成。而解决的方案
 * 通过spring容器访问业务层方法 hisrefBiz
 * @author Administrator
 *
 */
public class RemoteHisRef {
		
	private HisRefBiz hisrefBiz = null ;
	
	private ProjectBiz projectBiz = null  ;
	
	private RefInfoBiz	refInfoBiz = null ;
	
	public RemoteHisRef(){
		hisrefBiz = (HisRefBiz)MySpringFactory.getInstance().getBean("hisrefBiz");
		projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
		refInfoBiz  = (RefInfoBiz)MySpringFactory.getInstance().getBean("refInfoBiz") ;
	}
	
	/**
	 * 根据机构Id，获取下面的所有的冷库信息
	 * @param branchId
	 * @return
	 */
	public List<TbccRefInfo> getRefList(Long branchId){
		return this.hisrefBiz.getRefListByBranchId(branchId) ;
	}
	
	/**
	 * 根据冷库标识Id,获取该冷库的探头集合
	 * @param id
	 * @return
	 */
	public List<TbccAiInfo> getAiList(long id){
		return this.hisrefBiz.getAiListByProperty(id);
	}
	
	/**
	 * 根据条件获取冷库历史数据信息，用于绘制曲线
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	public List<Map> getRefHisData(Long id,String startTime,String endTime,String type,String value){
		return this.hisrefBiz.getHisRefInfo(id, startTime, endTime, type, value);
	}
	
	/**
	 * 根据分支的标识Id，获取该分支下的所有冷库工程
	 * @param branchId	分支的标识Id
	 * @return
	 */
	public List<TbccPrjType> getRefProj(Long branchId){
		return projectBiz.getRefProjListProxyByBId(branchId) ;
	}
	
	/**
	 * 根据冷库工程的标识Id，获取该工程下的所有冷库信息
	 * @param projectId		冷库工程标识
	 * @return
	 */
	public List<TbccRefInfo> getRefListByPid(String projectId){
		return refInfoBiz.getRefListByPrj(projectId);
	}
}
