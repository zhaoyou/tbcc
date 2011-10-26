package org.fdap.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.FdapRefHisData;
import org.fdap.entity.Fdapaiinfo;

/**
 * 计算相应探头的最大、最小和平均值      以及整理仓库冷库历史数据
 * @author zhoukuanwei
 *
 */
public class GetMaxMinAvg {
	/**
	 * 计算车载历史数据中相应探头的最大、最小和平均值
	 * @param list		车载历史数据
	 * @return
	 */
	public List<Object> getCarMaxMinAvg(List<FdapCarHisData> list)
	{
		double[] tr;
		Double[] max=new Double[]{-300.0,-300.0,-300.0};
		Double[] min=new Double[]{300.0,300.0,300.0};
		Double[] avg=new Double[]{0.0,0.0,0.0};
		int[] count=new int[]{0,0,0};
		for (FdapCarHisData chv : list) {
			tr=new double[]{chv.getT1(),chv.getT2(),chv.getT3()};
			for(int i=0;i<3;i++){
				if(tr[i]!=-300){
					if(tr[i]>max[i])	max[i]=tr[i];
					if(tr[i]<min[i])	min[i]=tr[i];
					avg[i]+=tr[i];
					count[i]++;
				}
			}
		}
		for(int i=0;i<3;i++){
			if(count[i]==0){
				max[i]=-300.0;
				min[i]=-300.0;
				avg[i]=-300.0;
			}else{
				//保留两位小数
				avg[i]=Math.round((avg[i]/count[i])*100)/100.0;
			}
		}
		List<Object> dlist=new ArrayList<Object>();
		dlist.add(max);
		dlist.add(min);
		dlist.add(avg);
		return dlist;
	}
	
	/**
	 * 计算仓库冷库历史数据中相应探头的最大、最小和平均值
	 * @param reflist		仓库冷库历史数据
	 * @param ailist		仓库冷库的探头列表
	 * @return
	 */
	public List<Object> getRefMaxMinAvg(List<FdapRefHisData> reflist,List<Fdapaiinfo> ailist)
	{
		double[] max=new double[ailist.size()];
		double[] min=new double[ailist.size()];
		double[] avg=new double[ailist.size()];
		int[] count=new int[ailist.size()];
		String aiguid;
		Integer refaiid=-1;
		for(int i=0;i<ailist.size();i++){
			max[i]=-300;
			min[i]=300;
			avg[i]=0;
			count[i]=0;
		}
		
		for (FdapRefHisData frh : reflist) {
			//一个判断变量，该条探头历史数据是否为该冷库下的探头数据
			refaiid=-1;
			aiguid=frh.getAiGuid();
			for(Fdapaiinfo fai : ailist){
				if(aiguid.equals(fai.getAiguid())){
					refaiid=fai.getReid();
				}
			}
			if(refaiid!=-1){
				for(int i=0;i<ailist.size();i++){
					if(ailist.get(i).getAiguid().equals(frh.getAiGuid())&&frh.getData().compareTo(-300.0)!=0){
						if(frh.getData()>max[i]) max[i]=frh.getData();
						if(frh.getData()<min[i]) min[i]=frh.getData();
						avg[i]+=frh.getData();
						count[i]++;
						break;
					}
				}
			}
		}
		
		for(int i=0;i<ailist.size();i++){
			if(count[i]==0){
				max[i]=-300;
				min[i]=-300;
				avg[i]=-300;
			}
			else{
				//保留两位小数
				avg[i]=Math.round((avg[i]/count[i])*100)/100.0;
			}
		}
		
		List<Object> dlist=new ArrayList<Object>();
		dlist.add(max);
		dlist.add(min);
		dlist.add(avg);
		return dlist;
	}
	
	/**
	 * 将仓库冷库中每条探头历史数据整理成每个仓库冷库的历史数据
	 * @param list			仓库冷库历史数据
	 * @param timeList		根据分页查询得到的时间刻
	 * @param ailist		仓库冷库的探头列表
	 * @return
	 */
	public List<Object> getrefhis(List<FdapRefHisData> list,List<Date> timeList,List<Fdapaiinfo> ailist)
	{
		Object[] data=null;
		List<Object> olist=new ArrayList<Object>();
		for(Date time : timeList){
			data=new Object[ailist.size()+2];
			//每个探头数据默认赋值为-300.0
			for(int i=0;i<ailist.size();i++){
				data[i]=-300.0;
			}
			//保存历史数据的时间
			data[ailist.size()]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
			//保存报警标志，默认为1 (0代表报警，1代表正常)
			data[ailist.size()+1]=1;
			for(FdapRefHisData frh : list){
				if(frh.getTime().compareTo(time)==0){
					for(int i=0;i<ailist.size();i++){
						if(ailist.get(i).getAiguid().equals(frh.getAiGuid())){
							data[i]=frh.getData();
							if(frh.getIsAlarm()==0){
								//如果报警标志为0，则保存报警标志为0 
								data[ailist.size()+1]=0;
							}
							break;
						}
					}
				}
			}
			olist.add(data);
		}
		return olist;
	}
}
