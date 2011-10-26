package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.LinkConfigBiz;
import org.fdap.dao.LinkConfigDao;
import org.fdap.entity.Fdaplink;

public class LinkConfigBizImpl implements LinkConfigBiz {
	private LinkConfigDao linkConfigDao;
	
	public LinkConfigDao getLinkConfigDao() {
		return linkConfigDao;
	}

	public void setLinkConfigDao(LinkConfigDao linkConfigDao) {
		this.linkConfigDao = linkConfigDao;
	}

	@Override
	public boolean DelLink(String[] delId) {
		try{
			for(int i=0;i<delId.length;i++){
				linkConfigDao.DelLink(new Long(delId[i]));
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addLink(Fdaplink link) {
		return this.getLinkConfigDao().insertLink(link);
	}

	@Override
	public List<Fdaplink> getLinkByType(Long ltid) {
		return this.getLinkConfigDao().queryLinkByType(ltid);
	}

	@Override
	public Fdaplink getById(Long lid) {
		return this.getLinkConfigDao().queryById(lid);
	}

	@Override
	public boolean updateLink(Fdaplink link) {
		try{
			linkConfigDao.updateLink(link);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public boolean isBeyond(Long ltid) {
		Long count = linkConfigDao.querycouts(ltid);
		if(count.intValue()<4) return false;
		else return true;
	}

	@Override
	public List<Fdaplink> getAllLink() {
		return this.getLinkConfigDao().queryAllLink();
	}
	
	
}
