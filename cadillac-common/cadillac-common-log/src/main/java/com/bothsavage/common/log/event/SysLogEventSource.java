package com.cadillac.common.log.event;

import com.cadillac.admin.api.entity.SysLog;
import lombok.Data;

/**
 * spring event log
 *

 * @date 2023/8/11
 */
@Data
public class SysLogEventSource extends SysLog {

	/**
	 * 参数重写成object
	 */
	private Object body;

}
