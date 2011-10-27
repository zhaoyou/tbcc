package org.tbcc.test;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.cool.TbccAirCoolerRealData;
import org.tbcc.util.HibernateSessionFactory;

/**
 *  ≤‚ ‘ ”Õº”≥…‰πÿœµ
 * @author Administrator
 *
 */
public class ViewTest {
	
	private static String a[]	 = { "applicationContext-dao.xml", "applicationContext-biz.xml", "applicationContext-action.xml" } ;
	
	private static  ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	public static void main(String[] args) {
		
				testHisCarStartUp() ;
	}
	

	public static void testAirCoolerReal(){
		Session session = HibernateSessionFactory.getSession() ;
		String sql = "select c.id as {cool.id},c.name as {cool.name},c.defrostAlarm as {cool.defrostAlarm} " +
				",c.defrostState as {cool.defrostState} from tbccaircoolerview c where c.id >0" ;
		SQLQuery query = session.createSQLQuery(sql) ;
		query.addEntity("cool", TbccAirCoolerRealData.class) ;
		List list = query.list() ;
		System.out.println("ok!"+list.size());
	}
	
	/**
	 * ≤‚ ‘≥µ‘ÿ∆Õ£
	 */
	public static void  testHisCarStartUp(){
		
	
		
		
		List<TbccBaseHisStartUp> list =
			((HisStartUpDao)context.getBean("startUpDao")).getStartUpListByTime("TbccHistStartUp_3000_1", "2010/1/16 15:16:13", "2010/1/21 7:54:12") ;
		for (TbccBaseHisStartUp tbccBaseHisStartUp : list) {
			System.out.println(tbccBaseHisStartUp.getId());
			System.out.println(tbccBaseHisStartUp.getBeginAddress());
			System.out.println(tbccBaseHisStartUp.getBeginTime());
			System.out.println(tbccBaseHisStartUp.getBtimeStr());
			System.out.println(tbccBaseHisStartUp.getCarrier());
			System.out.println(tbccBaseHisStartUp.getEndAddress());
			System.out.println(tbccBaseHisStartUp.getEndTime());
			System.out.println(tbccBaseHisStartUp.getEtimeDisplay());
			System.out.println(tbccBaseHisStartUp.getEtimeStr());
			System.out.println(tbccBaseHisStartUp.getLastPageIndex());
			System.out.println(tbccBaseHisStartUp.getLastPageRecNum());
			System.out.println(tbccBaseHisStartUp.getLastRecordTime());
			System.out.println(tbccBaseHisStartUp.getPageCount());
			System.out.println(tbccBaseHisStartUp.getPageIndex());
			System.out.println(tbccBaseHisStartUp.getReceiver());
			System.out.println(tbccBaseHisStartUp.getRecordInterval());
			System.out.println(tbccBaseHisStartUp.getShipper());
			System.out.println(tbccBaseHisStartUp.getUpdateStatus());
			
		}
	}
}
