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
 * �����Ϊ����dwr����������ص���ʵʱ���ݶ���Ƶġ�
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
		 * ���ݻ���ı�ʶId����ȡ���������ʵʱ����
		 * @param id	����ı�ʶId
		 * @return
		 */
		public TbccMultiCompressorRealData getMultiData(Integer id){
			return compressorSetBiz.getMultiRealById(id) ;
		}
		
		/**
		 * ���ݻ���ı�ʶId����ȡ���������ʵʱ����
		 * @param id	����ı�ʶId
		 * @return
		 */
		public TbccSingleCompressorRealData	getSingleData(Integer id){
			return compressorSetBiz.getSingleRealById(id);
		}
		
		
		
		
		/**
		 * ���ݻ���ı�ʶId����ȡ�û����ѹ������ʵʱ����
		 * @param id	����ı�ʶId
		 * @return
		
		public List<TbccCompressorRealData> getCompressorData(Integer id){
			return compressorBiz.getBycsId(id);
		}
		 */
		
		/**
		 * ���ݻ�ͷ�ı�ʶId���ϣ���ȡ��ͷ��ʵʱ���ݼ���
		 * @param str	eg: 12,13,14
		 * @return
		 */
		public List<TbccCompressorRealData> getCompressorDataByIds(String str){
			return compressorBiz.getByCids(str) ;
		}
		
		
		
		
		/**
		 * ���ݻ���ı�ʶId����ȡ�û����������������ʵʱ����
		 * @param id	����ı�ʶId
		 * @return
		
		public List<TbccCondenserRealData> getCondenserData(Integer id){
			return condenserBiz.getBycsId(id) ;
		}
		 */
		
		
		/**
		 * �����������ı�ʶId���ϣ���ȡ��������ʵʱ���ݼ���
		 * @param str		eg: 12,13,14
		 * @return
		 */
		public List<TbccCondenserRealData> getCondenserDataByIds(String str){
			return condenserBiz.getByCids(str) ;
		}
		
		
		
		/**
		 * ���ݻ���ı�ʶId����ȡ�û��������������ʵʱ����
		 * @param id	����ı�ʶid
		 * @return
		
		public List<TbccAirCoolerRealData> getAirCoolerData(Integer id){
			return airCoolerBiz.getBycsId(id) ;
		}
		 */
		
		/**
		 * ���������ı�ʶId���ϡ���ȡ������ʵʱ���ݼ���
		 * @param Str eg: 12,13,14
		 * @return 
		 */
		public List<TbccAirCoolerRealData> getAirCoolerDataByIds(String str) {
			return airCoolerBiz.getByAids(str) ;
		}
		
		
		
		
		
		/**
		 * ������⹤�̵ı�ʶ���ϣ���ȡ����ϵͳʵʱ����
		 * @param str	��⹤�̵ı�ʶ����
		 * @return
		 */
		public TbccCcapSystemRealData getCoolerSysRealData(String[] str){
			return coolerSystemBiz.getByProjectId(str);
		}
		
		
		
		
}
