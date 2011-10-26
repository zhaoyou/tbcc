package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.StoreConfigBiz;
import org.fdap.entity.Fdapstoretype;
import org.fdap.util.BaseAction;

/**
 * 存储类型配置的action 
 * @author zhaoyou
 *
 */
public class StoreConfigAction extends BaseAction {

	   private Logger logger = Logger.getLogger(StoreConfigAction.class);
	
		private StoreConfigBiz storeConfigBiz = null ;

		public StoreConfigBiz getStoreConfigBiz() {
			return storeConfigBiz;
		}

		public void setStoreConfigBiz(StoreConfigBiz storeConfigBiz) {
			this.storeConfigBiz = storeConfigBiz;
		}

		/**
		 * 跳转到冷库参数列表
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward toStoreList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			List<Fdapstoretype> list = this.storeConfigBiz.getAll() ;
			request.setAttribute("storeTypeList", list);
			return mapping.findForward("list") ;
		}
		
		/**
		 * 跳转到添加存储类型页面
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward toAddStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			request.setAttribute("operater", "doAddStore") ;
			return mapping.findForward("update") ;
		}
		
		/**
		 * 保存添加的存储类型
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward doAddStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			String storeTypeName = request.getParameter("storeTypeName") ;
			
			String t_lower = request.getParameter("t_lower") ;
			String t_lower_time = request.getParameter("t_lower_time") ;
//			String t_lower_min = request.getParameter("t_lower_min") ;
//			String t_lower_min_time = request.getParameter("t_lower_min_time") ;
			
			String t_high = request.getParameter("t_high") ;
			String t_high_time = request.getParameter("t_high_time") ;
//			String t_high_max = request.getParameter("t_high_max") ;
//			String t_high_max_time = request.getParameter("t_high_max_time");
			
			String rh_lower = request.getParameter("rh_lower") ;
			String rh_lower_time = request.getParameter("rh_lower_time");
			String rh_high = request.getParameter("rh_high") ;
			String rh_high_time = request.getParameter("rh_high_time") ;
			
			
			if(storeTypeName==null || storeTypeName.equals("") || t_high==null || t_high.equals("") ||
					t_high_time==null || t_high_time.equals("")||t_lower==null || t_lower.equals("")||
					t_lower_time==null || t_lower_time.equals("")||	rh_high==null || rh_high.equals("")|| 
					rh_high_time==null || rh_high_time.equals("")|| rh_lower==null || rh_lower.equals("")||
					rh_lower_time==null || rh_lower_time.equals("")){
					logger.warn("params is null ") ;
					throw new Exception("param is null ");
			}
			/*if(storeTypeName==null || storeTypeName.equals("") || t_high==null || t_high.equals("") || t_high_max==null || t_high_max.equals("")||
					t_high_max_time==null || t_high_max_time.equals("")|| t_high_time==null || t_high_time.equals("")||t_lower==null || t_lower.equals("")||
					t_lower_time==null || t_lower_time.equals("")|| t_lower_min==null || t_lower_min.equals("")|| t_lower_min_time==null || t_lower_min_time.equals("")||
					rh_high==null || rh_high.equals("")|| rh_high_time==null || rh_high_time.equals("")|| rh_lower==null || rh_lower.equals("")||
					rh_lower_time==null || rh_lower_time.equals("")){
					logger.warn("params is null ") ;
					throw new Exception("param is null ");
			}*/
			
			Fdapstoretype storeType = new Fdapstoretype();
			storeType.setName(storeTypeName) ;
			storeType.setTemphighdelay(new Integer (t_high_time));
			storeType.setTemphighlimit(new Double(t_high));
			storeType.setTemplowerdelay(new Integer(t_lower_time));
			storeType.setTemplowerlimit(new Double(t_lower));
			
//			storeType.setTempmaxhighdelay(new Integer(t_high_max_time));
//			storeType.setTempmaxhighlimit(new Double(t_high_max));
//			storeType.setTempminlowerdelay(new Integer(t_lower_min_time));
//			storeType.setTempminlowerlimit(new Double(t_lower_min));
			
			storeType.setHumhighdelay(new Integer (rh_high_time));
			storeType.setHumhighlimit(new Double(rh_high));
			storeType.setHumlowerdelay(new Integer(rh_lower_time));
			storeType.setHumlowerlimit(new Double(rh_lower)); 
			
			this.getStoreConfigBiz().addStoreType(storeType);
			
