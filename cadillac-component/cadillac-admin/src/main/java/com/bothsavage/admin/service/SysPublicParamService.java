

package com.cadillac.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cadillac.admin.api.entity.SysPublicParam;
import com.cadillac.common.core.util.R;

/**
 * 公共参数配置
 *

 * @date 2019-04-29
 */
public interface SysPublicParamService extends IService<SysPublicParam> {

	/**
	 * 通过key查询公共参数指定值
	 * @param publicKey
	 * @return
	 */
	String getSysPublicParamKeyToValue(String publicKey);

	/**
	 * 更新参数
	 * @param sysPublicParam
	 * @return
	 */
	R updateParam(SysPublicParam sysPublicParam);

	/**
	 * 删除参数
	 * @param publicIds 参数列表
	 * @return
	 */
	R removeParamByIds(Long[] publicIds);

	/**
	 * 同步缓存
	 * @return R
	 */
	R syncParamCache();

}
