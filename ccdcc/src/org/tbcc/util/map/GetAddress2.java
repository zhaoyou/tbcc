package org.tbcc.util.map;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * ����һ��ͨ�õ��࣬�������ݾ�γ������Ϣ����ȡʵ�ʵĵ���λ��
 * @author Administrator
 *
 */
public class GetAddress2 {
	
	
	private static  String KEY = "30d03c0ba5c60850be655229ede0f001c17751ecce42fb81df6077eaa1031fb01a4a18c4a07047f0" ;
	
	private static final String X = "121.596923" ;   //��˾�ɼ����ľ���(ddd.mmmmmm) 121.358252    �����վ�ɼ���(ddd.dddd) 12160.177      ��ʵ��121.596923
	
	private static final String Y = "31.255061" ;	  //��˾�ɼ�����γ��(ddd.mmmmmm) 31.153066    �����վ�ɼ���(dd.dddd)3125.2817		��ʵ�� 31.255061
	
	private static final Integer INDEX = 3 ;			//index = 21(�ȵ��ַ) or index = 3(�ֵ���ַ) 
	
	private static final String NODE = "Road" ;			//node = poi(�ȵ��ַ)  or node = Road(�ֵ���ַ)
	
	private static Logger logger = Logger.getLogger(GetAddress2.class);
	
	static {
		try {
			Properties pro = new Properties();
			pro.load(GetAddress2.class.getResourceAsStream("mapKey.properties")) ;
			KEY = pro.getProperty("mapkey") ;
		} catch (Exception e) {
			logger.warn("��ȡMap��Ӧ��key��������..."+e.getMessage());
		}
		
	}
	
	private GetAddress2(){};
	
	
	public static String getAddress(String X,String Y) {
		 try{
			 
		
			//URL url = new URL("http://search1.mapabc.com/sisserver?");	
			URL url = new URL("http://search1.mapabc.com/sisserver?");
			URLConnection urlc = url.openConnection();
	        HttpURLConnection httpConnection = (HttpURLConnection) urlc;
	        httpConnection.setRequestMethod("POST");
	        httpConnection.setRequestProperty("Proxy-Connection", "Keep-Alive");
	        httpConnection.setDoOutput(true);
	        httpConnection.setDoInput(true);
	        httpConnection.connect();
	        

	 

	        String spatialXml = "<?xml version=\"1.0\""
	        	+" encoding=\"gb2312\"?><spatial_request method=\"searchPoint\"><x>"+X+"</x>" 
	        	+ "<y>"+Y+"</y><poiNumber>5</poiNumber><range>500</range><pattern>0</pattern><roadLevel>0</roadLevel>"
	        	+ "</spatial_request>";
	        spatialXml = URLEncoder.encode(spatialXml, "gb2312");
	        String param = "&config=SPAS&gps=1&enc=gb2312&spatialXml=" + spatialXml
	        +"&a_k="+KEY; 
	        OutputStreamWriter wr = new OutputStreamWriter(new
	        BufferedOutputStream(httpConnection.getOutputStream()));
	        wr.write(param);
	        wr.flush();
	        wr.close();
	        
	         InputStream is = null;
	       
	         is = httpConnection.getInputStream();
	         
	         String n = getAddressBy4j(is);
	            
	         httpConnection.disconnect();
	         return n ;
	         
//	        InputStream is = null;
//	         BufferedReader in = null;
//		     InputStreamReader isr = null;  
//		     StringBuffer s = new StringBuffer("");	
//	         is = httpConnection.getInputStream();
//	        
//	        
////       isr = new InputStreamReader(is,"utf-8");
//	        
//	        isr = new InputStreamReader(is,"gb2312");// �ĳ� gb2312
//	        in = new BufferedReader(isr);
//	        String line = null;
//	        line = in.readLine();
//	        System.out.println(line);
//	        while((line = in.readLine()) != null) 
//	           // s.append(line);
//	        System.out.println(line.toString());
//	       //  String r  = parseXML(is);
//	          //  String n = getAddressBy4j(is);
//	            
//	         httpConnection.disconnect();
//	       //  return n ;
////	        System.out.println("x: "+X+" Y:"+Y+"r: "+n);
////	         if(n==null || n.equals(""))
////	        	return "----" ;
////	         return n ;
//	         return s.toString() ;
		 }catch(Exception e){
			 logger.warn("��ȡ��ַʧ��.."+e.getMessage());
			 return "----" ;
		 }
         
	}
	
	/**
	 * ���ݷ��ص�xml�ַ���������dom���������ص�һ�����������ĵ�ַ
	 * @param xml
	
	private static String  parseXML(InputStream is) throws Exception{
		String result = "" ;
		DocumentBuilderFactory factory  = null ;
		DocumentBuilder	 builder  = null ;
		Document doc  = null ;
		
		try {
				 factory= DocumentBuilderFactory.newInstance() ;
				 builder = factory.newDocumentBuilder() ;
				 doc =builder.parse(is); 
				NodeList list = doc.getElementsByTagName(NODE) ;
			if(list==null || list.getLength()==0){
				result = "" ;
			}else{
					result = list.item(0).getChildNodes().item(INDEX).getTextContent();
					return result ;	
			}
			return result ;
	
		} catch (Exception e) {
			System.out.println("�������ص�xml�ļ�����"+e.getMessage());
			return result  ;
		}finally{
			factory = null  ;
			builder = null ;
			doc = null ;
		}
	
	}
	 */
	
	/**
	 * ���ݷ��ص��ַ���xml������dom4j���������ص�һ�����������ĵ�ַ
	 * @param args
	 */
	
	public static String getAddressBy4j(InputStream in)throws Exception{
		
		String result = "" ;
		Element root = null ;
		Element n =null;
		
		
		try {
			root = new SAXReader().read(in).getRootElement() ;		
			Element f = root.element("SpatialBean") ;
			Element s = f.element("roadList") ;
			List list = s.elements() ;
			Element r = (Element)list.get(0) ;
			n= r.element("name") ;
			
			
		} catch (Exception e) {
			throw e ;
		}
		return n.getText() ;
		
	}
	
//	public static void main(String[] args) {
//			
//		for(int i=0;i<1;i++){	 
//			   try{
//				System.out.println(getAddress(X, Y) ); 
//			   }catch (Exception e) {
//				System.out.println("http����δ�ɹ���");			 
//			}
//	    }
//    }
}
