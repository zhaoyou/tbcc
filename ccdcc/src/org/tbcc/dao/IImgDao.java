package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccImageControls;
import org.tbcc.entity.TbccProjectImages;

public interface IImgDao {
	public List<TbccProjectImages> findImageByProjectId(String projectId);
	public List<TbccBaseRealRef> getImageAllControlRealData(long imageId,String projectId);
	//public String findControlNameByImageControl(TbccImageControls control);
	public List<TbccImageControls> findImageControlsByImgID(long imageId,String projectId);
	public String getImagePathByImgID(long imageId);
	
	public List<TbccImageControls> findImageAlarmControlsByImgID(long imageId);
}
