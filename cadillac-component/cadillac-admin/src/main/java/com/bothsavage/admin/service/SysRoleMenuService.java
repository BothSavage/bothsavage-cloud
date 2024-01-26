
package com.cadillac.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cadillac4cloud.cadillac.admin.api.entity.SysRoleMenu;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *

 * @since 2017-10-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	/**
	 * 更新角色菜单
	 * @param roleId 角色ID
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	Boolean saveRoleMenus(Long roleId, String menuIds);

}
