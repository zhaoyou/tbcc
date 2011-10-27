package org.tbcc.dwr;

import java.util.List;

import org.tbcc.biz.AirCoolerBiz;
import org.tbcc.biz.CompressorBiz;
import org.tbcc.biz.CompressorSetBiz;
import org.tbcc.biz.CondenserBiz;
import org.tbcc.biz.CoolerSystemBiz;
import org.tbcc.entity.cool.TbccAirCoolerRealData;
import org.tbcc.entity.cool.TbccCompressorRealData;
import org.tbcc.entity.cool.TbccCondenserRealData;
import org.tbcc.entity.cool.TbccCcapSystemRealData;
import org.tbcc.entity.cool.TbccMultiCompressorRealData;
import org.tbcc.entity.cool.TbccSingleCompressorRealData;
import org.tbcc.util.MySpringFactory;


/**
 * 这个是为了用dwr调用制冷相关的是实时数据而设计的。
 * @author Administrator
 *
 */
public class RealCool {
		private CompressorSetBiz compressorSetBiz = null ;
		private CompressorBiz compressorBiz = null ;
		private CondenserBiz condenserBiz = null ;
		private AirCoolerBiz airCoolerBiz = null ;
		private CoolerSystemBiz coolerSystemBiz = null ;
		
		public RealCool(){
			compressorSetBiz = (CompressorSetBiz)MySpringFactory.getInstance().getBean("compressSetBiz") ;
			compressorBiz = 	(CompressorBiz)MySpringFactory.getInstance().getBean("compressBiz") ;
			condenserBiz = 		(CondenserBiz)MySpringFactory.getInstance().getBean("condenserBiz") ;
			airCoolerBiz = 		(AirCoolerBiz)MySpringFactory.getInstance().getBean("aircoolerBiz") ;
			coolerSystemBiz = 	(CoolerSystemBiz)MySpringFactory.getInstance().getBean("CoolerSystemBiz");
		}
		
		/**
		 * 根据机组的标识Id，获取并联机组的实时数据
		 * @param id	机组的标识Id
		 * @return
		 */
		public TbccMultiCompressorRealData getMultiData(Integer id){
			return compressorSetBiz.getMultiRealById(id) ;
		}
		
		/**
		 * 根据机组的标识Id，获取冷凝机组的实时数据
		 * @param id	机组的标识Id
		 * @return
		 */
		public TbccSingleCompressorRealData	getSingleData(Integer id){
			return compressorSetBiz.getSingleRealById(id);
		}
		
		
		
		
		/**
		 * 根据机组的标识Id，获取该机组的压缩机的实时数据
		 * @param id	机组的标识Id
		 * @return
		
		public List<TbccCompressorRealData> getCompressorData(Integer id){
			return compressorBiz.getBycsId(id);
		}
		 */
		
		/**
		 * 根据机头的标识Id集合，获取机头的实时数据集合
		 * @param str	eg: 12,13,14
		 * @return
		 */
		public List<TbccCompressorRealData> getCompressorDataByIds(String str){
			return compressorBiz.getByCids(str) ;
		}
		
		
		
		
		/**
		 * 根据机组的标识Id，获取该机组的所有冷凝器的实时数据
		 * @param id	机组的标识Id
		 * @return
		
		public List<TbccCondenserRealData> getCondenserData(Integer id){
			return condenserBiz.getBycsId(id) ;
		}
		 */
		
		
		/**
		 * 根据冷凝器的标识Id集合，获取冷凝器的实时数据集合
		 * @param str		eg: 12,13,14
		 * @return
		 */
		public List<TbccCondenserRealData> getCondenserDataByIds(String str){
			return condenserBiz.getByCids(str) ;
		}
		
		
		
		/**
		 * 根据机组的标识Id，获取该机组的所有冷风机的实时数据
		 * @param id	机组的标识id
		 * @return
		
		public List<TbccAirCoolerRealData> getAirCoolerData(Integer id){
			return airCoolerBiz.getBycsId(id) ;
		}
		 */
		
		/**
		 * 根据冷风机的标识Id集合、获取冷风机的实时数据集合
		 * @param Str eg: 12,13,14
		 * @return 
		 */
		public List<TbccAirCoolerRealData> getAirCoolerDataByIds(String str) {
			return airCoolerBiz.getByAids(str) ;
		}
		
		
		
		
		
		/**
		 * 根据冷库工程的标识集合，获取制冷系统实时数据
		 * @param str	冷库工程的标识集合
		 * @return
		 */
		public TbccCcapSystemRealData getCoolerSysRealData(String[] str){
			return coolerSystemBiz.getByProjectId(str);
		}
		
		
		
		
}
