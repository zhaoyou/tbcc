package org.fdap.entity;

/**
 * Fdaplink entity.
 * 
 * @author zhoukuanwei
 */

public class Fdaplink implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long lid;
	private Long ltid;
	private String name;
	private String url;
	
	public Fdaplink(){}
	
	public Fdaplink(Long lid,Long ltid,String name,String url){
		super();
		this.lid = lid;
		this.ltid = ltid;
		this.name = name;
		this.url = url;
	}
	
	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}
	
	public Long getLtid() {
		return ltid;
	}

	public void setLtid(Long ltid) {
		this.ltid = ltid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
