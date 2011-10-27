package org.tbcc.util;

/**
 * �������Ҫ������������Ŀ�����ɱ���
 * @author Administrator
 *
 */
public class BuildTable {
	
	/**
	 * ���쳵����ʷ���ݱ�
	 * @param proId			���̱��
	 * @return
	 */
	public static String tohisCarTable(String proId)
	{
		return "TbccHistData_Car_"+proId+"_1";	
	}
	
	/**
	 * ����С������ʷ���ݱ�
	 * @param proId			���̱��
	 * @return
	 */
	public static String toHisBoxTable(String proId)
	{
		return "TbccHistData_Box_"+proId+"_1" ;
	}
	
	/**
	 * ���������ʷ���ݱ�
	 * @param proId			���̱��
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
	 * ��ȡ������ʷ��ͣ��¼��
	 * @param proId			���̱��
	 * @return
	 */
	public static String toHisStartUpTable(String proId){
		return "TbccHistStartUp_"+proId+"_1" ;
	}
	
	

}
