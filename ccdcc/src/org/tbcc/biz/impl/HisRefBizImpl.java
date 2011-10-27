package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tbcc.biz.AiInfoBiz;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.DevTypeBiz;
import org.tbcc.biz.HisRefBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.dao.HisRefDao;
import org.tbcc.dao.RefInfoDao;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccDevType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.BuildTable;
import org.tbcc.util.Calc;
import org.tbcc.util.ObjectToHistory;



/**
 * 这个历史冷库数据操作的业务实现类
 * @author Administrator
 *
 */
public class HisRefBizImpl implements HisRefBiz {

	/**
	 * 由spring容器注入冷库历史操作对象
	 */
	private HisRefDao hisRefDao = null ;
	
	private RefInfoBiz refinfoBiz = null ;
	
	private AiInfoBiz aiinfoBiz = null ;
	
	private BranchBiz branchBiz = null ;
	
	private ObjectToHistory objToHis = null ;
	
	private Calc calc = null ;
	
	private DevTypeBiz devTypeBiz = null ;
	
	
	
	public void setDevTypeBiz(DevTypeBiz devTypeBiz) {
		this.devTypeBiz = devTypeBiz;
	}

	public void setCalc(Calc calc) {
		this.calc = calc;
	}

	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	public void setBranchBiz(BranchBiz branchBiz) {
		this.branchBiz = branchBiz;
	}

	public void setHisRefDao(HisRefDao hisRefDao) {
		this.hisRefDao = hisRefDao;
	}
	
	public void setRefinfoBiz(RefInfoBiz refinfoBiz) {
		this.refinfoBiz = refinfoBiz;
	}

	public void setAiinfoBiz(AiInfoBiz aiinfoBiz) {
		this.aiinfoBiz = aiinfoBiz;
	}
	
	
	
