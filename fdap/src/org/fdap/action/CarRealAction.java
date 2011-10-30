package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.CarRealBiz;
import org.fdap.biz.HospitalBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.entity.FdapHospital;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.util.BaseAction;

/**
 * 车载实时数据action
 * @author zhaoyou
 *
 */
public class CarRealAction extends BaseAction {

	private static Logger logger = Logger.getLogger(CarRealAction.class);
	
	private CarRealBiz 	carRealBiz = null ;
	
	
	private OrgBiz orgBiz = null ;
	
	private HospitalBiz hospitalBiz = null;
	
	public void setHospitalBiz(HospitalBiz hospitalBiz) {
		this.hospitalBiz = hospitalBiz;
	}

	public OrgBiz getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	
	
	public CarRealBiz getCarRealBiz() {
		return carRealBiz;
	}

	public void setCarRealBiz(CarRealBiz carRealBiz) {
		this.carRealBiz = carRealBiz;
	}

	/**
	 * 跳转到车载实时数据页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//验证参数
		String oid = request.getParameter("oid");
		

		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的oid参数");
			throw new Exception("传递了非法的参数oid");
		}
		
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		
		//保存所有的车载工程集合
		List<Fdapproject> list = this.carRealBiz.getCarProjectByOid(new Long(oid));
		request.setAttribute("projectIds", buildStr(list));
		
		return mapping.findForward("realdata");
	}
	
	
	
	public ActionForward toRealMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//验证参数
		String oid = request.getParameter("oid");
		
		String pNO = request.getParameter("pNO");
		//String pNO = "3010";

		if(oid==null || oid.equals("")||pNO==null || pNO.equals("")){
			logger.warn("传递了非法参数");
			throw new Exception("传递了非法参数");
		}
		
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		
		request.setAttribute("pNO", pNO);
		
		//get hospital info to carRealMap info .
		
		List<FdapHospital> list = hospitalBiz.getAll();
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i ++) {
				FdapHospital fdapHospital = list.get(i);
				sb.append(fdapHospital.getHospitalName() + ",");
				sb.append(fdapHospital.getLatitude() + ",");
				sb.append(fdapHospital.getLongitude() + ",");
				sb.append(fdapHospital.getProjectId());
				if ( i != list.size()-1) {
					sb.append(";");
				}
			}
			logger.info("hospital infos: " + sb.toString());
			request.setAttribute("hospitals", sb.toString());
		}
		
		return mapping.findForward("realmap");
	}
	
	
	
	
	/**
	 * 封装工程标识Id字符串 1001,1002,1003
	 * @param list
	 * @return
	 */
	private String buildStr(List<Fdapproject> list){
		StringBuffer sb = new StringBuffer();
		
		if(list==null || list.size()==0)
			return sb.toString() ;
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getProjectid());
			if(i!=list.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}
}
