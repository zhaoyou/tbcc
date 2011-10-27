package org.tbcc.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.tbcc.biz.IGetImageEachControlAllDatas;
import org.tbcc.dao.IImageControlDao;
import org.tbcc.dao.IgetNetIdRealDataDao;
import org.tbcc.entity.TbccImageControls;

public class TestRealDataDao extends  AbstractDependencyInjectionSpringContextTests{
	IImageControlDao imageControlDaoImpl;
//	HibernateTemplate hibernateTemplate;
	IgetNetIdRealDataDao getNetIdRealDataDaoImpl;
	IGetImageEachControlAllDatas getImageEachControlAllDatasImpl;
	
	
	public IGetImageEachControlAllDatas getGetImageEachControlAllDatasImpl() {
		return getImageEachControlAllDatasImpl;
	}
	public void setGetImageEachControlAllDatasImpl(
			IGetImageEachControlAllDatas getImageEachControlAllDatasImpl) {
		this.getImageEachControlAllDatasImpl = getImageEachControlAllDatasImpl;
	}
	public void setGetNetIdRealDataDaoImpl(
			IgetNetIdRealDataDao getNetIdRealDataDaoImpl) {
		this.getNetIdRealDataDaoImpl = getNetIdRealDataDaoImpl;
	}
	protected String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext-dao.xml","classpath:applicationContext-biz.xml","classpath:applicationContext-action.xml" };
	}
	@Test
	public void testGetRealData(){
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("1000");
		control.setNetid(1);
		control.setPortNo(1);
		
		Assert.assertNotNull(imageControlDaoImpl.findControlRealDataByImageControl(control));
	//	System.out.println(imageControlDaoImpl.findControlRealDataByImageControl(control).concat("   ").concat(String.valueOf(control.getPortNo())));
	}
	
	@Test
	public void testGetEachControlAllDates(){
//		Assert.assertNotNull(getImageEachControlAllDatasImpl.getEachControlAllDates(1));
//		System.out.println(getImageEachControlAllDatasImpl.getEachControlAllDates(1));
	}
	
	@Test
	public void testNull_GetRealData(){
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("1000");
		control.setNetid(1);
		control.setPortNo(1);
		
		Assert.assertNotNull(getNetIdRealDataDaoImpl.getRealData(control));
	//	System.out.println(imageControlDaoImpl.findControlRealDataByImageControl(control).concat("   ").concat(String.valueOf(control.getPortNo())));
	}
	
	@Test
	public void testNull_findControlRealDataByImageControl(){
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("1000");
		control.setNetid(2);
		control.setPortNo(1);
		
		Assert.assertNull(imageControlDaoImpl.findControlRealDataByImageControl(control));
	//	System.out.println(imageControlDaoImpl.findControlRealDataByImageControl(control).concat("   ").concat(String.valueOf(control.getPortNo())));
	}
	@Test
	public void testNull_findControlRealDataByImageControl2(){
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("2001");
		control.setNetid(1);
		control.setPortNo(1);
		
		Assert.assertEquals(null,imageControlDaoImpl.findControlRealDataByImageControl(control));
	//	System.out.println(imageControlDaoImpl.findControlRealDataByImageControl(control).concat("   ").concat(String.valueOf(control.getPortNo())));
	}
	@Test
	public void testNull_findControlRealDataByImageControl3(){
		TbccImageControls control = new TbccImageControls();
		control.setProjectId("1000");
		control.setNetid(1);
		control.setPortNo(3);
		
		Assert.assertEquals("Œ¥∆Ù”√",imageControlDaoImpl.findControlRealDataByImageControl(control));
	//	System.out.println(imageControlDaoImpl.findControlRealDataByImageControl(control).concat("   ").concat(String.valueOf(control.getPortNo())));
	}
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	
	public IImageControlDao getImageControlDaoImpl() {
		return imageControlDaoImpl;
	}
	public void setImageControlDaoImpl(IImageControlDao imageControlDaoImpl) {
		this.imageControlDaoImpl = imageControlDaoImpl;
	}

}
