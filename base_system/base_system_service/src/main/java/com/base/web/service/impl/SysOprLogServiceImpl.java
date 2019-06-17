/**
 * com.base.service.impl.SysOprLogService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysOprLogMapper;
import com.base.mapper.model.SysOprLog;
import com.base.pojo.basic.SysOprLogVO;
import com.base.web.service.ISysOprLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Service
public class SysOprLogServiceImpl extends BaseBusService<SysOprLog,SysOprLogVO> implements ISysOprLogService {

	@Autowired
	private ISysOprLogMapper dao;

	@Override
	protected ISysOprLogMapper getDao() {
		return dao;
	}
	public void insertOprLog(String oprContent, Date oprDate, Integer oprUserId, String oprName, String oprIP, String result){
		SysOprLog oprLog=new SysOprLog();
		oprLog.setCoprDetail(oprContent);
		oprLog.setDcreateTime(oprDate);
		oprLog.setIuserId(String.valueOf(oprUserId));
		oprLog.setCuserName(oprName);
		oprLog.setChostIp(oprIP);
		oprLog.setCresult(result);
		this.dao.insert(oprLog);
	}
	public Map<String,Object> getOprLogList(Integer page, Integer limit, String cname, String startTime, String endTime){
		Map<String, Object> map = Maps.newHashMap();
		try {
			PageHelper.startPage(page, limit);
			if (cname!=null&&cname!="") {
				cname = new String(cname.getBytes("iso-8859-1"), "utf-8");
			}
			if (startTime!=null&&startTime!=""){
				startTime=new String(startTime.getBytes("iso-8859-1"),"utf-8");
			}
			if (endTime!=null&&endTime!=""){
				endTime=new String(endTime.getBytes("iso-8859-1"),"utf-8");
			}
			List<SysOprLogVO> sysOprLogVOS = dao.getOprLogList(cname,startTime,endTime);
			PageInfo<SysOprLogVO> pageList = new PageInfo<>(sysOprLogVOS);
			map.put("code", 0);
			map.put("msg", "查询成功");
			map.put("count", pageList.getTotal());
			map.put("data", pageList.getList());
		}catch (Exception e){
			e.getStackTrace();
		}
		return map;
	}
}
