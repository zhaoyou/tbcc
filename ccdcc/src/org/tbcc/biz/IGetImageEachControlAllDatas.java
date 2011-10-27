package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccProjectImages;

public interface IGetImageEachControlAllDatas {
	//public String getEachControlAllDates(long imageId);
	
	public String getEachControlAllDates(long imageId,String projectId);

	
	public List<TbccProjectImages> getImagesByPid(String pid);
}
