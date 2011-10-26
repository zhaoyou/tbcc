package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.CarHisBiz;
import org.fdap.dao.CarHisDao;
import org.fdap.dao.RefDao;
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdapref;
import org.fdap.util.GetMaxMinAvg;

/**
 * 车载历史数据业务实现接口
 * @author zhoukuanwei
 *
 */
public class CarHisBizImpl implements CarHisBiz {

	private CarHisDao carhisdao=null;
	
	private RefDao refdao;
	
	private GetMaxMinAvg getmaxminavg;
	
	public GetMaxMinAvg getGetmaxminavg() {
		return getmaxminavg;
	}

	public void setGetmaxminavg(GetMaxMinAvg getmaxminavg) {
		this.getmaxminavg = getmaxminavg;
	}

	public CarHisDao getCarhisdao() {
		return carhisdao;
	}

	public void setCarhisdao(CarHisDao carhisdao) {
		this.carhisdao = carhisdao;
	}
	
	public RefDao getRefdao() {
		return refdao;
	}

	public void setRefdao(RefDao refdao) {
		this.refdao = refdao;
	}

	public List<Object> getCarHisMMA(String tableName, Integer parentId) {
		List<FdapCarHisData> list=carhisdao.queryCarHis(tableName, parentId);
//		System.out.println("记录数"+list.size());
		if(list!=null&&list.size()!=0)
			return getmaxminavg.getCarMaxMinAvg(list);
		return null;
	}

	public List<FdapCarHisData> getCarHisbyStartup(String tableName,
			Integer parentId,Integer startRow,Integer pagesize) {
			return carhisdao.queryCarHisbyStartupPage(tableName, parentId, startRow,pagesize);
	}

	public Integer getCarHisCount(String tableName, Integer parentId) {
		return carhisdao.queryCarHisCount(tableName, parentId);
	}

	public List<Fdapref> getCarRefByOid(String oid) {
		return refdao.queryRefByOidAndType(oid, CAR_TYPE);
	}

	public Fdapref getCarRefById(String refId) {
		if(refId!=null){
			return refdao.queryByRefId(Long.parseLong(refId));
		}
		else{
			return null;
		}
	}

	@Override
	public List<FdapCarHisData> getCarHis(String oid, String sid) {
		if(oid==null||oid.equals("")||sid==null||sid.equals("")){
			return null;
		}
		String tableName = "fdapcarhisdata_"+oid;
		
		List<FdapCarHisData> carhisList = carhisdao.queryCarHis(tableName, Integer.parseInt(sid));
		for(FdapCarHisData carhis : carhisList){
			if(carhis.getT1()==-300){
				carhis.setT1(null);
			}
			if(carhis.getT2()==-300){
				carhis.setT2(null);
			}
			if(carhis.getT3()==-300){
				carhis.setT3(null);
			}
		}
		
		return carhisList;
	}
	
}
