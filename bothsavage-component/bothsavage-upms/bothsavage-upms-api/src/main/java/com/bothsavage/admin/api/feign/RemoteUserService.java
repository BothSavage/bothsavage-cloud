
package com.bothsavage.admin.api.feign;

import com.bothsavage.admin.api.dto.UserDTO;
import com.bothsavage.admin.api.dto.UserInfo;
import com.bothsavage.common.core.constant.SecurityConstants;
import com.bothsavage.common.core.constant.ServiceNameConstants;
import com.bothsavage.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**

 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param user 用户查询对象
	 * @param from 调用标志
	 * @return R
	 */
	@GetMapping("/user/info/query")
	R<UserInfo> info(@SpringQueryMap UserDTO user, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 锁定用户
	 * @param username 用户名
	 * @param from 调用标识
	 * @return
	 */
	@PutMapping("/user/lock/{username}")
	R<Boolean> lockUser(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

}
