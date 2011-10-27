package org.tbcc.util;

import java.text.DecimalFormat;
import java.util.List;

import org.tbcc.biz.HisRefBiz;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisRef;
import org.tbcc.entity.TbccBaseHisRef_Ex;

/**
 * �������Ҫ����������ʷ����չʾ��ͳ�����ֵ����Сֵ��ƽ��ֵ��
 * @author Administrator
 *
 */
public class Calc {
	
	/**
	 * �����ͳ���ƶ�������ʷ����ʱ���õķ���
	 * @param list
	 * @return
	 */
	public Object[] calcHisCarData(List<TbccBaseHisCar> list2){
		
	  /**
	   * �����������飬������ʾ��Сֵ�����ֵ���ܺ͡�ƽ��ֵ���ĸ�Ai̽ͷ
	   */
	   double min[] = new double[]{30000,30000,30000,30000},
	          max[] = new double[]{-30000,-30000,-30000,-30000},
	          sum[] = new double[]{0,0,0,0},
	          avg[] = new double[]{0,0,0,0};
	   
	   Object[] result =  null ;
		
		if(list2!=null && list2.size() > 0){	
			result = new Object[3] ;  //������
			
			
			
		/**
		 *  ����ͳ��
		 */
		double avg1,avg2,avg3,avg4 ;
		      avg1=avg2=avg3=avg4=0 ;  //�����ĸ���������ͳ��ÿһ��̽ͷ��������Ч�Եĸ���
		
		for(int i=0; i<list2.size(); i++){
			
			TbccBaseHisCar m = (TbccBaseHisCar)list2.get(i);
			
			/**
			 * ���̽ͷ��������-300������������Ч�����������
			 */
			if(m.getAi1()!=-300){
				avg1++ ;
				if(min[0]>m.getAi1())            //��Сֵ    
					min[0]=m.getAi1() ;
				
				if(max[0]<m.getAi1())			//���ֵ
					max[0]= m.getAi1() ;
				
					sum[0]+=m.getAi1() ;		//���
			}
			
			//ͬ��
				
			if(m.getAi2()!=-300){
				avg2++ ;
				if(min[1]>m.getAi2())
					min[1]=m.getAi2() ;
				if(max[1]<m.getAi2())
					max[1]= m.getAi2() ;
					sum[1]+=m.getAi2() ;
			}
			
			//ͬ��
			if(m.getAi3()!=-300){
				avg3++ ;
				if(min[2]>m.getAi3())
					min[2]=m.getAi3() ;
				if(max[2]<m.getAi3())
					max[2]= m.getAi3() ;
					sum[2]+=m.getAi3() ;
			}
			
			//ͬ��
			if(m.getAi4()!=-300){
				avg4++ ;
				if(min[3]>m.getAi4())
					min[3]=m.getAi4() ;
				if(max[3]<m.getAi4())
					max[3]= m.getAi4() ;
					sum[3]+=m.getAi4() ;
			}
			
		}
		/**
		 * ���ÿһ��̽ͷ��ƽ��ֵ
		 */
		avg[0] = (avg1==0?-300:sum[0]/avg1);
		avg[1] = (avg2==0?-300:sum[1]/avg2);
		avg[2] = (avg3==0?-300:sum[2]/avg3);
		avg[3] = (avg4==0?-300:sum[3]/avg4);
		
		/**
		 * Ϊ��ҳ����ʾ�����û����Сֵ(���ݶ���Ч)����ʾ-300
		 */
		for(int i=0;i<4;i++){
			min[i] = min[i]==30000?min[i]/100*-1:min[i] ;
			max[i] = max[i]==-30000?max[i]/100:max[i];
		}
		
		
		
		/**
		 * ����Сֵ�����ֵ��ƽ��ֵ���浽������
		 */
		result[0] = min ;
		result[1] = max ;
		result[2] = avg ;
		 
	}
		return result ;
	}
	
	
	/**
	 * �����ͳ��С������ʷ���ݵ��õķ���
	 * @param list2
	 * @return
	 */
	public Object [] calcHisBoxData(List<TbccBaseHisBox> list2){
		double min = 30000,max=-30000,sum=0,avg=-300;
		int i =0 ;
		
		Object[] result = new Object[3] ;
		
		if(list2!=null && list2.size()>0){
			for (TbccBaseHisBox tbccBaseHisBox : list2) {
				if(tbccBaseHisBox.getAi1()!=-300){
					i++ ;
					
					if(tbccBaseHisBox.getAi1()>max)
						max = tbccBaseHisBox.getAi1() ;
					if(tbccBaseHisBox.getAi1()<min)
						min = tbccBaseHisBox.getAi1() ;
					
					sum +=tbccBaseHisBox.getAi1() ;
				}
			}
			
			min = min==30000?min/100*-1:min ;
			max = max==-30000?max/100:max ;
			if(i!=0){
				avg = sum /i ;
				avg = Double.parseDouble((new DecimalFormat("##.##").format(avg)));
			}
				
			
			result[0] = min ;
			result[1] = max ;
			result[2] = avg ;
			
			
			return result ;
		}
		return null ;
	}
	
