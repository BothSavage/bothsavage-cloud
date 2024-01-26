
package com.cadillac.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cadillac.admin.api.dto.SysLogDTO;
import com.cadillac.admin.api.entity.SysLog;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *

 * @since 2017-11-20
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 分页查询日志
	 * @param page
	 * @param sysLog
	 * @return
	 */
	Page getLogByPage(Page page, SysLogDTO sysLog);

	/**
	 * 插入日志
	 * @param sysLog 日志对象
	 * @return true/false
	 */
	Boolean saveLog(SysLog sysLog);

}
