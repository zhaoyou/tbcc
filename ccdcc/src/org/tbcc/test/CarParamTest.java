package org.tbcc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.biz.ParamActionBiz;
import org.tbcc.biz.ParamCarVehicleBiz;
import org.tbcc.entity.config.TbccParamAction;

public class CarParamTest {
private static String a[]	 = { "applicationContext-dao.xml", "applicationContext-biz.xml", "applicationContext-action.xml" } ;
	
	private static  ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private static ParamActionBiz paramBiz = (ParamActionBiz)context.getBean("paramActionBiz") ;
	
	private static ParamCarVehicleBiz carVehicleBiz = (ParamCarVehicleBiz)context.getBean("paramCarVehicleBiz");
	
	
	public static void main(String[] args) {
		//testAddOperate() ;
		
		//testUpdateOperate() ;
			
		//testQueryOperate() ;	
		
		//testQueryParam() ;
		
		
		testUpdateParam() ;
	}
	
	/**
	 * 测试增加参数
	 */
	public static void testAddOperate(){
		String projectId = "2008" ;
		Byte cmdType = 2;
		Byte funcType =  2 ;
		Byte optStatus = 1 ;
		
		Long id = paramBiz.addOperate(projectId, funcType, cmdType, optStatus);
		System.out.println("id: "+id);
	
	}
	
	/**
	 * 测试更新参数
	 */
	public static void testUpdateOperate(){
		Long id = new Long(0) ;
		Byte optStatus = null ;
		System.out.println(paramBiz.updateOperateStatus(id, optStatus)) ;
	}
	
	/**
	 * 测试查询参数 
	 */
	public static void testQueryOperate(){
		Long id = new Long(12) ;
		Byte optStatus = paramBiz.getOperateStatus(id) ;
		System.out.println(optStatus);
	}
	
	/**
	 * 测试获取车载参数的
	 */
	
	public static void testQueryParam(){
		Long id = new Long(17) ;
		System.out.println(carVehicleBiz.getCarVehicleByParentId(id));
	}
	
	
	public static void testUpdateParam(){
		
		Long id = new Long(17) ;
		
		String source = "1,N/A,N/A;2,N/A,N/A;3,N/A,N/A;4,N/A,N/A;5,1,100.0;6,1,60;7,1,20.0;8,1,60;" +
				"9,N/A,N/A;10,N/A,N/A;11,N/A,N/A;12,N/A,N/A;13,1,120.0;14,1,60;15,1,10.0;16,1,60" ;
		
		String source2 = "1,1,20.0;2,1,60;3,N/A,N/A;4,N/A,N/A;5,1,100.0;6,1,60;7,N/A,N/A;8,N/A,N/A;9,1,30.0;10,1,60;" +
				"11,N/A,N/A;12,N/A,N/A;13,1,120.0;14,1,60;15,N/A,N/A;16,N/A,N/A";
		
		String projectId = "8888" ;
		
		System.out.println(carVehicleBiz.updateCarVehicle(projectId, source2,null));
	}
	
}
