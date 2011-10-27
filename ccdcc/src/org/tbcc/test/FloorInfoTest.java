package org.tbcc.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.dao.RealRefDao;

public class FloorInfoTest {
	private static String a[]	 = { "applicationContext-dao.xml", "applicationContext-biz.xml", "applicationContext-action.xml" } ;
	
	private static  ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private static RealRefDao realrefDao = (RealRefDao)context.getBean("realrefDao");
	
	public static void main(String[] args) {
		List<String> projects = Arrays.asList("2045","200c");
		List<Map<String,Object>> list = realrefDao.getFloorInfo(projects);
		for (Map<String, Object> map : list) {
			System.out.print("projectId: "+map.get("project"));
			System.out.print("  floorNo: " + map.get("floorNo"));
			System.out.println("  floorType:"+map.get("floorType"));
		}
	}
	
}
