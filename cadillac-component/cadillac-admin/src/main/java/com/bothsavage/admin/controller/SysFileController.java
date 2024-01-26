

package com.cadillac.admin.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cadillac4cloud.cadillac.admin.api.entity.SysFile;
import com.cadillac4cloud.cadillac.admin.service.SysFileService;
import com.cadillac4cloud.cadillac.common.core.util.R;
import com.cadillac4cloud.cadillac.common.log.annotation.SysLog;
import com.cadillac4cloud.cadillac.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 *

 * @date 2019-06-18 17:18:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-file")
@Tag(description = "sys-file", name = "文件管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysFileController {

	private final SysFileService sysFileService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysFile 文件管理
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	public R getSysFilePage(@ParameterObject Page page, @ParameterObject SysFile sysFile) {
		LambdaQueryWrapper<SysFile> wrapper = Wrappers.<SysFile>lambdaQuery()
			.like(StrUtil.isNotBlank(sysFile.getOriginal()), SysFile::getOriginal, sysFile.getOriginal());
		return R.ok(sysFileService.page(page, wrapper));
	}

	/**
	 * 通过id删除文件管理
	 * @param ids id 列表
	 * @return R
	 */
	@Operation(summary = "通过id删除文件管理", description = "通过id删除文件管理")
	@SysLog("删除文件管理")
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys_file_del')")
	public R removeById(@RequestBody Long[] ids) {
		for (Long id : ids) {
			sysFileService.deleteFile(id);
		}
		return R.ok();
	}

	/**
	 * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 * @param file 资源
	 * @return R(/ admin / bucketName / filename)
	 */
	@PostMapping(value = "/upload")
	public R upload(@RequestPart("file") MultipartFile file) {
		return sysFileService.uploadFile(file);
	}

	/**
	 * 获取文件
	 * @param bucket 桶名称
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */
	@Inner(false)
	@GetMapping("/{bucket}/{fileName}")
	public void file(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
		sysFileService.getFile(bucket, fileName, response);
	}

	/**
	 * 获取本地（resources）文件
	 * @param fileName 文件名称
	 * @param response 本地文件
	 */
	@SneakyThrows
	@GetMapping("/local/file/{fileName}")
	public void localFile(@PathVariable String fileName, HttpServletResponse response) {
		ClassPathResource resource = new ClassPathResource("file/" + fileName);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IoUtil.copy(resource.getInputStream(), response.getOutputStream());
	}

}