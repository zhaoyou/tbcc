package org.tbcc.entity;

/**
 * ��־ʵ����
 * @author zhaoyou
 *
 */
public class TbccLogo {
	
		/**
		 * Logo��ʾ������
		 */
		private String logoDisplayName ;

		/**
		 * Logo ��ʵ��ͼƬ����
		 */
		private String logoImg ;
		
		/**
		 * Logo �Ƿ���ʾ
		 */
		private Integer isShow ;
		
		
		public String getLogoDisplayName() {
			return logoDisplayName;
		}
		
		public void setLogoDisplayName(String logoDisplayName) {
			this.logoDisplayName = logoDisplayName;
		}
	
		
		
		public String getLogoImg() {
			return logoImg;
		}
		
		public void setLogoImg(String logoImg) {
			this.logoImg = logoImg;
		}

		public Integer getIsShow() {
			return isShow;
		}

		public void setIsShow(Integer isShow) {
			this.isShow = isShow;
		}
		
		
}
