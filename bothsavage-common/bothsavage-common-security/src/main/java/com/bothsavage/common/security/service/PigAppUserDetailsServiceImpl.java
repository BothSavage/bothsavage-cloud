
package com.bothsavage.common.security.service;

import com.bothsavage.admin.api.dto.UserDTO;
import com.bothsavage.admin.api.dto.UserInfo;
import com.bothsavage.admin.api.feign.RemoteUserService;
import com.bothsavage.common.core.constant.CacheConstants;
import com.bothsavage.common.core.constant.SecurityConstants;
import com.bothsavage.common.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 *
 hccake
 */
@Slf4j
@RequiredArgsConstructor
public class PigAppUserDetailsServiceImpl implements PigUserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 手机号登录
	 * @param phone 手机号
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String phone) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(phone) != null) {
			return (PigUser) cache.get(phone).get();
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setPhone(phone);
		R<UserInfo> result = remoteUserService.info(userDTO, SecurityConstants.FROM_IN);

		UserDetails userDetails = getUserDetails(result);
		if (cache != null) {
			cache.put(phone, userDetails);
		}
		return userDetails;
	}

	/**
	 * check-token 使用
	 * @param bothsavageUser user
	 * @return
	 */
	@Override
	public UserDetails loadUserByUser(PigUser bothsavageUser) {
		return this.loadUserByUsername(bothsavageUser.getPhone());
	}

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	@Override
	public boolean support(String clientId, String grantType) {
		return SecurityConstants.MOBILE.equals(grantType);
	}

}
