package org.fdap.biz;

import java.util.List;

import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdaprealalarm;
import org.fdap.util.AlarmTip;

/**
 * 实时报警业务访问接口
 * @author zhaoyou
 *
 */
public interface RealAlarmBiz {
	
		/**
		 * 通过企业标识Id获取仓库工程集合
		 * @param oid		企业标识Id
		 * @return
		 */
		public List<Fdapproject>		getRefProjectByOid(Long oid);
		
		/**
		 *  通过企业标识Id获取车载工程集合
		 * @param oid		企业标识Id
		 * @return
		 */
		public List<Fdapproject> 	getCarProjectByOid(Long oid);
		
		/**
		 * 通过工程标识Id集合获取，该工程下的所有报警探头数据
		 * @param projectIds		 工程标识Id字符串集合
		 * @return
		 */
		public List<Fdaprealalarm> getAlarmByProjectIds(String projectIds);
		
		/**
		 * 根据当前机构的标识id，获取该机构下的报警信息，在地图上显示
		 * @param oid
		 * @return
		 */
		public  List<AlarmTip> getAlarmTip(String oid );
		
		/**
		 * 根据当前机构的标识Id，获取机构下的所有企业报警状态,在公司列表页面显示
		 * @param parentId		机构的Id
		 * @return
		 */
		public List<AlarmTip> getAlarmEntryTip(String parentId);
		
		/**
		 * 根据当前企业的标识oid，获取该企业的报警状态,在公司信息页面显示
		 * @param oid		机构的Id
		 * @return
		 */
		public String getAlarmOrgTip(String oid);

}
