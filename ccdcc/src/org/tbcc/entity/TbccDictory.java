package org.tbcc.entity;

/**
 * ��Ŀ�������ֵ� TbccDictory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbccDictory implements java.io.Serializable {

	// Fields


	
	private Long id;  //��ʾId
	private String projectId;	//����Id
	private String key;			//�����ֵ�keyֵ
	private String value;		//�����ֵ�valueֵ
	

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