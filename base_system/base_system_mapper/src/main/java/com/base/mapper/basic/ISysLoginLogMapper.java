/**
 * com.base.dao.ISysLoginLogDAO
 */
package com.base.mapper.basic;


import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysLoginLog;
import com.base.pojo.basic.SysLoginLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public interface ISysLoginLogMapper extends IBaseEntityDAO<SysLoginLog,SysLoginLogVO> {
    List<SysLoginLogVO> getLoginLogList(@Param("cname")String cname,@Param("startTime")String startTime,@Param("endTime")String endTime);
    Integer delLogCount(@Param("date")String date);
    void delByDate(@Param("date") String date);
}
