package org.tbcc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.tbcc.biz.IGetImageEachControlAllDatas;
import org.tbcc.dao.IImgDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;
import org.tbcc.entity.TbccProjectImages;

public class GetImageEachControlAllDatasImpl implements
		IGetImageEachControlAllDatas {
	static long eImageId = -10000;
	static int count = -1;
	@SuppressWarnings("unchecked")
	static List list = new ArrayList();
	IImgDao imgDaoImpl;

	/**
	 * 在页面每次重复刷新同一图层数据时仅调用该函数一次；以获取图层的路径和所有的监控头数据（温度除外）
	 * 
	 * @param imageId
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public List invokeOnlyOne(long imageId, String projectId) {
		
		//如果成功返回list集合中list[0] = path ,list[1...n] = list<ImageControl>
		List plist = new ArrayList();
		
		//如果图层标示Id非法、直接返回"no_picture"
		if(imageId<=0){
			plist.add("no_picture");
			return plist ;
		}
		
		//获取该图层对应的图层物理路径，如果不存在、直接返回、如果存在保存之list集合中
		String path = imgDaoImpl.getImagePathByImgID(imageId);
		/**
		 * 如果图层的路径为空，则为用户返回无图片的提示信息
		 */
		if (path == null) {
			plist.add("no_picture");
			return plist;
		} else {
			plist.add(path);
		}
		
		//获取单个图层对应的所有图元控件
		List plist2 = imgDaoImpl.findImageControlsByImgID(imageId, projectId);
		plist.addAll(plist2);
		return plist;
	}

	public String getEachControlAllDates(long imageId, String projectId) {
		long sImageId = imageId;
		if ((eImageId != sImageId)) {
			list = invokeOnlyOne(imageId, projectId);
			eImageId = sImageId;
		}
		/**
		 * 如果图层上无探头，则为用户返没有监控的空图层
		 */
		if (list.size() == 1)
			return (String) list.get(0);
		
		
		//定义一个length为path + list<ImageControl>的字符串数组
		String controlDataArray[] = new String[list.size()];
		
		//存放路径
		controlDataArray[0] = (String) list.get(0);

		String tName;
		String aName;
		String tData;
		String taName;
		/**
		 * 这是之前定义的28个端口 12(ai)+4(alarm)+4(lostPower)+4(sound)+4(door)
		 * 扩展兼容模块后已经有	   32(ai)+16(alarm)+4(lostPower)+4(sound)+4(door)
		 */
		//double temp[] = new double[28];			//12(ai)+4(alarm)+4(lostPower)+4(sound)+4(door)
		
		double temp[] = new double[60] ;
		
		//获取冷库工程对应的所有实时数据
		List<TbccBaseRealRef> dataList = imgDaoImpl.getImageAllControlRealData(
				imageId, projectId);
		
		
		
		//如果实时数据不存在
		if (dataList == null) {
			for (int k = 1; k < list.size(); k++) {
				tName = ((TbccImageControls) list.get(k)).getCtop() + ","
						+ ((TbccImageControls) list.get(k)).getCleft() + ","
						+ ((TbccImageControls) list.get(k)).getCheight() + ","
						+ ((TbccImageControls) list.get(k)).getCwidth() + ","
						+ ((TbccImageControls) list.get(k)).getTitleMsg() + ","
						+ ((TbccImageControls) list.get(k)).getUnitMsg();
				
				//实时数据不存在、如果一个探头为温湿度则表明**.**, 为DiDo表明"无数据"
				if (((TbccImageControls) list.get(k)).getDataType() != 3) {
					controlDataArray[k] = tName.concat(",").concat("**.**");
				} else {
					controlDataArray[k] = tName.concat(",").concat("无数据");
				}
			}
			
		} else {
			
			//循环遍历每一个图元控件
			for (int i = 1; i < controlDataArray.length; i++) {
				
				boolean find = false;
				
				//拼装显示的信息
				taName = ((TbccImageControls) list.get(i)).getCtop() + ","
						+ ((TbccImageControls) list.get(i)).getCleft() + ","
						+ ((TbccImageControls) list.get(i)).getCheight() + ","
						+ ((TbccImageControls) list.get(i)).getCwidth() + ","
						+ ((TbccImageControls) list.get(i)).getTitleMsg() + ","
						+ ((TbccImageControls) list.get(i)).getUnitMsg();
				
				//循环实时数据,匹配每一个探头图元是否存在实时数据
				for (int f = 0; f < dataList.size(); f++) {
					if ((int)dataList.get(f).getNeiId() == (int)((TbccImageControls) list.get(i)).getNetid()){
						find = true;
						break ;
					}else{
						find = false;
					}
				}
				
				//如果当前探头找到了相应的实时数据的设备
				if (find == true) {
					// 循环实时数据列表
					for (int j = 0; j < dataList.size(); j++) {
						//处于同一设备netId相等
						if ((int) dataList.get(j).getNeiId() == (int) ((TbccImageControls) list.get(i)).getNetid()) {
							
							//判断是否已经连接上
							if (dataList.get(j).getConnectStatus() == 1) {
								
								if (((TbccImageControls) list.get(i)).getDataType() != 3) {
									controlDataArray[i] = taName.concat(",").concat("**.**");
								} else {
									controlDataArray[i] = taName.concat(",").concat("无数据");
								}
							} else {	//当前设备处于连接状态
								temp[0] = dataList.get(j).getAi1();
								temp[1] = dataList.get(j).getAi2();
								temp[2] = dataList.get(j).getAi3();
								temp[3] = dataList.get(j).getAi4();
								temp[4] = dataList.get(j).getAi5();
								temp[5] = dataList.get(j).getAi6();
								temp[6] = dataList.get(j).getAi7();
								temp[7] = dataList.get(j).getAi8();
								temp[8] = dataList.get(j).getAi9();
								temp[9] = dataList.get(j).getAi10();
								temp[10] = dataList.get(j).getAi11();
								temp[11] = dataList.get(j).getAi12();
								
								
								//后面兼容模块增加的ai
								
								temp[12] = dataList.get(j).getAi13() ;
								temp[13] = dataList.get(j).getAi14() ;
								temp[14] = dataList.get(j).getAi15() ;
								temp[15] = dataList.get(j).getAi16() ;
								temp[16] = dataList.get(j).getAi17() ;
								temp[17] = dataList.get(j).getAi18() ;
								temp[18] = dataList.get(j).getAi19() ;
								temp[19] = dataList.get(j).getAi20() ;
								temp[20] = dataList.get(j).getAi21() ;
								temp[21] = dataList.get(j).getAi22() ;
								temp[22] = dataList.get(j).getAi23() ;
								temp[23] = dataList.get(j).getAi24() ;
								temp[24] = dataList.get(j).getAi25() ;
								temp[25] = dataList.get(j).getAi26() ;
								temp[26] = dataList.get(j).getAi27() ;
								temp[27] = dataList.get(j).getAi28() ;
								temp[28] = dataList.get(j).getAi29() ;
								temp[29] = dataList.get(j).getAi30() ;
								temp[30] = dataList.get(j).getAi31() ;
								temp[31] = dataList.get(j).getAi32() ;
								
								
								
								
								// ----------------------------------------------------
								temp[32] = dataList.get(j).getAlarmStatus_Ref1();
								temp[33] = dataList.get(j).getAlarmStatus_Ref2();
								temp[34] = dataList.get(j).getAlarmStatus_Ref3();
								temp[35] = dataList.get(j).getAlarmStatus_Ref4();
								
								//后面兼容模块增加的探头
								
								temp[36] = dataList.get(j).getAlarmStatus_Ref5();
								temp[37] = dataList.get(j).getAlarmStatus_Ref6();
								temp[38] = dataList.get(j).getAlarmStatus_Ref7();
								temp[39] = dataList.get(j).getAlarmStatus_Ref8();
								temp[40] = dataList.get(j).getAlarmStatus_Ref9();
								temp[41] = dataList.get(j).getAlarmStatus_Ref10();
								temp[42] = dataList.get(j).getAlarmStatus_Ref11();
								temp[43] = dataList.get(j).getAlarmStatus_Ref12();
								temp[44] = dataList.get(j).getAlarmStatus_Ref13();
								temp[45] = dataList.get(j).getAlarmStatus_Ref14();
								temp[46] = dataList.get(j).getAlarmStatus_Ref15();
								temp[47] = dataList.get(j).getAlarmStatus_Ref16();
								
								// -----------------------------------------------------
								temp[48] = dataList.get(j).getAlarmStatus_Door1();
								temp[49] = dataList.get(j).getAlarmStatus_Door2();
								temp[50] = dataList.get(j).getAlarmStatus_Door3();
								temp[51] = dataList.get(j).getAlarmStatus_Door4();
								// ----------------------------------------------------
								temp[52] = dataList.get(j).getAlarmStatus_LostPower1();
								temp[53] = dataList.get(j).getAlarmStatus_LostPower2();
								temp[54] = dataList.get(j).getAlarmStatus_LostPower3();
								temp[55] = dataList.get(j).getAlarmStatus_LostPower4();
								// -----------------------------------------------------
								temp[56] = dataList.get(j).getAlarmStatus_Sound1();
								temp[57] = dataList.get(j).getAlarmStatus_Sound2();
								temp[58] = dataList.get(j).getAlarmStatus_Sound3();
								temp[59] = dataList.get(j).getAlarmStatus_Sound4();

								// --------------------------------如果当前探头的类型为Ai----------------------------------------
								if (((TbccImageControls) list.get(i)).getDataType() != 3) {
									if (temp[((TbccImageControls) list.get(i)).getPortNo() - 1] == -300) {
										controlDataArray[i] = taName.concat(",").concat("未启用");
									} else {
										tData = String.valueOf(temp[((TbccImageControls) list.get(i)).getPortNo() - 1]);
										controlDataArray[i] = taName.concat(",").concat(tData);
									}
								} else {	//----------如果当前探头的类型为Di、Do------------
									
									if (Pattern.matches(".*库门报警.*",((TbccImageControls) list.get(i)).getTitleMsg())||
										Pattern.matches(".*库门预警.*",((TbccImageControls) list.get(i)).getTitleMsg())) {
										
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 47] == 0){
											controlDataArray[i] = taName.concat(",").concat("预警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 47] == 1){
											controlDataArray[i] = taName.concat(",").concat("报警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 47] == 2){
											controlDataArray[i] = taName.concat(",").concat("正常");
										}
									}else if (Pattern.matches(".*断电报警.*",((TbccImageControls) list.get(i)).getTitleMsg()) || 
										Pattern.matches(".*断电预警.*",((TbccImageControls) list.get(i)).getTitleMsg()) ||	
										Pattern.matches(".*缺项报警.*",((TbccImageControls) list.get(i)).getTitleMsg()) ||
										Pattern.matches(".*缺项预警.*",((TbccImageControls) list.get(i)).getTitleMsg())) {
										
										

										if (temp[((TbccImageControls) list.get(i)).getRefid() + 51] == 0){
											controlDataArray[i] = taName.concat(",").concat("预警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 51] == 1){
											controlDataArray[i] = taName.concat(",").concat("报警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 51] == 2){
											controlDataArray[i] = taName.concat(",").concat("正常");
										}
									}else if (Pattern.matches(".*声光报警.*",((TbccImageControls) list.get(i)).getTitleMsg()) ||
										Pattern.matches(".*声光预警.*",((TbccImageControls) list.get(i)).getTitleMsg())) {
										
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 55] == 0){
											controlDataArray[i] = taName.concat(",").concat("预警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 55] == 1){
											controlDataArray[i] = taName.concat(",").concat("报警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 55] == 2){
											controlDataArray[i] = taName.concat(",").concat("正常");
										}
									}else{						
										
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 31] == 0){
											controlDataArray[i] = taName.concat(",").concat("预警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 31] == 1){
											controlDataArray[i] = taName.concat(",").concat("报警");
										}
										if (temp[((TbccImageControls) list.get(i)).getRefid() + 31] == 2){
											controlDataArray[i] = taName.concat(",").concat("正常");
										}
									}
										
									if (controlDataArray[i] == null){
											controlDataArray[i] = taName.concat(",").concat("无数据");
									}
								}
							}
						}
					}
					
				}else {		//******************************当前探头没有找到所处设备的实时数据记录
					if (((TbccImageControls) list.get(i)).getDataType() != 3) {
						controlDataArray[i] = taName.concat(",").concat("**.**");
					} else {
						controlDataArray[i] = taName.concat(",").concat("无数据");
					}
				} 
			}
		}

		StringBuffer buffer = new StringBuffer("");
		for (int j = 0; j < controlDataArray.length; j++) {
			buffer.append(controlDataArray[j]).append("|");
		}

		return buffer.toString().substring(0, buffer.toString().length() - 1);
	}

	public IImgDao getImgDaoImpl() {
		return imgDaoImpl;
	}

	public void setImgDaoImpl(IImgDao imgDaoImpl) {
		this.imgDaoImpl = imgDaoImpl;
	}

	/**
	 * 根据项目Id，获取图层列表
	 */
	public List<TbccProjectImages> getImagesByPid(String pid) {
		return imgDaoImpl.findImageByProjectId(pid);
	}
	
	/**
	 * 无用
	 * @param imageId
	 * @param projectId
	 * @return
	 */
	public String getEachAlarmControlAllDates(long imageId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
