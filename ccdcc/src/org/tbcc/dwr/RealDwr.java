package org.tbcc.dwr;

import java.util.List;

import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.IGetImageEachControlAllDatas;
import org.tbcc.biz.RealBoxBiz;
import org.tbcc.biz.RealCarBiz;
import org.tbcc.biz.RealRefBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseRealBox;
import org.tbcc.entity.TbccBaseRealCar;
import org.tbcc.entity.TbccProjectImages;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.MySpringFactory;

/**
 * 这个是为了DWR 使用 JavaScript 访问的java类，
 * 这些方法是为了显示冷库、车载、小批零实时数据所调用的。
 * @author Administrator
 *
 */
public class RealDwr {
	
	private  RealBoxBiz realboxBiz = null ;
	
	private  RealCarBiz realcarBiz = null ;
	
	private HisCarBiz hiscarBiz = null ;
	
	private IGetImageEachControlAllDatas imageBiz = null ;
	
	private RefInfoBiz refinfoBiz = null ;
	
	private RealRefBiz	realRefBiz = null ;
	
	public RealDwr(){
		realboxBiz = (RealBoxBiz)MySpringFactory.getInstance().getBean("realboxBiz");
		realcarBiz = (RealCarBiz)MySpringFactory.getInstance().getBean("realcarBiz");
		imageBiz   = (IGetImageEachControlAllDatas)MySpringFactory.getInstance().getBean("getImageEachControlAllDatasImpl");
		hiscarBiz  = (HisCarBiz)MySpringFactory.getInstance().getBean("hiscarBiz") ;
		refinfoBiz = (RefInfoBiz)MySpringFactory.getInstance().getBean("refInfoBiz") ;
		realRefBiz = (RealRefBiz)MySpringFactory.getInstance().getBean("realrefBiz");
	}
	
	/**
	 * 获取实时小批零数据
	 * @param branchId	用户所属的分支Id
	 * @return		小批零实时数据集合
	 */
	public  List<TbccBaseRealBox> getRealBox(Long branchId){
		return this.realboxBiz.getRealBox(branchId);
	}
	
	/**
	 * 获取实时移动车载数据
	 * @param branchId	用户所属的车载Id
	 * @return	车载实时数据集合
	 */
	public  List<TbccBaseRealCar> getRealCar(Long branchId){
		List<TbccBaseRealCar> list = this.realcarBiz.getRealData(branchId);
		return list ;
	}
	
	/**
	 * 获取楼层的实时数据
	 * @param imageId		楼层对应的图层编号
	 * @param projectId		冷库对应的工程编号
	 * @return				需要解析的冷库探头实时数据集合字符串
	 */
	public String getRealFloor(Long imageId,String projectId){
		return this.imageBiz.getEachControlAllDates(imageId,projectId);
	}
	
	
	public List<TbccProjectImages> getProjectImages(String pid){
		return this.imageBiz.getImagesByPid(pid);
	}
	
	
	
	/**
	 * 根据车载工程编号，获取车载的实时数据、用于地图显示
	 * @param projectId		车载工程编号
	 * @return				车载数据实体
	 */
	public TbccBaseRealCar	getCarToMap(String projectId) {
		return this.realcarBiz.getDataToMap(projectId) ;
	}
	
	/**
	 * 根据车载编号、以及启停记录的编号，获取车载历史数据
	 * @param projectId	车载工程编号
	 * @param parentId	启停记录编号
	 * @return			车载历史数据集合
	 */
	public List<TbccBaseHisCar> getHisCarData(String projectId ,Long parentId) {
		return this.hiscarBiz.getByParentId(projectId, parentId) ; 
	}
	
	/**
	 * 根据冷库工程标识Id、获取该工程下的所有冷库信息
	 * @param projectId	冷库工程标识Id
	 * @return
	 */
	public List<TbccRefInfo> getRefListById(String projectId){
		return this.refinfoBiz.getRefListByPrj(projectId);
	}
	
	/**
	 * 通过仓库工程标示，获取仓库的系统实时数据
	 * @param projectId		仓库工程标示id
	 * @return				连接状态、系统断电、声光报警
	 */
	public String[] getRealRefSys(String projectId){
		return this.realRefBiz.getRealRefSystemData(projectId) ;
	}
	
}
