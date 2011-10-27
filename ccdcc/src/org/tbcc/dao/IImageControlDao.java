package org.tbcc.dao;

import org.tbcc.entity.TbccImageControls;

public interface IImageControlDao {
	public String findControlRealDataByImageControl(TbccImageControls control);
}
