/**
 * cn.rmt.admin.dao.ISysParamCategoryDAO
 */
package com.base.mapper.basic;

import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysParamCategory;
import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.basic.SysParamCategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Repository
public interface ISysParamCategoryMapper extends IBaseEntityDAO<SysParamCategory,SysParamCategoryVO> {
    List<SysModuleVO> getParamCategoryList();
}
