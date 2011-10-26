package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.LinkTypeBiz;
import org.fdap.dao.LinkTypeDao;
import org.fdap.entity.Fdaplinktype;

public class LinkTypeBizImpl implements LinkTypeBiz {
	private LinkTypeDao linktypedao;
	
	public LinkTypeDao getLinktypedao() {
		return linktypedao;
	}

	public void setLinktypedao(LinkTypeDao linktypedao) {
		this.linktypedao = linktypedao;
	}

	@Override
	public boolean DelLinktype(String[] delId) {
		try{
			for(int i=0;i<delId.length;i++){
				linktypedao.DelLinktype(new Long(delId[i]));
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addLinktype(Fdaplinktype linktype) {
		return this.getLinktypedao().insertLinktype(linktype);
	}

	@Override
	public List<Fdaplinktype> getAllLinktype() {
		List<Fdaplinktype> list = this.getLinktypedao().queryAllLinktype();
		if(list==null||list.size()==0){
			return null;
		}
		return list;
	}

	@Override
	public Fdaplinktype getById(Long ltid) {
		return this.getLinktypedao().queryById(ltid);
	}

	@Override
	public boolean updateLinktype(Fdaplinktype linktype) {
		try{
			linktypedao.updateLinktype(linktype);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}
