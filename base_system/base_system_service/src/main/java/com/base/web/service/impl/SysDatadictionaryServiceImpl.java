/**
 * cn.rmt.admin.service.impl.SysDatadictionaryService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysDatadictionaryMapper;
import com.base.mapper.model.SysDatadictionary;
import com.base.pojo.basic.SysDatadictionaryVO;
import com.base.web.service.ISysDatadictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Service
public class SysDatadictionaryServiceImpl extends BaseBusService<SysDatadictionary,SysDatadictionaryVO> implements ISysDatadictionaryService {

	@Autowired
	private ISysDatadictionaryMapper dao;
	
	@Override
	protected ISysDatadictionaryMapper getDao() {
		return dao;
	}

}
