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
 * �����Ϊ��DWR ʹ�� JavaScript ���ʵ�java�࣬
 * ��Щ������Ϊ����ʾ��⡢���ء�С����ʵʱ���������õġ�
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
	 * ��ȡʵʱС��������
	 * @param branchId	�û������ķ�֧Id
	 * @return		С����ʵʱ���ݼ���
	 */
	public  List<TbccBaseRealBox> getRealBox(Long branchId){
		return this.realboxBiz.getRealBox(branchId);
	}
	
	/**
	 * ��ȡʵʱ�ƶ���������
	 * @param branchId	�û������ĳ���Id
	 * @return	����ʵʱ���ݼ���
	 */
	public  List<TbccBaseRealCar> getRealCar(Long branchId){
		List<TbccBaseRealCar> list = this.realcarBiz.getRealData(branchId);
		return list ;
	}
	
	/**
	 * ��ȡ¥���ʵʱ����
	 * @param imageId		¥���Ӧ��ͼ����
	 * @param projectId		����Ӧ�Ĺ��̱��
	 * @return				��Ҫ���������̽ͷʵʱ���ݼ����ַ���
	 */
	public String getRealFloor(Long imageId,String projectId){
		return this.imageBiz.getEachControlAllDates(imageId,projectId);
	}
	
	
	public List<TbccProjectImages> getProjectImages(String pid){
		return this.imageBiz.getImagesByPid(pid);
	}
	
	
	
	/**
	 * ���ݳ��ع��̱�ţ���ȡ���ص�ʵʱ���ݡ����ڵ�ͼ��ʾ
	 * @param projectId		���ع��̱��
	 * @return				��������ʵ��
	 */
	public TbccBaseRealCar	getCarToMap(String projectId) {
		return this.realcarBiz.getDataToMap(projectId) ;
	}
	
	/**
	 * ���ݳ��ر�š��Լ���ͣ��¼�ı�ţ���ȡ������ʷ����
	 * @param projectId	���ع��̱��
	 * @param parentId	��ͣ��¼���
	 * @return			������ʷ���ݼ���
	 */
	public List<TbccBaseHisCar> getHisCarData(String projectId ,Long parentId) {
		return this.hiscarBiz.getByParentId(projectId, parentId) ; 
	}
	
	/**
	 * ������⹤�̱�ʶId����ȡ�ù����µ����������Ϣ
	 * @param projectId	��⹤�̱�ʶId
	 * @return
	 */
	public List<TbccRefInfo> getRefListById(String projectId){
		return this.refinfoBiz.getRefListByPrj(projectId);
	}
	
	/**
	 * ͨ���ֿ⹤�̱�ʾ����ȡ�ֿ��ϵͳʵʱ����
	 * @param projectId		�ֿ⹤�̱�ʾid
	 * @return				����״̬��ϵͳ�ϵ硢���ⱨ��
	 */
	public String[] getRealRefSys(String projectId){
		return this.realRefBiz.getRealRefSystemData(projectId) ;
	}
	
}
