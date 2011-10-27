package org.tbcc.util.map;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 这是一个通用的类，用来
 * @author Administrator
 *
 */
public class GetAddress {
	
	
	private static final String KEY = "30d03c0ba5c60850be655229ede0f001c17751ecce42fb81df6077eaa1031fb01a4a18c4a07047f0" ;
	
	private static final String X = "121.59822463989258" ;
	
	private static final String Y = "31.250708347490906" ;
	
	private static final Integer INDEX = 3 ;			//index = 21(热点地址) or index = 3(街道地址) 
	
	private static final String NODE = "Road" ;			//node = poi(热点地址)  or node = Road(街道地址)
	
	
	private GetAddress(){};
	
	
	public static String getAddress(String X,String Y){
		try{
			//URL url = new URL("http://search1.mapabc.com/sisserver?");	
			URL url = new URL("http://search.mapabc.com/sisserver?");
			 URLConnection urlc = url.openConnection();
	        HttpURLConnection httpConnection = (HttpURLConnection) urlc;
	        httpConnection.setRequestMethod("POST");
	        httpConnection.setRequestProperty("Proxy-Connection", "Keep-Alive");
	        httpConnection.setDoOutput(true);
	        httpConnection.setDoInput(true);
	        httpConnection.connect();
	//        String spatialXml = "<?xml version=\"1.0\""
	//        	+" encoding=\"GB2312\"?><spatial_request method=\"searchPoint\"><x>"+X+"</x>" 
	//        	+ "<y>"+Y+"</y><poiNumber>10</poiNumber><range>300</range><pattern>0</pattern><level>1</level>"
	//        	+ "</spatial_request>";
	        
	        String spatialXml = "<?xml version=\"1.0\""
	        	+" encoding=\"utf-8\"?><spatial_request method=\"searchPoint\"><x>"+X+"</x>" 
	        	+ "<y>"+Y+"</y><poiNumber>1</poiNumber><range>500</range><pattern>0</pattern><level>0</level>"
	        	+ "</spatial_request>";
	
	        
	        
	        spatialXml = URLEncoder.encode(spatialXml, "utf-8");
	        String param = "&config=SPAS&enc=utf8&spatialXml=" + spatialXml
	        +"&a_k="+KEY;
	       // System.out.println(param);
	        OutputStreamWriter wr = new OutputStreamWriter(new
	        BufferedOutputStream(httpConnection.getOutputStream()));
	        wr.write(param);
	        wr.flush();
	        wr.close();
	        
	         InputStream is = null;
	         BufferedReader in = null;
		     InputStreamReader isr = null;  
		     StringBuffer s = new StringBuffer("");	
	         is = httpConnection.getInputStream();
	        
	        
//        isr = new InputStreamReader(is,"utf-8");
	        
	        isr = new InputStreamReader(is,"gb2312");// 改成 gb2312
	        in = new BufferedReader(isr);
	        String line = null;
	        line = in.readLine();
	        System.out.println(line);
	        while((line = in.readLine()) != null) 
	        	System.out.println(line);
	            s.append(line);
	        System.out.print(s.toString());
	       //  String r  = parseXML(is);
	          //  String n = getAddressBy4j(is);
	            
	         httpConnection.disconnect();
	       //  return n ;
//	        System.out.println("x: "+X+" Y:"+Y+"r: "+n);
//	         if(n==null || n.equals(""))
//	        	return "----" ;
//	         return n ;
	         return s.toString() ;
		}catch(Exception e){
			System.out.println("获取车载实时地址错误."+e.getMessage());
			return "----" ;
		}
         
	}
	
	/**
	 * 根据返回的xml字符串、利用dom解析。返回第一个符合条件的地址
	 * @param xml
	 */
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
			System.out.println("解析返回的xml文件错误"+e.getMessage());
			return result  ;
		}finally{
			factory = null  ;
			builder = null ;
			doc = null ;
		}
	
	}
	
	/**
	 * 根据返回的字符串xml，利用dom4j解析、返回第一个符合条件的地址
	 * @param args
	 */
	
	public static String getAddressBy4j(InputStream in){
		String result = "" ;
		Element root = null ;
		try {
			root = new SAXReader().read(in).getRootElement() ;		
			Element f = root.element("SpatialBean") ;
			Element s = f.element("roadList") ;
			List list = s.elements() ;
			Element r = (Element)list.get(0) ;
			Element n = r.element("name") ;
			return n.getText() ;
			
		} catch (Exception e) {
			System.out.println("dom4j解析错误...."+e.getMessage());
			return "----" ;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(getAddress(X, Y) );
	}
}
