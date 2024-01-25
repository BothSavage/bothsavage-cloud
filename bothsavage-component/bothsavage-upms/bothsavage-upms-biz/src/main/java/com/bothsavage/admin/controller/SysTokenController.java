

package com.bothsavage4cloud.bothsavage.admin.controller;

import com.bothsavage4cloud.bothsavage.admin.api.feign.RemoteTokenService;
import com.bothsavage4cloud.bothsavage.common.core.constant.SecurityConstants;
import com.bothsavage4cloud.bothsavage.common.core.util.R;
import com.bothsavage4cloud.bothsavage.common.log.annotation.SysLog;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**

 * @date 2018/9/4 getTokenPage 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
@Tag(description = "token", name = "令牌管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysTokenController {

	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 * @param params 参数集
	 * @return token集合
	 */
	@RequestMapping("/page")
	public R getTokenPage(@RequestBody Map<String, Object> params) {
		return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 * @param tokens tokens
	 * @return success/false
	 */
	@SysLog("删除用户token")
	@DeleteMapping("/delete")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public R removeById(@RequestBody String[] tokens) {
		for (String token : tokens) {
			remoteTokenService.removeTokenById(token, SecurityConstants.FROM_IN);
		}
		return R.ok();
	}

}
