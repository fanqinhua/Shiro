/**
 * com.base.service.impl.SysLoginLogService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysLoginLogMapper;
import com.base.mapper.model.SysLoginLog;
import com.base.pojo.basic.SysLoginLogVO;
import com.base.web.service.ISysLoginLogService;
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
public class SysLoginLogServiceImpl extends BaseBusService<SysLoginLog, SysLoginLogVO> implements ISysLoginLogService {

    @Autowired
    private ISysLoginLogMapper dao;

    @Override
    protected ISysLoginLogMapper getDao() {
        return dao;
    }


    public void insertLoginLog(Integer loginUserId, String loginUserName, String loginIP, Date loginTime,  String result) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setIuserId(loginUserId);
        loginLog.setCuserLoginName(loginUserName);
        loginLog.setChostIp(loginIP);
        loginLog.setDloginTime(loginTime);
        loginLog.setCresult(result);
        this.dao.insert(loginLog);
    }
    public Map<String,Object> getLoginLogList(Integer page, Integer limit, String cname, String startTime, String endTime){
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
            List<SysLoginLogVO> sysLoginLogVOS = dao.getLoginLogList(cname,startTime,endTime);
            PageInfo<SysLoginLogVO> pageList = new PageInfo<>(sysLoginLogVOS);
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("count", pageList.getTotal());
            map.put("data", pageList.getList());
        }catch (Exception e){
            e.getStackTrace();
        }
        return map;
    }

    public Integer delByDate(String date){
        int count = dao.delLogCount(date);
        dao.delByDate(date);
        return count;
    }
}
