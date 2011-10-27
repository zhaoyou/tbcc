package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HisCarAlarmBiz;
import org.tbcc.dao.HisCarAlarmDao;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisCarAlarm;
import org.tbcc.util.BuildTable;
import org.tbcc.util.MyUtil;

public class HisCarAlarmBizImpl implements HisCarAlarmBiz {

	private HisCarAlarmDao hiscaralarmdao;
	public HisCarAlarmDao getHiscaralarmdao() {
		return hiscaralarmdao;
	}
	public void setHiscaralarmdao(HisCarAlarmDao hiscaralarmdao) {
		this.hiscaralarmdao = hiscaralarmdao;
	}
	
	public List<TbccBaseHisCarAlarm> insertAndGetHisCarAlarm(String proId,
			String startTime, String endTime, Long sid) {
		if(proId==null || proId.equals("")|| startTime==null || startTime.equals("")||endTime==null||
				endTime.equals("")){
			return null ;
		}
		
		startTime =MyUtil.getValid(startTime);
		
		List<TbccBaseHisCarAlarm> caralarmList = hiscaralarmdao.getHisAlarm(proId, sid);
		
		List<TbccBaseHisCar> list = 
			hiscaralarmdao.getHisCarAlarmBySidAndTime(BuildTable.tohisCarTable(proId), startTime, endTime, sid);
		List<TbccBaseHisCar> carList = MyUtil.distinctData(list);
		
		int mod = 1;
		String flagStr=null;
		
		for(TbccBaseHisCar hiscar:carList){
			//System.out.println(hiscar.getId()+" "+hiscar.getFlag()+" "+hiscar.getHistAlarmDataStr());
			flagStr = toBineryStr(hiscar.getFlag());
			for(int i=0;i<4;i++){
				mod=getMod(1,i);
//				System.out.println(hiscar.getFlag()+" "+mod+" "+hiscar.getHistAlarmDataStr()+" "+flagStr+" "+hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]+" "+hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]);
				if(flagStr.toCharArray()[i]!='1'){
					if(hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]!=hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]){
						for(TbccBaseHisCarAlarm caralarm : caralarmList){
//							System.out.println(hiscar.getParentId().compareTo(caralarm.getParentId())+" "+hiscar.getUpdateTime().compareTo(caralarm.getStartTime())+" "+caralarm.getTvalue()+" "+(caralarm.getTvalue()==(4-i)));
							if(hiscar.getParentId().compareTo(caralarm.getParentId())==0&&caralarm.getTvalue()==(4-i)&&(hiscar.getUpdateTime().compareTo(caralarm.getStartTime())==0
									||(hiscar.getUpdateTime().compareTo(caralarm.getStartTime())==1&&hiscar.getUpdateTime().compareTo(caralarm.getEndTime())==-1))){
								hiscar.setFlag(hiscar.getFlag()+mod);
								break;
							}
						}
						//内循环可能更改了flag标识，得重新获取并解析flag标识
						flagStr = toBineryStr(hiscar.getFlag());
						if(flagStr.toCharArray()[i]!='1'){
							TbccBaseHisCarAlarm calarm = new TbccBaseHisCarAlarm();
							calarm.setParentId(sid);
							calarm.setProjectId(proId);
							calarm.setStartTime(hiscar.getUpdateTime());
							calarm.setTvalue(4-i);
							if(hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]=='0'){
								calarm.setEvents((i==0?"RH1":("T"+(4-i)))+"温度超低("+hiscar.getAis()[3-i]+")");
							}else if(hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]=='0'){
								calarm.setEvents((i==0?"RH1":("T"+(4-i)))+"温度超高("+hiscar.getAis()[3-i]+")");
							}
							for(TbccBaseHisCar hiscar1:carList){
								if(hiscar1.getUpdateTime().compareTo(hiscar.getUpdateTime())==1){
									if(hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]==hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]
									   &&hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]==hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]){
										if(hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]=='0'){
											calarm.setEvents((i==0?"RH1":("T"+(4-i)))+"温度超低("+hiscar1.getAis()[3-i]+")");
										}else if(hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]=='0'){
											calarm.setEvents((i==0?"RH1":("T"+(4-i)))+"温度超高("+hiscar1.getAis()[3-i]+")");
										}
										hiscar1.setFlag(hiscar1.getFlag()+mod);
									}else if(hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]==hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]){
										calarm.setEndTime(hiscar1.getUpdateTime());
										hiscar1.setFlag(hiscar1.getFlag()+mod);
										//添加车载历史报警
										if(!hiscaralarmdao.insertHisAlarm(calarm)){
											System.out.println("插入车载历史报警数据出错");
										}
										break;
									}else if(hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]!=hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-2]
									 ||hiscar1.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]!=hiscar.getHistAlarmDataStr().toCharArray()[2*(i+1)-1]){
										calarm.setEndTime(hiscar1.getUpdateTime());
										//添加车载历史报警
										if(!hiscaralarmdao.insertHisAlarm(calarm)){
											System.out.println("插入车载历史报警数据出错");
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		List<TbccBaseHisCarAlarm> alarmList = hiscaralarmdao.getHisAlarm(proId, sid);
		
		return alarmList;
	}

	
	public List<TbccBaseHisCarAlarm> getHisCarAlarm(String proId, Long sid) {
		return hiscaralarmdao.getHisAlarm(proId, sid);
	}
	
	public boolean updateHisCarAlarm(String caralarmStr) {
		String[] caralarms=caralarmStr.split(";");
		String[] strs;
		boolean b = true;
		for(int i =0;i<caralarms.length;i++){
			strs = caralarms[i].split(",");
			b=hiscaralarmdao.updateHisAlarm(Long.parseLong(strs[0]), strs[1], strs[2], strs[3], strs[4]);
			if(!b){break;}
		}
		return b;
	}
	
	
	//将flag十进制数转换成4位二进制数
	private String toBineryStr(int i){
		char[] c={'0','0','0','0'};
		if(i==0){
			return String.valueOf(c);
		}else{
			if(i/8==1){c[0]='1';}
			if((i%8)/4==1){c[1]='1';}
			if((i%4)/2==1){c[2]='1';}
			if(i%2==1){c[3]='1';}
		}
		return String.valueOf(c);
	}
	
	private int getMod(int m,int i){
		for(int j=0;j<3-i;j++){
			m=m*2;
		}
		return m;
	}
}
