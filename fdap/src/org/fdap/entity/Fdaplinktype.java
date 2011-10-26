package org.fdap.entity;

import java.io.Serializable;

public class Fdaplinktype implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long ltid;
	private String ltname;
	
	public Fdaplinktype(){super();}
	
	public Fdaplinktype(Long ltid,String ltname){
		super();
		this.ltid = ltid;
		this.ltname = ltname;
	}

	public Long getLtid() {
		return ltid;
	}

	public void setLtid(Long ltid) {
		this.ltid = ltid;
	}

	public String getLtname() {
		return ltname;
	}

	public void setLtname(String ltname) {
		this.ltname = ltname;
	}
	
}
