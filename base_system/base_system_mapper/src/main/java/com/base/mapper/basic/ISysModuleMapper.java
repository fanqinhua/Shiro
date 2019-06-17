/**
 * com.base.dao.ISysModuleDAO
 */
package com.base.mapper.basic;


import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysModule;
import com.base.pojo.basic.SysModuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public interface ISysModuleMapper extends IBaseEntityDAO<SysModule,SysModuleVO> {
    List<SysModuleVO> findByPid(@Param("pid")Integer pid,@Param("roles")Integer... roles);
    List<SysModuleVO> findByOrder();
}
