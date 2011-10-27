package org.tbcc.util;

/**
 * 这个类主要是用来根据项目，生成表名
 * @author Administrator
 *
 */
public class BuildTable {
	
	/**
	 * 构造车载历史数据表
	 * @param proId			工程编号
	 * @return
	 */
	public static String tohisCarTable(String proId)
	{
		return "TbccHistData_Car_"+proId+"_1";	
	}
	
	/**
	 * 构造小批零历史数据表
	 * @param proId			工程编号
	 * @return
	 */
	public static String toHisBoxTable(String proId)
	{
		return "TbccHistData_Box_"+proId+"_1" ;
	}
	
	/**
	 * 构造冷库历史数据表
	 * @param proId			工程编号
	 * @param netId
	 * @return
	 */
	public static String toHisRefTable(String proId,String netId){
		return "TbccHistData_Ref_"+proId+"_"+netId;
	}
	
	public static String toHisRef_ExTable(String proId,Integer netId){
		return "TbccHistData_Ref_Ex_"+proId+"_"+netId ;
	}
	
	/**
	 * 获取车载历史起停记录表
	 * @param proId			工程编号
	 * @return
	 */
	public static String toHisStartUpTable(String proId){
		return "TbccHistStartUp_"+proId+"_1" ;
	}
	
	

}
