package org.fdap.dao;

import java.util.List;

import org.fdap.entity.Fdaprealalarm;


/**
 * 探头实时报警数据访问接口
 * @author zhaoyou
 *
 */
public interface RealAlarmDao {
		
		/**
		 * 获取实时报警，通过工程标识Id集合
		 * @param projectIds			工程标识Id集合
		 * @return
		 */
		public List<Fdaprealalarm>		queryAlarmByProjectIds(List<Long> projectIds);
		
		/**
		 * 获取已经发生报警的企业标识Id
		 * @return
		 */
		public List<Long> 		queryAlarmOid();

}
