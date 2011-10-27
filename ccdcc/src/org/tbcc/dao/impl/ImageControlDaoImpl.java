package org.tbcc.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.IImageControlDao;
import org.tbcc.dao.IgetNetIdRealDataDao;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;

public class ImageControlDaoImpl extends HibernateDaoSupport  implements IImageControlDao {
	IgetNetIdRealDataDao getNetIdRealDataDaoImpl;
	public double temp[]= new double[12];

	public String findControlRealDataByImageControl(TbccImageControls control) {
		TbccBaseRealRef realData = getNetIdRealDataDaoImpl.getRealData(control);
		
		if(realData==null) return "**.**";
		if (realData.getConnectStatus() == 1)
			return "**.**";
		temp[0] = realData.getAi1();
		temp[1] = realData.getAi2();
		temp[2] = realData.getAi3();
		temp[3] = realData.getAi4();
		temp[4] = realData.getAi5();
		temp[5] = realData.getAi6();
		temp[6] = realData.getAi7();
		temp[7] = realData.getAi8();
		temp[8] = realData.getAi9();
		temp[9] = realData.getAi10();
		temp[10] = realData.getAi11();
		temp[11] = realData.getAi12();
		if (temp[control.getPortNo()-1] == -300)
			return "Œ¥∆Ù”√";
		else
			return String.valueOf(temp[control.getPortNo()-1]);
	}

	public IgetNetIdRealDataDao getGetNetIdRealDataDaoImpl() {
		return getNetIdRealDataDaoImpl;
	}

	public void setGetNetIdRealDataDaoImpl(
			IgetNetIdRealDataDao getNetIdRealDataDaoImpl) {
		this.getNetIdRealDataDaoImpl = getNetIdRealDataDaoImpl;
	}
}
