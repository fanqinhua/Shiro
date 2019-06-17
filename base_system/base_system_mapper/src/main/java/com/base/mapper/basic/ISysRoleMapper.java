/**
 * com.base.dao.ISysRoleDAO
 */
package com.base.mapper.basic;
import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysRole;
import com.base.pojo.basic.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */

public interface ISysRoleMapper extends IBaseEntityDAO<SysRole,SysRoleVO> {
    List<SysRoleVO> getRoleManList(@Param("cname")String cname);
}
