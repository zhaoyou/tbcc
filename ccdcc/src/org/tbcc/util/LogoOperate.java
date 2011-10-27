package org.tbcc.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import org.apache.log4j.Logger;

/**
 * ���ǲ����ܲ�����֧����Logo����
 * @author zhaoyou
 *
 */
public class LogoOperate {
	
	private static  Logger logger = Logger.getLogger(LogoOperate.class);
	
	private static String prefixPath = "" ;
	
	public static void setPrefixPath(String contextPath){
		prefixPath = contextPath + "/img/logo/" ;
	}
	/**
	 * ����һ��������Logo
	 * @param img			ͼƬ�Ķ�����������
	 * @param fileName		����ͼƬ������ 
	 * @return ���ز�����״̬�� 	1 ����ɹ�  0 ����ʧ��
	 */
	public static Integer buildImage(Blob img,String fileName){
		try {
			
			if(img==null || fileName==null || fileName=="")
				return 0 ;
			
			FileOutputStream output = new FileOutputStream(prefixPath + fileName);
			InputStream input = img.getBinaryStream() ;
			byte context[]  = new byte[1024];
			int len = -1 ;
			while((len = input.read(context))!=-1){
				output.write(context, 0, len);
			}
			output.close() ;
			input.close() ;
		} catch (Exception e) {
			logger.warn("��ȡ��˾Logo "+fileName+" ��������: "+e.getMessage());
			return 0 ;
		}
		return 1 ;
	}
}
