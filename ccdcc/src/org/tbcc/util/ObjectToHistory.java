package org.tbcc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisStartUp;

import sun.nio.cs.HistoricallyNamedCharset;

/**
 * 这个类主要是用来把历史数据转化为对象集合
 * @author Administrator
 *
 */
public class ObjectToHistory {
	
	public ObjectToHistory(){
	}
	
	/**
	 * 把所有的数据转换成移动车载历史数据
	 * @param list
	 * @return
	 */
	public List<TbccBaseHisCar> objectToCar(List list){
		if(list!=null && list.size()>0){
			List<TbccBaseHisCar> carList = new ArrayList<TbccBaseHisCar>();
			TbccBaseHisCar objCar = null ;
			for(int i=0;i<list.size();i++){
				objCar = new TbccBaseHisCar();
				Object[] car = (Object[])list.get(i);
				
				objCar.setId((Long)car[0]);
				objCar.setParentId((Long)car[1]);
				objCar.setUpdateTime((Date)car[2]) ;
				objCar.setAi1((Double)car[3]);
				objCar.setAi2((Double)car[4]);
				objCar.setAi3((Double)car[5]);
				objCar.setAi4((Double)car[6]);
				objCar.setLatitude_dir((Integer)car[7]);
				objCar.setLatitude((Double)car[8]);
				objCar.setLongitude_dir((Integer)car[9]);
				objCar.setLongitude((Double)car[10]);
				objCar.setAlarmStatus((Integer)car[11]);	
				
				carList.add(objCar);
				
			}
			return carList ;
		}
		return null ;
	}
	
	
	/**
	 * 把所有的数据转换成小批零的历史数据
	 * @param list
	 * @return
	 */
	public List<TbccBaseHisBox> objectToBox(List list){
		if(list!=null && list.size()>0){
			List<TbccBaseHisBox> boxList = new ArrayList<TbccBaseHisBox>();
			TbccBaseHisBox objBox = null ;
			for (int i = 0; i < list.size(); i++) {
				objBox = new TbccBaseHisBox();
				Object[] box = (Object[])list.get(i);
				
				objBox.setId((Long)box[0]);
				objBox.setParentId((Long)box[1]);
				objBox.setUpdateTime((Date)box[2]);
				objBox.setAi1((Double)box[3]);
				objBox.setLatitude_dir((Integer)box[4]);
				objBox.setLatitude((Double)box[5]);
				objBox.setLongitude_dir((Integer)box[6]);
				objBox.setLongitude((Double)box[7]);
				objBox.setAlarmStatus((Integer)box[8]);
				
				
				boxList.add(objBox);
			}
			return boxList ;
		}
		return null ;
	}
	
	
	
	/**
	 * 把所哟的数据转换成冷库的历史数据
	 * @param list
	 * @return
	 */
	public List<TbccBaseHisRef> objectToRef(List list){
		
		if(list!=null && list.size()>0){
			List<TbccBaseHisRef> refList = new ArrayList<TbccBaseHisRef>();
			TbccBaseHisRef refobj = null ;
			for (int i = 0; i < list.size(); i++) {
				refobj = new TbccBaseHisRef();
				Object[] ref = (Object[])list.get(i);
				
				//这里原来是用的是updateTime,现在已经改成了用hDate字段了
				//refobj.setUpdateTime((Date)ref[0]);
				refobj.setHdate((Date)ref[0]) ;
				refobj.setAi1((Double)ref[1]);
				refobj.setAi2((Double)ref[2]);
				refobj.setAi3((Double)ref[3]);
				refobj.setAi4((Double)ref[4]);
				refobj.setAi5((Double)ref[5]);
				refobj.setAi6((Double)ref[6]);
				refobj.setAi7((Double)ref[7]);
				refobj.setAi8((Double)ref[8]);
				refobj.setAi9((Double)ref[9]);
				refobj.setAi10((Double)ref[10]);
				refobj.setAi11((Double)ref[11]);
				refobj.setAi12((Double)ref[12]);
				refobj.setAlarmStatus_ref1((Integer)ref[13]);
				refobj.setAlarmStatus_ref2((Integer)ref[14]);
				refobj.setAlarmStatus_ref3((Integer)ref[15]);
				refobj.setAlarmStatus_ref4((Integer)ref[16]);
					
				refList.add(refobj);			
			}
			return refList ;
		}
		return null ;
	}
	
	
	public List<Map> objectToRefCurve(List list){
		if(list!=null && list.size()>0){
			List<Map> refList = new ArrayList<Map>();
			Map<String,Object> refobj = null ;
			for (int i = 0; i < list.size(); i++) {
				refobj = new HashMap<String, Object>();
				Object[] ref = (Object[])list.get(i);
				
				//实际上，此时的ref[0],查询出来已经不是updateTime了，已经是hDate了，为了兼容曲线绘图，所有map的key还用的名字还是
				//updateTime,对应的值已经是hDate了，以后扩展功能需要注意。
				refobj.put("updateTime", ref[0]);
				refobj.put("1", ref[1]);
				refobj.put("2", ref[2]);
				refobj.put("3", ref[3]);
				refobj.put("4", ref[4]);
				refobj.put("5", ref[5]);
				refobj.put("6", ref[6]);
				refobj.put("7", ref[7]);
				refobj.put("8", ref[8]);
				refobj.put("9", ref[9]);
				refobj.put("10", ref[10]);
				refobj.put("11", ref[11]);
				refobj.put("12", ref[12]);
				
				
				refList.add(refobj);			
			}
			return refList ;
		}
		return null ;
	}
	
	
	/**
	 * 把所有的数据转换成启停记录
	 * @param list
	 * @return
	 */
	public List<TbccBaseHisStartUp> objectToStartUp(List list){
		if(list!=null && list.size()>0){
			List<TbccBaseHisStartUp> startUpList = new ArrayList<TbccBaseHisStartUp>();
			TbccBaseHisStartUp hisStart = null ;
			for (int i = 0; i < list.size(); i++) {
				hisStart = new TbccBaseHisStartUp();
				Object[] obj = (Object[])list.get(i);
				
				hisStart.setId((Long)obj[0]);
				hisStart.setBeginTime((Date)obj[1]);
				hisStart.setEndTime((Date)obj[2]);
				hisStart.setPageIndex((Integer)obj[3]);
				hisStart.setPageCount((Integer)obj[4]);
				hisStart.setRecordInterval((Integer)obj[5]);
				hisStart.setLastPageIndex((Integer)obj[6]);
				hisStart.setLastPageRecNum(((Integer)obj[7]));
				hisStart.setBeginAddress((String)obj[8]);
				hisStart.setEndAddress((String)obj[9]);
				hisStart.setShipper((String)obj[10]);
				hisStart.setCarrier((String)obj[11]);
				hisStart.setReceiver((String)obj[12]);
				hisStart.setLastRecordTime((Date)obj[13]);
				hisStart.setUpdateStatus((Integer)obj[14]);
				//hisStart.setTlimitType((Integer)obj[15]) ;
				hisStart.setTuplimit((Integer)obj[15]) ;
				hisStart.setTdwlimit((Integer)obj[16]);
				
				
				startUpList.add(hisStart);
			}
			return startUpList ;
		}
		return null ;
	}
	
}
