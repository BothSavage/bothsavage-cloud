
package com.bothsavage.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bothsavage.admin.api.entity.SysOauthClientDetails;
import com.bothsavage.common.core.util.R;

/**
 * <p>
 * 服务类
 * </p>
 *

 * @since 2018-05-15
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {

	/**
	 * 根据客户端信息
	 * @param clientDetails
	 * @return
	 */
	Boolean updateClientById(SysOauthClientDetails clientDetails);

	/**
	 * 添加客户端
	 * @param clientDetails
	 * @return
	 */
	Boolean saveClient(SysOauthClientDetails clientDetails);

	/**
	 * 分页查询客户端信息
	 * @param page
	 * @param query
	 * @return
	 */
	Page queryPage(Page page, SysOauthClientDetails query);

	/**
	 * 同步缓存 （清空缓存）
	 * @return R
	 */
	R syncClientCache();

}
