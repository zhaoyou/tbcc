package org.tbcc.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tbcc.log.LogOperate;
import org.tbcc.log.entity.TbccLog;
import org.tbcc.biz.ParamActionBiz;
import org.tbcc.biz.ParamCarVehicleBiz;
import org.tbcc.dao.ParamCarVehicleDao;
import org.tbcc.entity.config.TbccParaVehicleAlarm;

/**
 * ���ز������ò���ҵ��ʵ����
 * @author zhaoyou
 *
 */
public class ParamCarVehicleBizImpl implements ParamCarVehicleBiz {

	private ParamCarVehicleDao paramCarVehicleDao = null ;
	
	private ParamActionBiz	paramActionBiz = null ;
	
	private LogOperate logOperate = null ;
	
	private Map<String,String> thMap = new HashMap<String, String>(); 
	
	
	public ParamCarVehicleBizImpl(){
		super();
		thMap.put("1", "TPreAlarm_HighLimit") ;
		thMap.put("2", "TPreAlarm_HighDelay");
		thMap.put("3", "TPreAlarm_LowLimit");
		thMap.put("4", "TPreAlarm_LowDelay");
		
		thMap.put("5", "HPreAlarm_HighLimit");
		thMap.put("6", "HPreAlarm_HighDelay");
		thMap.put("7", "HPreAlarm_LowLimit");
		thMap.put("8", "HPreAlarm_LowDelay");
		
		thMap.put("9", "TAlarm_HighLimit");
		thMap.put("10", "TAlarm_HighDelay");
		thMap.put("11", "TAlarm_LowLimit");
		thMap.put("12", "TAlarm_LowDelay");
		
		thMap.put("13", "HAlarm_HighLimit");
		thMap.put("14", "HAlarm_HighDelay");
		thMap.put("15", "HAlarm_LowLimit");
		thMap.put("16", "HAlarm_LowDelay");
	}
	
	
	public void setParamCarVehicleDao(ParamCarVehicleDao paramCarVehicleDao) {
		this.paramCarVehicleDao = paramCarVehicleDao;
	}
	
	public void setParamActionBiz(ParamActionBiz paramActionBiz) {
		this.paramActionBiz = paramActionBiz;
	}

	public void setLogOperate(LogOperate logOperate) {
		this.logOperate = logOperate;
	}	
	
	
	public String getCarVehicleByParentId(Long pid) {
		if(pid==null || pid.equals(""))
			return "N/A" ;
		
		List<TbccParaVehicleAlarm>	list = paramCarVehicleDao.getByPid(pid);
		
		if(list==null || list.size()==0)
			return "N/A" ;
		
		return buildCarInfo(list.get(0));
	}

	public Integer updateCarVehicle(String projectId, String source,TbccLog log)  {
		try {
					
			if(projectId==null || projectId.equals("")|| source ==null || source.equals(""))
				return 0 ;
			//���͸��³��ز������� 2������²�����1 ����������״̬
			Long pid = paramActionBiz.addOperate(projectId, ParamActionBiz.FUNCTYPE_CARPARAM, (byte)2, (byte)1) ;
			
			if(pid.toString().equals("0"))
				return 0 ;
			
			if(paramCarVehicleDao.updateParamVehicle(CarInfoToSQL(source.split("&")[0], pid))>0){
				String message = buildUpdateLog(projectId, source.split("&")[1]);
				//message = message + " �����ɹ�" ;
				logOperate.addLog(log.getUid(), log.getUname(), LogOperate.OPTYPE_UPDATE, LogOperate.OPMODULE_ALARMPARAM,  message, log.getMachineName(), log.getExt01(), log.getExt02(), null);
				return pid.intValue() ;
			}
			
		} catch (Exception e) {
			System.out.println("���³�����־ʧ��: "+e.getMessage());
		}
		
		return 0 ;
	}
	
