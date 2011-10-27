package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.util.BuildTable;
import org.tbcc.util.ObjectToHistory;

/**
 * ������ʷ��ͣ����ҵ��ʵ����
 * @author Administrator
 *
 */
public class HisStartUpBizImpl implements HisStartUpBiz{

	/**
	 * ��spring ע����ͣ���ݷ��ʽӿ�
	 */
	private HisStartUpDao startupDao = null ;
	
	private ObjectToHistory objToHis = null ;
	
	public void setStartupDao(HisStartUpDao startupDao) {
		this.startupDao = startupDao;
	}
	
	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	
	/**
	 * ����������ȡ��ͣ��¼
	 */
	public List<TbccBaseHisStartUp> getStartUpList(String proId,
			String startTime, String endTime) {
		
		List list = this.startupDao.getStartUpList(BuildTable.toHisStartUpTable(proId), startTime, endTime);
		if(list!=null && list.size()>0)
			return  objToHis.objectToStartUp(list);
		return null;
		
	}

	/**
	 * ������Ŀ��������ͣid����ȡ������¼
	 */
	public TbccBaseHisStartUp getStartUp(String proId, long id) {
		List list = this.startupDao.getStartUp(BuildTable.toHisStartUpTable(proId), id);
		List<TbccBaseHisStartUp> startupList =	objToHis.objectToStartUp(list);
		if(startupList!=null && startupList.size()>0)
			return startupList.get(0);
		return null;
	}

	
	
	public TbccBaseHisStartUp getStartUpById(String projectId, Long id) {
		if(projectId==null || projectId.equals("") || id==null || id.equals(""))
			return null ;
		List<TbccBaseHisStartUp> list = 
			this.startupDao.getStartUpById(BuildTable.toHisStartUpTable(projectId), id) ;
		
		if(list==null || list.size()==0)
			return null ;
		return list.get(0);
	}

	
	public List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,
			String beginTime, String endTime) {
		
		if(projectId ==null || projectId.equals("") || beginTime==null || beginTime.equals("")
			||	endTime==null || endTime.equals(""))
			return null ;
		
		List<TbccBaseHisStartUp> list = this.startupDao.getStartUpListByTime(BuildTable.toHisStartUpTable(projectId), beginTime, endTime) ;
		
		if(list==null || list.size()==0)
			return null ;
		
		return list;
	}

	
	public Long getStartupidByBeginTime(String projectId,String beginTime) {
		if(projectId ==null || projectId.equals("") || beginTime==null || beginTime.equals(""))
				return null ;
		List<Long> list = startupDao.queryStartupidByBeginTime(BuildTable.toHisStartUpTable(projectId), beginTime);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	
}
