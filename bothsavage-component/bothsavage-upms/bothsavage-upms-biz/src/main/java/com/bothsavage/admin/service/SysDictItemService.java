
package com.bothsavage.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bothsavage.admin.api.entity.SysDictItem;
import com.bothsavage.common.core.util.R;

/**
 * 字典项
 *

 * @date 2019/03/19
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 * @return
	 */
	R removeDictItem(Long id);

	/**
	 * 更新字典项
	 * @param item 字典项
	 * @return
	 */
	R updateDictItem(SysDictItem item);

}
