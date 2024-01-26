

package com.cadillac.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cadillac.admin.api.entity.SysPost;
import com.cadillac.admin.api.vo.PostExcelVO;
import com.cadillac.common.core.util.R;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * 岗位信息表
 *

 * @date 2022-03-26 12:50:43
 */
public interface SysPostService extends IService<SysPost> {

	/**
	 * 导出excel 表格
	 * @return
	 */
	List<PostExcelVO> listPost();

	/**
	 * 导入岗位
	 * @param excelVOList 岗位列表
	 * @param bindingResult 错误信息列表
	 * @return ok fail
	 */
	R importPost(List<PostExcelVO> excelVOList, BindingResult bindingResult);

}
