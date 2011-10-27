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
 * ����Ϊ�˽��flex ��spring���ɡ�������ķ���
 * ͨ��spring��������ҵ��㷽�� hisrefBiz
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
	 * ���ݻ���Id����ȡ��������е������Ϣ
	 * @param branchId
	 * @return
	 */
	public List<TbccRefInfo> getRefList(Long branchId){
		return this.hisrefBiz.getRefListByBranchId(branchId) ;
	}
	
	/**
	 * ��������ʶId,��ȡ������̽ͷ����
	 * @param id
	 * @return
	 */
	public List<TbccAiInfo> getAiList(long id){
		return this.hisrefBiz.getAiListByProperty(id);
	}
	
	/**
	 * ����������ȡ�����ʷ������Ϣ�����ڻ�������
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
	 * ���ݷ�֧�ı�ʶId����ȡ�÷�֧�µ�������⹤��
	 * @param branchId	��֧�ı�ʶId
	 * @return
	 */
	public List<TbccPrjType> getRefProj(Long branchId){
		return projectBiz.getRefProjListProxyByBId(branchId) ;
	}
	
	/**
	 * ������⹤�̵ı�ʶId����ȡ�ù����µ����������Ϣ
	 * @param projectId		��⹤�̱�ʶ
	 * @return
	 */
	public List<TbccRefInfo> getRefListByPid(String projectId){
		return refInfoBiz.getRefListByPrj(projectId);
	}
}
