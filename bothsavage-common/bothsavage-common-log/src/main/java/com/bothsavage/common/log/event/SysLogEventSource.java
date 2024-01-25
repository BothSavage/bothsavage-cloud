package com.bothsavage.common.log.event;

import com.bothsavage.admin.api.entity.SysLog;
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
