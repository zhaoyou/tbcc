package org.tbcc.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.tbcc.dao.impl.ImgDaoImpl;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;


public class DaoTest extends  AbstractDependencyInjectionSpringContextTests{
	ImgDaoImpl imgDaoImpl;
	HibernateTemplate hibernateTemplate;
	protected String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext-dao.xml","classpath:applicationContext-biz.xml","classpath:applicationContext-action.xml" };
	}
	/**
	 * 所有正常流程测试
	 *
	 */
	@Test
	public void testGetAllControlsData(){
		Assert.assertNotNull(imgDaoImpl.getImageAllControlRealData(6, "1001"));
		for(int i=0;i<imgDaoImpl.getImageAllControlRealData(6, "1001").size();i++){
			System.out.println(((TbccBaseRealRef)imgDaoImpl.getImageAllControlRealData(6, "1001").get(i)).getNeiId());
		}
	}
	@Test
	public void testFindImageByProjectId() {
		
		Assert.assertNotNull(imgDaoImpl.findImageByProjectId("1000"));
		System.out.println(imgDaoImpl.findImageByProjectId("1000").get(0).getId());		
	}
	
	@Test
	public void testFindImageControlsByImgId(){
//		Assert.assertNotNull(imgDaoImpl.findImageControlsByImgID(1));
//	//	Assert.assertEquals(null, imgDaoImpl.findImageControlsByImgID(9));
//		System.out.println(imgDaoImpl.findImageControlsByImgID(1).get(0).getDataType());
//		List<TbccImageControls> list= imgDaoImpl.findImageControlsByImgID(1);
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).getPortNo());
//		}
	}
	
	@Test
	public void testFindControlNameByImageControl(){
//		Assert.assertNotNull(imgDaoImpl.findControlNameByImageControl(imgDaoImpl.findImageControlsByImgID(1).get(1)));
//		System.out.println(imgDaoImpl.findControlNameByImageControl(imgDaoImpl.findImageControlsByImgID(1).get(5)));
	}
	
	@Test
	public void testFindImagePath() {
		Assert.assertNotNull(imgDaoImpl.getImagePathByImgID(7));
	//	System.out.println(imgDaoImpl.getImagePathByImgID(5));		
	}
	
	/**
	 * 所有异常、错误测试
	 *
	 */
	
	@Test
	public void testNull_FindImagePath() {
		Assert.assertNull(imgDaoImpl.getImagePathByImgID(4));
	//	System.out.println(imgDaoImpl.getImagePathByImgID(5));		
	}
	@Test
	public void testNull_FindImageByProjectId() {
		Assert.assertEquals(0,imgDaoImpl.findImageByProjectId("90").size());
	//	System.out.println(imgDaoImpl.findImageByProjectId("90"));
	}
	
	@Test
	public void testNull_FindImageControlsByImgId() {
//		Assert.assertEquals(0, imgDaoImpl.findImageControlsByImgID(8).size());
	//	System.out.println(imgDaoImpl.findImageControlsByImgID(8).size());
	}
	
	@Test
	public void testNull_FindControlNameByImageControl() {
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("200c");
		control.setNetid(1);
		control.setPortNo(9);
		control.setRefid(1);
//		Assert.assertEquals("T51", imgDaoImpl.findControlNameByImageControl(control));
		//System.out.println(imgDaoImpl.findControlNameByImageControl(control));
	}
	
	
	public void setImgDaoImpl(ImgDaoImpl imgDaoImpl) {
		this.imgDaoImpl = imgDaoImpl;
	}

//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
}
