
package com.bothsavage4cloud.bothsavage.admin.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bothsavage4cloud.bothsavage.admin.api.dto.SysLogDTO;
import com.bothsavage4cloud.bothsavage.admin.api.entity.SysLog;
import com.bothsavage4cloud.bothsavage.admin.service.SysLogService;
import com.bothsavage4cloud.bothsavage.common.core.util.R;
import com.bothsavage4cloud.bothsavage.common.security.annotation.Inner;
import com.bothsavage4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *

 * @since 2017-11-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Tag(description = "log", name = "日志管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysLogController {

	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R getLogPage(@ParameterObject Page page, @ParameterObject SysLogDTO sysLog) {
		return R.ok(sysLogService.getLogByPage(page, sysLog));
	}

	/**
	 * 批量删除日志
	 * @param ids ID
	 * @return success/false
	 */
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public R removeByIds(@RequestBody Long[] ids) {
		return R.ok(sysLogService.removeBatchByIds(CollUtil.toList(ids)));
	}

	/**
	 * 插入日志
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping("/save")
	public R save(@Valid @RequestBody SysLog sysLog) {
		return R.ok(sysLogService.saveLog(sysLog));
	}

	/**
	 * 导出excel 表格
	 * @param sysLog 查询条件
	 * @return
	 */
	@ResponseExcel
	@GetMapping("/export")
	@PreAuthorize("@pms.hasPermission('sys_log_export')")
	public List<SysLog> export(SysLog sysLog) {
		return sysLogService.list(Wrappers.query(sysLog));
	}

}
