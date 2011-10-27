package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccRefInfo;

/**
 * ���ǲ�������ҵ��ӿ�
 * @author Administrator
 *
 */
public interface RefInfoBiz {
	/**
	 * �������
	 */
	public static final int REFTYPE = 1 ;
	/**
	 * ����������
	 */
	public static final int SHADYTYPE = 2 ;
	
	/**
	 * ¥������Ϊ¥��
	 */
	public static final int FLOORUP = 1 ;
	
	/**
	 * ¥������Ϊ¥��
	 */
	public static final int FLOORDOWN = 2 ;
	
	/**
	 * ������⹤�̵ı�ʶId����ȡ���е������Ϣ
	 * @param proId		��⹤�̱�ʶ
	 * @return
	 */
	public List<TbccRefInfo> getRefListByPrj(String proId);
	
	/**
	 * ������⹤�̱�ʶId�������豸Id������� ��ȡĳ��������Ϣ
	 * @param proId		���̱�ʶId
	 * @param netId		�����豸Id
	 * @param refId		�����
	 * @return
	 */
	public TbccRefInfo getRef(String proId,String netId,String refId);
	
	/**
	 * �������ı�ʶId����ȡ������Ϣ
	 * @param id	���ı�ʶId
	 * @return
	 */
	public TbccRefInfo getById(Long id);
}
