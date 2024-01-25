

package com.bothsavage.admin.service;

import com.bothsavage.common.core.util.R;

/**

 * @date 2018/11/14
 */
public interface SysMobileService {

	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);

}
