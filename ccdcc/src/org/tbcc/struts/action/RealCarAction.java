package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 *  这是一个action 接受 realcar.do 的请求 extends baseAction
 * @author Administrator
 *
 */
public class RealCarAction extends BaseAction {
	
	private BranchBiz branchbiz = null;
	
	private HisCarBiz hiscarBiz = null ;
	
	private Logger logger = Logger.getLogger(RealCarAction.class);
	
	/**
	 * 由spring容器注入
	 * @param hiscarBiz
	 */
	public void setHiscarBiz(HisCarBiz hiscarBiz) {
		this.hiscarBiz = hiscarBiz;
	}
	
	public BranchBiz getBranchbiz() {
		return branchbiz;
	}

	public void setBranchbiz(BranchBiz branchbiz) {
		this.branchbiz = branchbiz;
	}

	/**
	 * 请求到实时数据的页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(toRealData)传递了一个非法的机构标示!");
			throw new Exception("参数非法!");
		}
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("reallist2");
		}else{
			TbccBranchType branch = branchbiz.getById(Long.valueOf(branchId));
			if(branch==null){
				return mapping.findForward("index");
			}else{
				request.setAttribute("exeget", request.getParameter("exeget"));
				return mapping.findForward("reallist2_exe");
			}
		}
		
	}
	
	/**
	 * 跳转的车载实时数据地图显示页面
	 * @param mapping		包含映射关系的信息
	 * @param form			表单
	 * @param request		请求
	 * @param response		响应
	 * @return				返回到 realMap映射的jsp页面
	 * @throws Exception
	 */
	public ActionForward toRealMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(toRealMap)传递了一个非法的机构标示!");
			throw new Exception("参数非法!");
		}
		
		/**
		 * 标注一下、后来扩展实现实时数据地图显示的时候、也需要获取所有的移动车载工程、所有也是公用一个carprjList
		 * 所有在这里判断一下、session中是否存在了车载集合、如果存在、则不需要查询了
		 * 为了解决浏览器选项卡的session问题，每次进入页面都重新查询
		 */
		
	//	if(request.getSession().getAttribute("carprjList")==null){
			//获取该机构下的所有移动车载项目
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
		
			// 这个地方我把所有的移动项目放入session当中不要每次查询都要去查数据库，以后从性能考虑，要改一下
				if(carlist!=null && carlist.size()>0)
					request.getSession().setAttribute("carprjList", carlist);  	
	//	}
		
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("realmap2");
		}else{
			request.setAttribute("exeget", request.getParameter("exeget"));
			return mapping.findForward("realmap2_exe");
		}
	}
	
	/**
	 * 		刷新实时数据页面的地图显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRealMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		
		
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(doRealMap)传递了一个非法的机构标示!");
			throw new Exception("参数非法!");
		}
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("realmap2");
		}else{
			request.setAttribute("exeget", request.getParameter("exeget"));
			return mapping.findForward("realmap2_exe");
		}
	}
	
}