			return new ActionForward("/storeconfig.do?ope=toStoreList");
		}
		
		/**
		 * 跳转到存储类型编辑
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward toEditStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String[]   chkStoreType = request.getParameterValues("chkStoreType") ;
			if(chkStoreType==null || chkStoreType.length==0){
				logger.warn("param is null") ;
				throw new Exception("param is null");
			}		
			request.setAttribute("storetype", this.getStoreConfigBiz().getByStoreId(new Integer(chkStoreType[0])));
			request.setAttribute("operater", "doEditStore") ;
			return mapping.findForward("update") ;
		}
		
		/**
		 *  保存编辑的存储类型
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward doEditStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String storetypeId = request.getParameter("storetypeid") ;
			String storeTypeName = request.getParameter("storeTypeName") ;
			
			String t_lower = request.getParameter("t_lower") ;
			String t_lower_time = request.getParameter("t_lower_time") ;
//			String t_lower_min = request.getParameter("t_lower_min") ;
//			String t_lower_min_time = request.getParameter("t_lower_min_time") ;
			
			String t_high = request.getParameter("t_high") ;
			String t_high_time = request.getParameter("t_high_time") ;
//			String t_high_max = request.getParameter("t_high_max") ;
//			String t_high_max_time = request.getParameter("t_high_max_time");
			
			String rh_lower = request.getParameter("rh_lower") ;
			String rh_lower_time = request.getParameter("rh_lower_time");
			String rh_high = request.getParameter("rh_high") ;
			String rh_high_time = request.getParameter("rh_high_time") ;
			
			if(storeTypeName==null || storeTypeName.equals("") || t_high==null || t_high.equals("") ||
					t_high_time==null || t_high_time.equals("")||t_lower==null || t_lower.equals("")||
					t_lower_time==null || t_lower_time.equals("")||	rh_high==null || rh_high.equals("")|| 
					rh_high_time==null || rh_high_time.equals("")|| rh_lower==null || rh_lower.equals("")||
					rh_lower_time==null || rh_lower_time.equals("")){
					logger.warn("params is null ") ;
					throw new Exception("param is null ");
			}
			
			/*if(storeTypeName==null || storeTypeName.equals("") || t_high==null || t_high.equals("") || t_high_max==null || t_high_max.equals("")||
					t_high_max_time==null || t_high_max_time.equals("")|| t_high_time==null || t_high_time.equals("")||t_lower==null || t_lower.equals("")||
					t_lower_time==null || t_lower_time.equals("")|| t_lower_min==null || t_lower_min.equals("")|| t_lower_min_time==null || t_lower_min_time.equals("")||
					rh_high==null || rh_high.equals("")|| rh_high_time==null || rh_high_time.equals("")|| rh_lower==null || rh_lower.equals("")||
					rh_lower_time==null || rh_lower_time.equals("")|| storetypeId==null || storetypeId.equals("")){
					logger.warn("params is null ") ;
					throw new Exception("param is null ");
			}*/
			
			Fdapstoretype storeType = new Fdapstoretype();
			
			storeType.setStoreid(new Integer(storetypeId)) ;
			storeType.setName(storeTypeName) ;
			storeType.setTemphighdelay(new Integer (t_high_time));
			storeType.setTemphighlimit(new Double(t_high));
			storeType.setTemplowerdelay(new Integer(t_lower_time));
			storeType.setTemplowerlimit(new Double(t_lower));
			
//			storeType.setTempmaxhighdelay(new Integer(t_high_max_time));
//			storeType.setTempmaxhighlimit(new Double(t_high_max));
//			storeType.setTempminlowerdelay(new Integer(t_lower_min_time));
//			storeType.setTempminlowerlimit(new Double(t_lower_min));
			
			storeType.setHumhighdelay(new Integer (rh_high_time));
			storeType.setHumhighlimit(new Double(rh_high));
			storeType.setHumlowerdelay(new Integer(rh_lower_time));
			storeType.setHumlowerlimit(new Double(rh_lower));
			
			this.getStoreConfigBiz().updateStoreType(storeType);
			return new ActionForward("/storeconfig.do?ope=toStoreList");
		}
		
		/**
		 * 批量删除存储类型
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward toDelStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String [] chkStoreType = request.getParameterValues("chkStoreType") ;
			if(chkStoreType==null || chkStoreType.length==0){
				logger.warn("param is null ") ;
				throw new Exception("param is null");
			}
			
			if(!this.getStoreConfigBiz().delStoreTypeByStoreIds(chkStoreType)){
				request.setAttribute("msg", "<font color='red'>失败,请先删除配置了该存储类型的冷库!</font>") ;
			}
			
			return new ActionForward("/storeconfig.do?ope=toStoreList");
		}
		
}