	public List<TbccRefInfo> getRefListByBranchId(Long branchId) {
		
		TbccBranchType branchType = this.branchBiz.getById(branchId);
		
		if(branchType!=null){
			List<TbccRefInfo> refList = new ArrayList<TbccRefInfo>();
			String proId = "";
		if(branchType.getPrjTypes()!=null && branchType.getPrjTypes().size()>0){
			//首先取出项目Id
			for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
				if(tbccPrjType.getProjectType().equals(ProjectBiz.REF)){
					proId = tbccPrjType.getProjectId() ;
					break ; 
				}
					
			}
			refList = this.refinfoBiz.getRefListByPrj(proId);		//根据项目Id，获取所有的冷库项目
			return refList ;
		}
	}
		return null;
	}
	
	
	public List<TbccAiInfo> getAiListByProperty(Long id) {
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		return this.aiinfoBiz.getRefAiList(refinfo.getProjectId(),refinfo.getNetid().toString(), refinfo.getRefid().toString());
	}

	
	public List<Map> getHisRefInfo(Long id, String startTime, String endTime,
			String type, String value) {
		
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		//获取表名
		String tableName = BuildTable.toHisRefTable(refinfo.getProjectId(), refinfo.getNetid().toString());
		int total = Integer.parseInt(value);
		
		//获取总时间秒数
		if(type.equals("1")){			//代表小时
			total = 3600 * total ;
		}else if(type.equals("2"))		//代表分钟
			total = 60 * total ;
		
		//获取该冷库所在netId 的历史数据
		List list = this.hisRefDao.getHisRefData(tableName, startTime, endTime, total); 
		List<Map> refDataList = objToHis.objectToRefCurve(list);
		return refDataList;
	}
	
	
	//*******************老版本仓库获取历史数据******************
	
	
	private List<TbccBaseHisRef> getHisRefData(Long id, String startTime, String endTime, String type,
			String value) {
		TbccRefInfo refinfo = this.refinfoBiz.getById(id);
		//获取表名
		String tableName = BuildTable.toHisRefTable(refinfo.getProjectId(), refinfo.getNetid().toString());
		
		int total = Integer.parseInt(value);
		
		//获取总时间秒数
		if(type.equals("1")){			//代表小时
			total = 3600 * total ;
		}else if(type.equals("2"))		//代表分钟
			total = 60 * total ;
		
		/**
		 * 此处修改了实现方式，直接用实体类返回没有再次转化
		 */
		//获取该冷库所在netId 的历史数据
		//List list = this.hisRefDao.getHisRefData(tableName, startTime, endTime, total); 
	    //List<TbccBaseHisRef> refDataList = objToHis.objectToRef(list);
		
		List<TbccBaseHisRef> refDataList = this.hisRefDao.getHisDataByTime(tableName, startTime, endTime, total);   
				
		return refDataList;
	}
	
	
	
	/**
	 * 之前版本获取仓库历史数据
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	private List<Object> getHisInfo(Long id,String startTime,String endTime,String type,String value) {
		
		List<TbccAiInfo> aiList = this.getAiListByProperty(id);			//获取与该冷库相关的探头
		
		List<TbccBaseHisRef> refDataList = this.getHisRefData(id, startTime, endTime, type, value);		//获取与该冷库相关的历史数据

		//如果没有找到相应的探头、则返回null值,这是后来增加的东西
		if(aiList==null || aiList.size()==0)
				return null ;
		
		
		List<Integer> tportList = new ArrayList<Integer>();		//用于盛放ai控制的实际温度端口
		
		List<Integer> hportList = new ArrayList<Integer>(); 	//用于盛放ai 控制的实际湿度端口
		
		List<Object> result = new ArrayList<Object>(3);			//用于保存返回的数据包装集合
		
		//分别获取温度和湿度端口
		for (TbccAiInfo tbccAiInfo : aiList) {		
			if(tbccAiInfo.getPortNo()>9)
				hportList.add(tbccAiInfo.getPortNo());
			else
				tportList.add(tbccAiInfo.getPortNo());				
		}

		
		List<String[]> aa = new ArrayList<String[]>();				//自定义页面显示文本
		
	
		
		//获取数据
		if(refDataList!=null && refDataList.size()>0){
			for (TbccBaseHisRef tbccBaseHisRef : refDataList) {
				String [] data =
					calc.calcRef(aiList.get(0).getRefid(), tbccBaseHisRef, tportList, hportList);  //数据列
				aa.add(data);
			}
		}
	
		result.add(aiList);			//保存所有的探头
		result.add(aa);				//保存所有结果集字符串
		result.add(calc.calcHisRefData(aa, aiList.size()));		//计算某列的最大值、最小值、平均值，并保存		
		return result;
	
	}
	
	

	
	
	
	
	
	
	//***********************仓库兼容模块历史数据查询********************************
	
	
	private List<TbccBaseHisRef_Ex> getHisRefData_ex(Long id, String startTime,
			String endTime, String type, String value) {
		
		//验证参数
		if(id==null || id.equals("") || startTime==null || startTime.equals("")|| endTime==null || endTime.equals("")||
				type==null || type.equals("")|| value==null || value.equals("")){
			return null ;
		}
		
		//获取冷库对象
		TbccRefInfo refInfo = this.refinfoBiz.getById(id);
		
		//获取兼容模块的表名
		String tableName = BuildTable.toHisRef_ExTable(refInfo.getProjectId(), refInfo.getNetid());
		
		//获取间隔的总秒数
		
		Integer total = Integer.parseInt(value);
		
		//获取总时间秒数
		if(type.equals("1")){			//hour
			total = 3600 * total ;
			
		}else if(type.equals("2"))		//minutes
			total = 60 * total ;
		
		
		return this.hisRefDao.getExHisDataByTime(tableName, startTime, endTime, total);
	}

	
	/**
	 * 获取扩展设备的仓库的历史数据
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	private List<Object> getHisRefData_ex_result(Long id,String startTime,String endTime,String type,String value) {
		
		//验证参数
		if(id==null ||id.equals("")|| startTime==null || startTime.equals("") || endTime==null || endTime.equals("") ||
				type==null || type.equals("") || value==null || value.equals("")){
			return null ;
		}
		
		
		//获取探头和实时数据
		
		List<TbccAiInfo> aiList = this.aiinfoBiz.getListByRid(id);
		List<TbccBaseHisRef_Ex> refExDataList = this.getHisRefData_ex(id, startTime, endTime, type, value);
	
		
		if(aiList==null || aiList.size()==0){
			return null ;
		}
		
		//定义变量保存温度、湿度探头对应的实际端口号、以及返回的结果
		 List<Integer> tportList = new ArrayList<Integer>() ;
		 List<Integer> rhportList = new ArrayList<Integer>() ;
		 
		 List<Object> resultList = new ArrayList<Object>(); 
		 
		 List<String[]> aa = new ArrayList<String[]>();
		 
		 //分类获取温湿度探头
		 for (TbccAiInfo tbccAiInfo : aiList) {
			if(tbccAiInfo.getDataType().equals(AiInfoBiz.T)){
				tportList.add(tbccAiInfo.getPortNo());
			}else if(tbccAiInfo.getDataType().equals(AiInfoBiz.RH)){
				rhportList.add(tbccAiInfo.getPortNo());
			}
		}
		 
		 //处理时间、温湿度、平均、最大、最小显示值
		 if(refExDataList!=null && refExDataList.size()!=0){
			 for (TbccBaseHisRef_Ex tbccBaseHisRef_Ex : refExDataList) {
				String [] data = calc.calcExRefDataInfo(aiList.get(0).getRefid(), tbccBaseHisRef_Ex, tportList, rhportList);
				aa.add(data) ;
			}
		 }
		 
		
		 
		 
		 resultList.add(aiList) ;
		 resultList.add(aa);
		 resultList.add(calc.calcHisRefData(aa, aiList.size())) ;
		 	
		return resultList;
	}

	
	
	
	public List<Object> getRefHisData(Long id, String startTime,
			String endTime, String type, String value) {
		
		//验证参数
		if(id==null || id.equals("") || startTime==null || startTime.equals("") || endTime==null || endTime.equals("")||
				type==null || type.equals("") || value==null || value.equals("")){
			return null ;
		}
		
		//根据冷库信息获取对应的设备类型
		TbccRefInfo refInfo = this.refinfoBiz.getById(id);
		if(refInfo==null)
			return null ;
		
		TbccDevType devType = this.devTypeBiz.getById(refInfo.getDevId());
		
		//如果为扩展设备，则调用扩展  getHisRefData_ex_result
		if(devType.getDevType().equals(DevTypeBiz.EXAPP)){
		
			return this.getHisRefData_ex_result(id, startTime, endTime, type, value);
		//如果为之前的老版本，则调用之前的方法 	getHisInfo
		}else{
			return this.getHisInfo(id, startTime, endTime, type, value);
		}
	}
	
}
