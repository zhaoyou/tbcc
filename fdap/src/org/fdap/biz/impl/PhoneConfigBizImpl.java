package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.PhoneConfigBiz;
import org.fdap.dao.PhoneConfigDao;
import org.fdap.entity.Fdapphone;

public class PhoneConfigBizImpl implements PhoneConfigBiz {
	private PhoneConfigDao phoneconfigdao;
	
	public PhoneConfigDao getPhoneconfigdao() {
		return phoneconfigdao;
	}

	public void setPhoneconfigdao(PhoneConfigDao phoneconfigdao) {
		this.phoneconfigdao = phoneconfigdao;
	}
	//获取电话信息 
	public Fdapphone getPhone() {
		List<Fdapphone> list = phoneconfigdao.queryPhone();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean savePhone(Fdapphone phoneinfo) {
		if(phoneinfo==null){
			return false;
		}
		return phoneconfigdao.updatePhone(phoneinfo);
	}
}
