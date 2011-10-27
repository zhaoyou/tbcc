package org.tbcc.entity;

/**
 * 项目的数字字典 TbccDictory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDictory implements java.io.Serializable {

	// Fields


	
	private Long id;  //标示Id
	private String projectId;	//工程Id
	private String key;			//数据字典key值
	private String value;		//数据字典value值
	

	/** default constructor */
	public TbccDictory() {
		super();
	}
	
	
	public TbccDictory(Long id, String projectId, String key, String value) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.key = key;
		this.value = value;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}