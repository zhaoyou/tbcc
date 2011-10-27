package org.tbcc.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import org.apache.log4j.Logger;

/**
 * 这是操作总部、分支机构Logo的类
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
	 * 产生一个机构的Logo
	 * @param img			图片的二进制流对象
	 * @param fileName		产生图片的名称 
	 * @return 返回操作的状态码 	1 代表成功  0 代表失败
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
			logger.warn("获取公司Logo "+fileName+" 发生错误: "+e.getMessage());
			return 0 ;
		}
		return 1 ;
	}
}
