/**
 * cn.rmt.admin.dao.ISysParamDAO
 */
package com.base.mapper.basic;

import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysParam;
import com.base.pojo.basic.SysParamVO;
import org.apache.ibatis.annotations.Param;
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
public interface ISysParamMapper extends IBaseEntityDAO<SysParam,SysParamVO> {
    List<SysParamVO> getSysParamTable(@Param("paramCategoryId") Integer paramCategoryId);
}
