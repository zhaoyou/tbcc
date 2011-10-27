package org.tbcc.biz.impl;

import org.tbcc.biz.ParamActionBiz;
import org.tbcc.biz.ParamCarTransportBiz;
import org.tbcc.dao.ParamCarTransportDao;
import org.tbcc.entity.config.TbccParamVehicleTransport;
import org.tbcc.log.LogOperate;
import org.tbcc.log.entity.TbccLog;

/**
 * ����������Ϣ������ҵ��ʵ����
 * @author zhaoyou
 *
 */
public class ParamCarTransportBizImpl implements ParamCarTransportBiz {

	 
	private ParamCarTransportDao transprotDao = null ;
	
	private LogOperate logOperate = null ;
	
	private ParamActionBiz	paramActionBiz = null ;
		
	
	public void setParamActionBiz(ParamActionBiz paramActionBiz) {
		this.paramActionBiz = paramActionBiz;
	}

	public void setTransprotDao(ParamCarTransportDao transprotDao) {
		this.transprotDao = transprotDao;
	}

	public void setLogOperate(LogOperate logOperate) {
		this.logOperate = logOperate;
	}

	
	
	
	public Long addCarTransport(String projectId ,String source, TbccLog log) {
		
		if(projectId==null || projectId.equals("") || source==null || source.equals("") || log==null || log.equals(""))
		{
			return new Long(0) ;
		}
		
		//����һ���������������־
		Long pid = paramActionBiz.addOperate(projectId, ParamActionBiz.FUNCTYPE_TRANS, (byte)2, (byte)1) ;
		
		if(pid.equals(0))
			return new Long(0) ;
		
		
		
		
		return null;
	}

	public Integer addUpdateResultLog(TbccLog log) {
		
		return null;
	}

	public String getTransport(Long id) {
		
		return null;
	}
	
	
	/**
	 * ����һ�������������
	 * @return
	 */
	private TbccParamVehicleTransport buildTransport(String source){
			if(source==null || source.equals("N/A"))
				return null ;
			
			TbccParamVehicleTransport transport = new TbccParamVehicleTransport() ;
			
			String [] params = source.split(";") ;
			
			for(int i = 0 ;i<params.length;i++){
				String param[] = params[i].split(",") ;
				
				if(!param[0].equals("N/A")){
					if(i==0)
						transport.setBeginAddress(param[1]) ;
					if(i==1)
						transport.setEndAddress(param[1]) ;
					if(i==2)
						transport.setShipper(param[1]) ;
					if(i==3)
						transport.setCarrier(param[1]) ;
					if(i==4)
						transport.setReceiver(param[1]) ;				
				}
			}
			return null ;
	}
	
	/**
	 * ���������־�Ľ���ַ���
	 * @return
	 */
	private String buildAddLog(String projectId ,String source){
		
		
		if(source==null || source.equals("N/A"))
			return "" ;
		
		StringBuffer sb = new StringBuffer("����" + projectId+"������������Ϣ����: ");
		
		String [] params = source.split(";") ;		//�ָ�ÿ������
		
		
		String [] param = params[0].split(",") ;	
		if(!param[0].equals("N/A"))
			sb.append("beginAddress "+param[0]+"����Ϊ"+param[1]+";");
		
		
		
		param = params[1].split(",") ;
		if(!param[0].equals("N/A"))
			sb.append("endAddress "+param[0]+"����Ϊ "+param[1]+";") ;
		
		
		param = params[2].split(",") ;
		if(!param[0].equals("N/A"))
			sb.append("shipper "+param[0]+"����Ϊ "+param[1]+";") ;
		
		
		param = params[3].split(",") ;
		if(!param[0].equals("N/A"))
			sb.append("carrier "+param[0]+"����Ϊ "+param[1]+";") ;
		
		
		param = params[4].split(",") ;
		if(!param[0].equals("N/A"))
			sb.append("recevier "+param[0]+"����Ϊ "+param[1]+";") ;
		
		
		return sb.toString() ;
	}
	

}
