package org.tbcc.entity;

/**
 * 日志实体类
 * @author zhaoyou
 *
 */
public class TbccLogo {
	
		/**
		 * Logo显示的文字
		 */
		private String logoDisplayName ;

		/**
		 * Logo 现实的图片名称
		 */
		private String logoImg ;
		
		/**
		 * Logo 是否显示
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