	/**
	 * �ѳ�����Ϣ��֯���ַ�����Ϣ
	 * @param paramVehicleAlarm	
	 * @return	eg: name,valid,value;name,valid,value name
	 */
	private String buildCarInfo(TbccParaVehicleAlarm paramVehicleAlarm){
		if(paramVehicleAlarm==null)
				return "N/A" ;
		StringBuffer sb = new StringBuffer("");
		
		//�¶�Ԥ��
		sb.append("1"+(paramVehicleAlarm.getTprealarm_highValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getTprealarm_high()+";"));
		
		sb.append("2"+(paramVehicleAlarm.getTprealarm_highDelayValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getTprealarm_highDelay()+";"));
		
		sb.append("3"+(paramVehicleAlarm.getTprealarm_lowValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getTprealarm_low()+";"));
		
		sb.append("4"+(paramVehicleAlarm.getTprealarm_lowDelayValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getTprealarm_lowDelay()+";"));
		
		//ʪ��Ԥ��
		sb.append("5"+(paramVehicleAlarm.getHprealarm_highValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getHprealarm_high()+";"));
	
		sb.append("6"+(paramVehicleAlarm.getHprealarm_highDelayValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getHprealarm_highDelay()+";"));
		
	
		sb.append("7"+(paramVehicleAlarm.getHprealarm_lowValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getHprealarm_low()+";"));
	
		sb.append("8"+(paramVehicleAlarm.getHprealarm_lowDelayValid().toString().equals("0")?
					",N/A,N/A;":
					",1,"+paramVehicleAlarm.getHprealarm_lowDelay()+";"));
		
		//�¶ȱ���
		sb.append("9"+(paramVehicleAlarm.getTalarm_highValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getTalarm_high()+";"));
	
		sb.append("10"+(paramVehicleAlarm.getTalarm_highDelayValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getTalarm_highDelay()+";"));
	
		sb.append("11"+(paramVehicleAlarm.getTalarm_lowValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getTalarm_low()+";"));
	
		sb.append("12"+(paramVehicleAlarm.getTalarm_lowDelayValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getTalarm_lowDelay()+";"));
		
		//ʪ�ȱ���
		sb.append("13"+(paramVehicleAlarm.getHalarm_highValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getHalarm_high()+";"));

		sb.append("14"+(paramVehicleAlarm.getHalarm_highDelayValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getHalarm_highDelay()+";"));
	

		sb.append("15"+(paramVehicleAlarm.getHalarm_lowValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getHalarm_low()+";"));

		sb.append("16"+(paramVehicleAlarm.getHalarm_lowDelayValid().toString().equals("0")?
				",N/A,N/A;":
				",1,"+paramVehicleAlarm.getHalarm_lowDelay()+";"));
		
		//ȥ�����һ���ֺ�;
		return sb.toString().length()>0?sb.toString().substring(0, sb.toString().length()-1):"" ;
	}
	
	/**
	 * ����Ҫ����������ת����Ϊsql���
	 * @param source
	 * @return
	 */
	private String CarInfoToSQL(String source,Long pid){
		String sb =new String(
				"insert into TbccParaVehicleAlarm (parentId" +
				"	,TPreAlarm_HighLimit,TPreAlarm_HighLimit_valid,TPreAlarm_HighDelay,TPreAlarm_HighDelay_valid,TPreAlarm_LowLimit,TPreAlarm_LowLimit_valid,TPreAlarm_LowDelay,TPreAlarm_LowDelay_valid" +
				"	,HPreAlarm_HighLimit,HPreAlarm_HighLimit_valid,HPreAlarm_HighDelay,HPreAlarm_HighDelay_valid,HPreAlarm_LowLimit,HPreAlarm_LowLimit_valid,HPreAlarm_LowDelay,HPreAlarm_LowDelay_valid" +
				"	,TAlarm_HighLimit,TAlarm_HighLimit_valid,TAlarm_HighDelay,TAlarm_HighDelay_valid,TAlarm_LowLimit,TAlarm_LowLimit_valid,TAlarm_LowDelay,TAlarm_LowDelay_valid" +
				"	,HAlarm_HighLimit,HAlarm_HighLimit_valid,HAlarm_HighDelay,HAlarm_HighDelay_valid,HAlarm_LowLimit,HAlarm_LowLimit_valid,HAlarm_LowDelay,HAlarm_LowDelay_valid )" +
				" values ("+pid+",1#,2#,3#,4#,5#,6#,7#,8#,9#,10#,11@,12@,13@,14@,15@,16@ )");
		
		//ÿ������ָ16���ֶ���
		String params[] = source.split(";") ;
		
		for(int i=0;i<params.length;i++){
			//�ָ�ÿ���ַ��� 1.�ֶ���š�2.�Ƿ���Ч��3.�ֶ�ֵ
			String carparam[] = params[i].split(",") ; 	
			String	splitChar = Integer.parseInt(carparam[0])>10?"@":"#" ;
				
			if(carparam[1].equals("N/A")){
				sb = sb.replace((carparam[0]+splitChar), "0,0") ;	//�����Ч�������0 ����ֵҲ����0
			}
			else {
				sb = sb.replace((carparam[0]+splitChar), carparam[2]+",1") ;	//�����Ч�������1����ֵ�������������			
			}
		}
		return sb.toString();
	}

	/**
	 * ���쳵�ظ�����־����message
	 * @param projectId	���ع��̱�ʾ
	 * @param source	������Ҫ���µ�����   
	 * @return			message
	 */
	private String buildUpdateLog(String projectId,String source){
		StringBuffer sb = new StringBuffer("���� "+projectId+"�����˲�������: ");
		String params[] = source.split(";");
		for(int i=0;i<params.length;i++){
			String []param = params[i].split(",");
			if(!param[1].equals(param[2])){
				sb.append("  "+thMap.get(param[0]+"")+"�� "+param[1]+"����"+param[2]);
			}		
		}
		return sb.toString();
	}


	public Integer addUpdateResultLog( TbccLog log) {
		try {	
			if(log==null)
				return 0 ;
			log.setOpType(LogOperate.OPTYPE_UPDATE);
			log.setOpModule(LogOperate.OPMODULE_ALARMPARAM);
			return logOperate.addLog(log);
		} catch (Exception e) {
			System.out.println("���Ӹ�����־ʧ��: "+e.getMessage());
			return 0 ;
		}
	}



}
