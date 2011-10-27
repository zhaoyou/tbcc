package org.tbcc.entity;

import java.util.Date;

import org.tbcc.util.MyUtil;

/**
 * 这是一个通用的启停实体类
 */
public class TbccBaseHisStartUp {
		
	public TbccBaseHisStartUp(){
			
		}
	
	private Long id ;
	private Date beginTime ;
	private Date endTime ;
	private int pageIndex ;
	private int pageCount ;
	private int recordInterval ;
	private int lastPageIndex ;
	private int lastPageRecNum ;
	private String beginAddress ;
	private String endAddress ;
	private String shipper ;
	private String carrier ;
	private String receiver  ;
	private Date lastRecordTime ;
	private int updateStatus ;
	
	

	
	private Integer tuplimit ;
	
	private Integer tdwlimit ;
	
	
	
	
	
	
	public Integer getTuplimit() {
		return tuplimit;
	}
	public void setTuplimit(Integer tuplimit) {
		this.tuplimit = tuplimit;
	}
	public Integer getTdwlimit() {
		return tdwlimit;
	}
	public void setTdwlimit(Integer tdwlimit) {
		this.tdwlimit = tdwlimit;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRecordInterval() {
		return recordInterval;
	}
	public void setRecordInterval(int recordInterval) {
		this.recordInterval = recordInterval;
	}
	public int getLastPageIndex() {
		return lastPageIndex;
	}
	public void setLastPageIndex(int lastPageIndex) {
		this.lastPageIndex = lastPageIndex;
	}
	public int getLastPageRecNum() {
		return lastPageRecNum;
	}
	public void setLastPageRecNum(int lastPageRecNum) {
		this.lastPageRecNum = lastPageRecNum;
	}
	public String getBeginAddress() {
		return "****";
	}
	public void setBeginAddress(String beginAddress) {
		this.beginAddress = beginAddress;
	}
	public String getEndAddress() {
		return "****";
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getShipper() {
		return "****";
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getCarrier() {
		return doEncode(this.carrier);
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getReceiver() {
		return "****";
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	public int getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(int updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	/**
	 * 自定义两个方法返回yyyy-MM-dd HH:mm:ss格式的值
	 */
	
	
	private String btimeStr ;
	private String etimeStr ;
	private String etimeDisplay ;
	
	public void setEtimeDisplay(String etimeDisplay) {
		this.etimeDisplay = etimeDisplay;
	}
	public void setBtimeStr(String btimeStr) {
		this.btimeStr = btimeStr;
	}
	public void setEtimeStr(String etimeStr) {
		this.etimeStr = etimeStr;
	}
	/**
	 * 获取开始时间的正确格式
	 */
	public String getBtimeStr(){
		return MyUtil.getToString(this.getBeginTime()) ;
	}
	
	/**
	 * 获取结束时间的正确格式
	 * @return
	 */
	public String getEtimeStr(){
		return MyUtil.getToString(this.getEndTime()) ;
	}
	
	/**
	 * 获取正确的结束时间，用于查询，如果为-------,则显示为当前时间
	 * @return
	 */
	public String getEtimeDisplay(){
		String display = MyUtil.getToString(this.getEndTime()) ;
		if(display.equals("--------"))
			return MyUtil.getToString(new java.util.Date());
		return display ;
	}
	
	
	private String doEncode(String source){
		if(source==null)
			return "****" ;
		byte[] mybyte  = source.getBytes() ;
		int len = mybyte.length ;
		if(len>4)
		{
			if(mybyte[len-1]==0 && mybyte[len-2]==0 && mybyte[len-3]==0 && mybyte[len-4]==0)
				return  "****" ;
		}	
		return source ;		
	}
}