	/**
	 *  ����������ʷ���ݼ��ϵ��õķ���
	 * @param list2
	 * @return
	 */
	/**
	 * ������ʪ�ȡ��Լ����ֵ����Сֵ��ƽ��ֵ
	 * @param data
	 * @param tbccBaseHisRef
	 * @param tlist
	 * @param hlist
	 */
	public  String[] calcRef(Integer refId, TbccBaseHisRef tbccBaseHisRef,List<Integer> tlist,List<Integer> hlist){
		
		int i = 1 ;
		
		double tmin = 30000 ;
		double tmax = -30000 ;
		double tavg = 0 ;
		int    tcount = 0 ;
		
		double hmin = 30000 ;
		double hmax = -30000 ;
		double havg = 0 ;
		int    hcount  = 0 ;
		
		//8��ȷ������ͷ(1��ʱ�䡢6����ʪ��״̬(�����С��ƽ��)��һ������״̬ +  �¶���ͷ��+ʪ����ͷ)
		int allLength = 8 + tlist.size() + hlist.size()  ; 
		
		String [] data = new String[allLength];
		
	
		
		data[0] = tbccBaseHisRef.getUpdateStr() ;		//	 ʱ����  	one
		
		for (Integer integer : tlist) {					//	 �¶���		tlist.size 
			if(integer.equals(1)){
				if(tbccBaseHisRef.getAi1()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					
					data[i++] = tbccBaseHisRef.getAi1().toString() ;
					tcount ++ ;
					tavg +=tbccBaseHisRef.getAi1() ;
					
					if(tbccBaseHisRef.getAi1()>tmax)
						tmax = tbccBaseHisRef.getAi1() ;
					if(tbccBaseHisRef.getAi1()<tmin)
						tmin = tbccBaseHisRef.getAi1() ;
					continue ;
				}	
			}
				
			if(integer.equals(2)){
				if(tbccBaseHisRef.getAi2()==-300){
					data[i++] = "--";
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi2().toString() ;
					tcount ++ ;
					tavg +=tbccBaseHisRef.getAi2() ;
					
					if(tbccBaseHisRef.getAi2()>tmax)
						tmax = tbccBaseHisRef.getAi2() ;
					if(tbccBaseHisRef.getAi2()<tmin)
						tmin = tbccBaseHisRef.getAi2() ;
					continue ;
				}
			}
			
			
			if(integer.equals(3)){
				if(tbccBaseHisRef.getAi3()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi3().toString() ;
					tcount++ ;
					tavg +=tbccBaseHisRef.getAi3() ;
					
					if(tbccBaseHisRef.getAi3()>tmax)
						tmax = tbccBaseHisRef.getAi3() ;
					if(tbccBaseHisRef.getAi3()<tmin)
						tmin = tbccBaseHisRef.getAi3() ;
					continue ;
				}
				
			}
			if(integer.equals(4)){
				if(tbccBaseHisRef.getAi4()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi4().toString() ;
					tavg += tbccBaseHisRef.getAi4() ;
					tcount ++ ;
					if(tbccBaseHisRef.getAi4()>tmax)
						tmax = tbccBaseHisRef.getAi4() ;
					if(tbccBaseHisRef.getAi4()<tmin)
						tmin = tbccBaseHisRef.getAi4() ;
					continue ;
				}
				
			}
			
			if(integer.equals(5)){
				if(tbccBaseHisRef.getAi5()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi5().toString() ;
					tcount ++  ;
					tavg +=tbccBaseHisRef.getAi5() ;
					if(tbccBaseHisRef.getAi5()>tmax)
						tmax = tbccBaseHisRef.getAi5() ;
					if(tbccBaseHisRef.getAi5()<tmin)
						tmin = tbccBaseHisRef.getAi5() ;
					continue ;
				}
				
			}
			
			if(integer.equals(6)){
				if(tbccBaseHisRef.getAi6()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi6().toString() ;
					
					tcount ++ ;
					tavg += tbccBaseHisRef.getAi6() ;
					
					if(tbccBaseHisRef.getAi6()>tmax)
						tmax = tbccBaseHisRef.getAi6() ;
					if(tbccBaseHisRef.getAi6()<tmin)
						tmin = tbccBaseHisRef.getAi6() ;
					continue ;
				}
				
			}
			if(integer.equals(7)){
				if(tbccBaseHisRef.getAi7()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi7().toString() ;
					tcount ++ ;
					tavg +=tbccBaseHisRef.getAi7() ;
					if(tbccBaseHisRef.getAi7()>tmax)
						tmax = tbccBaseHisRef.getAi7() ;
					if(tbccBaseHisRef.getAi7()<tmin)
						tmin = tbccBaseHisRef.getAi7() ;
					continue ;
				}
				
			}
			if(integer.equals(8)){
				if(tbccBaseHisRef.getAi8()==-300){
					data[i++] = "--" ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi8().toString() ;
					tcount ++ ;
					tavg +=tbccBaseHisRef.getAi8() ;
					if(tbccBaseHisRef.getAi8()>tmax)
						tmax = tbccBaseHisRef.getAi8() ;
					if(tbccBaseHisRef.getAi8()<tmin)
						tmin = tbccBaseHisRef.getAi8();
					continue ;
				}
				
			}
			if(integer.equals(9)){
				if(tbccBaseHisRef.getAi9()==-300){
					data[i++] = "--"	 ;
					continue ;
				}else{
					data[i++] = tbccBaseHisRef.getAi9().toString() ;
					tcount ++ ;
					tavg +=tbccBaseHisRef.getAi9() ;
					if(tbccBaseHisRef.getAi9()>tmax)
						tmax = tbccBaseHisRef.getAi9() ;
					if(tbccBaseHisRef.getAi9()<tmin)
						tmin = tbccBaseHisRef.getAi9() ;
					continue ;
				}
				
			}				
		}
		
		
		
		 
		 //Ĭ�ϲ�Ӧ���ж��Ƿ����ʪ��̽ͷ�� 
		  //�ж����
//		  if(hlist==null ||  hlist.size()==0){		//û��ʪ��̽ͷ
//			  data[i++] = "--" ;
//			  data[i++] = "--" ;
//			  data[i++] = "--" ;
//		  }else{					//��ʪ��̽ͷ
		
		
			  for (Integer integer2 : hlist) {				  
				if(integer2.equals(10)){
					if(tbccBaseHisRef.getAi10()==-300){
						data[i++] = "--" ;
						continue ;
					}else{
						data[i++] = tbccBaseHisRef.getAi10().toString() ;
						hcount ++ ;
						havg +=tbccBaseHisRef.getAi10();
						if(tbccBaseHisRef.getAi10()>hmax)
							hmax = tbccBaseHisRef.getAi10() ;
						if(tbccBaseHisRef.getAi10()<hmin)
							hmin = tbccBaseHisRef.getAi10()  ;
						continue ;
					}
					
				}
				
				if(integer2.equals(11)){
					if(tbccBaseHisRef.getAi11()==-300){
						data[i++] = "--" ;
						continue ;
					}else{
						data[i++] = tbccBaseHisRef.getAi11().toString() ;
						hcount ++ ;
						havg +=tbccBaseHisRef.getAi11()  ;
						if(tbccBaseHisRef.getAi11()>hmax)
							hmax = tbccBaseHisRef.getAi11() ;
						if(tbccBaseHisRef.getAi11()<hmin)
							hmin = tbccBaseHisRef.getAi11() ;
						continue ;
					}
				}
				
				if(integer2.equals(12)){
					if(tbccBaseHisRef.getAi12()==-300){
						data[i++] = "--" ;
						continue ;
					}else{
						data[i++] = tbccBaseHisRef.getAi12().toString() ;
						hcount ++ ;
						havg +=tbccBaseHisRef.getAi12() ;
						
						if(tbccBaseHisRef.getAi12()>hmax)
							hmax = tbccBaseHisRef.getAi12() ;
						if(tbccBaseHisRef.getAi12()<hmin)
							hmin = tbccBaseHisRef.getAi12() ;
						
						continue ;
					}
				}					
			}
			   
		
		  

			//ͳ���¶ȵ����ֵ����Сֵ��ƽ��ֵ
		  data[i++] = tmax==-30000?"--":tmax+"" ;
		  data[i++] = tmin==30000?"--":tmin +"" ;
		  data[i++] = tavg==0?"--":new DecimalFormat("##.##").format((tavg/tcount)) ;
		  
		  //����ʪ�����ֵ����Сֵ��ƽ��ֵ
		  data[i++] = hmax==-30000?"--":hmax+"" ;
		  data[i++] = hmin==30000?"--":hmin+"" ;
		  data[i++] = havg==0?"--" :new DecimalFormat("##.##").format((havg/hcount));
		  
		  
	  
		  if(refId.equals(1)){
			  if(tbccBaseHisRef.getAlarmStatus_ref1().equals(HisRefBiz.ALARM)){
				  data[i++] = "<font color='red'>����</font>" ;
			  }else if(tbccBaseHisRef.getAlarmStatus_ref1().equals(HisRefBiz.PREALARM)){
				  data[i++] = "<font color='orange'>Ԥ��</font>" ;
			  }else{
				  data[i++] = "����" ;
			  }
			  
		  }
			
		  if(refId.equals(2)){
			  if( tbccBaseHisRef.getAlarmStatus_ref2().equals(HisRefBiz.ALARM)){
				  data[i++] = "<font color='red'>����</font>" ;
			  }else if ( tbccBaseHisRef.getAlarmStatus_ref2().equals(HisRefBiz.PREALARM)){
				 data[i++] = "<font color='orange'>Ԥ��</font>" ;
			  }else {
				  data[i++] = "����" ;
			  }
		  }
		  
		  if(refId.equals(3)){
			  if( tbccBaseHisRef.getAlarmStatus_ref3().equals(HisRefBiz.ALARM)){
				  data[i++] = "<font color='red'>����</font>" ;
			  }else if ( tbccBaseHisRef.getAlarmStatus_ref3().equals(HisRefBiz.PREALARM)){
				 data[i++] = "<font color='orange'>Ԥ��</font>" ;
			  }else {
				  data[i++] = "����" ;
			  }
		  }
		  
		  if(refId.equals(4)){
			  if( tbccBaseHisRef.getAlarmStatus_ref4().equals(HisRefBiz.ALARM)){
				  data[i++] = "<font color='red'>����</font>" ;
			  }else if ( tbccBaseHisRef.getAlarmStatus_ref4().equals(HisRefBiz.PREALARM)){
				 data[i++] = "<font color='orange'>Ԥ��</font>" ;
			  }else {
				  data[i++] = "����" ;
			  }
			 
		  }
			

		  return data ;
	}	
	
	
	/**
	 * ��������豸�ֿ���ʷ����
	 * @param refId				����ʶId
	 * @param tbccBaseHisRef	ʵʱ����
	 * @param tlist				�¶�̽ͷ�б�
	 * @param hlist				ʪ��̽ͷ�б�
	 * @return
	 */
	public String[] calcExRefDataInfo(Integer refId, TbccBaseHisRef_Ex exHisRef,List<Integer> tlist,List<Integer> hlist){
		
		Integer i = 1 ;
		
		Double tmin = 30000d ;
		Double tmax = -30000d ;
		Double tavg = 0d ;
		Integer tcount = 0 ;
		
		Double	hmin = 30000d ;
		Double hmax = -30000d ;
		Double havg = 0d ;
		Integer hcount = 0 ;
		
		//�������鱣������
		Integer allLength = 8 + tlist.size()+hlist.size() ;	
		String [] data = new String[allLength];
			
		//��ȡʱ���ֶ�
		data[0] = exHisRef.getUpdateStr() ;
		
		//����һ�����鱣��1-32��̽ͷ������
		Double [] ais = new Double[]{
			exHisRef.getAi1() ,exHisRef.getAi2(),exHisRef.getAi3(),exHisRef.getAi4(),exHisRef.getAi5(),exHisRef.getAi6(),
			exHisRef.getAi7(),exHisRef.getAi8(),exHisRef.getAi9(),exHisRef.getAi10(),exHisRef.getAi11(),exHisRef.getAi12(),
			exHisRef.getAi13(),exHisRef.getAi14(),exHisRef.getAi15(),exHisRef.getAi16(),
			exHisRef.getAi17(),exHisRef.getAi18(),exHisRef.getAi19(),exHisRef.getAi20(),exHisRef.getAi21(),exHisRef.getAi22(),
			exHisRef.getAi23(),exHisRef.getAi24(),exHisRef.getAi25(),exHisRef.getAi26(),
			exHisRef.getAi27(),exHisRef.getAi28(),exHisRef.getAi29(),exHisRef.getAi30(),exHisRef.getAi31(),exHisRef.getAi32()
		};
		
		//����һ�����鱣�汨��״̬
		Integer [] alarms = new Integer[]{
				exHisRef.getRef1_RefAlarmState(),exHisRef.getRef2_RefAlarmState(),exHisRef.getRef3_RefAlarmState(),exHisRef.getRef4_RefAlarmState(),
				exHisRef.getRef5_RefAlarmState(),exHisRef.getRef6_RefAlarmState(),exHisRef.getRef7_RefAlarmState(),exHisRef.getRef8_RefAlarmState(),
				exHisRef.getRef9_RefAlarmState(),exHisRef.getRef10_RefAlarmState(),exHisRef.getRef11_RefAlarmState(),exHisRef.getRef12_RefAlarmState(),
				exHisRef.getRef13_RefAlarmState(),exHisRef.getRef14_RefAlarmState(),exHisRef.getRef15_RefAlarmState(),exHisRef.getRef16_RefAlarmState()
		} ;
		
		
		
		//ѭ��ÿһ���¶�̽ͷ�˿�
		for (Integer t : tlist) {
			for(int j=1;j<=16;j++){
				if(t==j){
					if(ais[j-1]==-300){
						data[i++] = "--" ;
						break ;
					}else{
						data[i++] = ais[j-1].toString() ;
						tcount ++ ;
						tavg +=ais[j-1] ;
						
						if(ais[j-1]>tmax)
							tmax = ais[j-1] ;
						if(ais[j-1]<tmin)
							tmin = ais[j-1] ;
						
						break ;
					}
				}
			}
		}
		
		//ѭ��ÿһ��ʪ��̽ͷ�˿�		
		for(Integer h :hlist){
			for(int k=17;k<=32;k++){
				if(h==k){
					if(ais[k-1]==-300){
						data[i++] = "--" ;
						break ;
					}else{
						data[i++] = ais[k-1].toString();
						hcount ++ ;
						havg +=ais[k-1] ;
						
						if(ais[k-1]>hmax)
							hmax = ais[k-1] ;
						if(ais[k-1]<hmin)
							hmin = ais[k-1] ;
						break ;
					}
				}
			}
		}
		
		
		//ͳ���¶ȵ����ֵ����Сֵ��ƽ��ֵ
		  data[i++] = tmax==-30000?"--":tmax+"" ;
		  data[i++] = tmin==30000?"--":tmin +"" ;
		  data[i++] = tavg==0?"--":new DecimalFormat("##.##").format((tavg/tcount)) ;
		  
		  //����ʪ�����ֵ����Сֵ��ƽ��ֵ
		  data[i++] = hmax==-30000?"--":hmax+"" ;
		  data[i++] = hmin==30000?"--":hmin+"" ;
		  data[i++] = havg==0?"--" :new DecimalFormat("##.##").format((havg/hcount));
		
		  
		  //���ñ���״̬
		  
		  for(int p=1;p<=16;p++){
			  if(p==refId){
				  
				  if( alarms[p-1].equals(HisRefBiz.ALARM)){
						  data[i++] = "<font color='red'>����</font>" ;
					 }else if ( alarms[p-1].equals(HisRefBiz.PREALARM)){
						 data[i++] = "<font color='orange'>Ԥ��</font>" ;
					 }else {
						  data[i++] = "����" ;
					 }	
					  	
				 break ;
			  }
		  }
		  
		  
	
		 return data ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ͳ�������ʷ���ݵ����ֵ����Сֵ��ƽ��ֵ(data ���ݼ��� len ��ʪ�ȵ���ͷ��)
	 * @return
	 */
	public Object[] calcHisRefData(List<String[]> data,int len){
		
		if(data==null || data.size()==0)
				return null ;
		
		double min[] = new double[len] ,
			   max[] = new double[len] ,
			   sum[] = new double[len] ,
			   avg[] = new double[len] ;
		
		int youxiao[] = new int[len] ;			//	����̽ͷ��������Ч�Եĸ���
		
		Object[] result = new Object[3] ;		//����һ���������飬���ڱ�������Ľ��
		
		for (int i = 0; i < min.length; i++) {	//��ʼ�����ϵ�ֵ
			min[i] = 30000 ;
			max[i] = -30000 ;
			avg[i] = -300 ;
			sum[i] = 0 ;
			youxiao[i] = 0 ;
		}
		
		
		
		
		
		if(data!=null && data.size()>0){		//	�жϴ��ݹ����Ľ�����Ƿ�������
			
			
			for(int i=0;i<data.size();i++){
				String [] d = data.get(i);
				for (int j = 0; j < len; j++) {
					if(!d[j+1].equals("--")){
						double lie = Double.parseDouble(d[j+1]) ;
						
						if(lie>max[j])
							max[j] = lie ;
						if(lie<min[j])
							min[j] = lie ;
						
						youxiao[j] ++ ;
						
						sum[j] = sum[j]+lie ;
					}
				}
			}
			
			//����ͳ��ƽ��ֵ,����û�д���ȫ���ǷǷ����������avg��i��Ĭ�ϳ�ʼ��ʱ-300��ҳ������˴���
			for (int i = 0; i < len; i++) {
				if(youxiao[i]!=0){
					avg[i] =Double.parseDouble(new DecimalFormat("##.##").format(sum[i]/youxiao[i]));
				}
			}
			
			//������Сֵ��300Ϊ-300
			for (int i = 0; i < len	; i++) {
				min[i] = min[i]==30000?min[i]/100*-1:min[i];
				max[i] = max[i]==-30000?max[i]/100:max[i];
			}
			
			result[0] = min	 ;
			result[1] = max  ;
			result[2] = avg  ;
			
		}	
		return result ;
	}
	
}
