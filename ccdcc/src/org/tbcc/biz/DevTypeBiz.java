package org.tbcc.biz;

import org.tbcc.entity.TbccDevType;

/**
 * ����������豸��ҵ��ӿ�
 * @author Administrator
 *
 */
public interface DevTypeBiz {
	/**
	 * ����豸 1
	 */
	public static final int RI300 = 1 ;
	/**
	 * С���豸 2
	 */
	public static final int RI300E = 2 ;
	
	/**
	 * RI300S���ذ� 0
	 */
	public static final int RI300S = 0 ;
	
	/**
	 * �豸��չӦ�� 3
	 */
	public static final int EXAPP = 3 ;
	
	/**
	 * ͨ����ʶId����ȡ�豸
	 * @param id		��ʶId
	 * @return
	 */
	public TbccDevType getById(Long id) ;
}
