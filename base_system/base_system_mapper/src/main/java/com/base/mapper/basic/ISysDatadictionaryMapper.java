/**
 * cn.rmt.admin.dao.ISysDatadictionaryDAO
 */
package com.base.mapper.basic;

import cn.rmt.framework.dao.IBaseEntityDAO;
import com.base.mapper.model.SysDatadictionary;
import com.base.pojo.basic.SysDatadictionaryVO;
import org.springframework.stereotype.Repository;


/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Repository
public interface ISysDatadictionaryMapper extends IBaseEntityDAO<SysDatadictionary,SysDatadictionaryVO> {

}
