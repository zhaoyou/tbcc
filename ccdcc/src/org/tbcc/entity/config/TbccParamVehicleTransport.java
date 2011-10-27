package org.tbcc.entity.config;

/**
 * 车载运输信息配置表
 * @author zhaoyou
 *
 */
public class TbccParamVehicleTransport implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private	Long parentId ;
	private String beginAddress ;
	private String endAddress ;
	private String shipper ;
	private String carrier ;
	private String receiver ;
	
	
	public TbccParamVehicleTransport(){
	}
	
	
	
	public TbccParamVehicleTransport(Long id, Long parentId,
			String beginAddress, String endAddress, String shipper,
			String carrier, String receiver) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.beginAddress = beginAddress;
		this.endAddress = endAddress;
		this.shipper = shipper;
		this.carrier = carrier;
		this.receiver = receiver;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getBeginAddress() {
		return beginAddress;
	}
	public void setBeginAddress(String beginAddress) {
		this.beginAddress = beginAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	

}
