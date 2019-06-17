/**
 * com.base.dao.ISysRoleModuleDAO
 */
package com.base.mapper.basic;

import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysRoleModule;
import com.base.pojo.basic.SysRoleModuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */

public interface ISysRoleModuleMapper extends IBaseEntityDAO<SysRoleModule,SysRoleModuleVO> {
    List<Integer> findByRoleIdAndMouduleId(@Param("roleId")Integer roleId, @Param("moduleId")Integer moduleId);
    List<Integer> findByRoleIdAndMouduleId2(@Param("roleId")Integer roleId, @Param("moduleId")Integer moduleId);
    void deleteUsrRoleModule(@Param("roleId")Integer roleId,@Param("roleModuleId")Integer roleModuleId,@Param("moduleId")Integer moduleId);
}
