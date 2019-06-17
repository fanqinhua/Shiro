/**
 * com.base.dao.ISysUserDAO
 */
package com.base.mapper.basic;


import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysUser;
import com.base.pojo.basic.SysUserVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */

public interface ISysUserMapper extends IBaseEntityDAO<SysUser,SysUserVO> {
    List<SysUserVO> getUserManList(@Param("cname") String cname);
    List<Integer> findRoleNamesByUserId(@Param("userId")Integer userId);
    List<Map<Integer, String>> findPermissionsByUserId(@Param("roleId")Integer roleId);
    SysUserVO getByCloginName(@Param("cloginName")String cloginName);
    void updateStatusByLoginName(@Param("cloginName")String cloginName,@Param("status")String status);
}
