/**
 * cn.rmt.admin.service.impl.SysParamService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysParamCategoryMapper;
import com.base.mapper.basic.ISysParamMapper;
import com.base.mapper.model.SysParam;
import com.base.pojo.basic.SysParamCategoryQueryParam;
import com.base.pojo.basic.SysParamCategoryVO;
import com.base.pojo.basic.SysParamVO;
import com.base.pojo.basic.SysUserVO;
import com.base.pojo.bean.SysAtreeParamCategoryBean;
import com.base.pojo.constant.MainConstant;
import com.base.web.service.ISysParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Service
public class SysParamServiceImpl extends BaseBusService<SysParam,SysParamVO> implements ISysParamService {

	@Autowired
	private ISysParamMapper dao;
	@Autowired
	private ISysParamCategoryMapper sysParamCategoryMapper;
	
	@Override
	protected ISysParamMapper getDao() {
		return dao;
	}

	public Map<String,Object> categoryList(){
		Map<String,Object> map= Maps.newHashMap();
		SysParamCategoryQueryParam queryParam=new SysParamCategoryQueryParam();
		OrderBean bean=new OrderBean();
		bean.setOrder("ASC");
		bean.setOrderBy("C_ORDER");
		queryParam.setOrderBean(bean);
		List<SysParamCategoryVO> list=sysParamCategoryMapper.findBy(queryParam);
		List<SysAtreeParamCategoryBean> sysAtreeParamCategoryBeans= Lists.newArrayList();
		for (SysParamCategoryVO paramCategoryVO:list){
			if (paramCategoryVO.getIpid().equals(MainConstant.PARAM_CATEGORY_PID)){
				SysAtreeParamCategoryBean paramCategoryBean=getAtreeParamCategoryBean(paramCategoryVO,list);
				sysAtreeParamCategoryBeans.add(paramCategoryBean);
			}
		}
		map.put("nodes",sysAtreeParamCategoryBeans);
		return map;
	}
	/**
	 * 构建AtreeContentCategoryBean（实体对象）
	 */
	public static SysAtreeParamCategoryBean getAtreeParamCategoryBean(SysParamCategoryVO bean, List<SysParamCategoryVO> sysParamCategoryVOS){
		SysAtreeParamCategoryBean sysAtreeParamCategoryBean=new SysAtreeParamCategoryBean(bean.getId(),bean.getCname(),true);
		sysAtreeParamCategoryBean.setChildren(getChild(bean.getId(),sysParamCategoryVOS));
		return sysAtreeParamCategoryBean;
	}

	/**
	 * 递归查找子菜单
	 */
	public static List<SysAtreeParamCategoryBean> getChild(Integer id, List<SysParamCategoryVO> rootMenu) {
		List<SysAtreeParamCategoryBean> childList = Lists.newArrayList();
		for (SysParamCategoryVO root : rootMenu) {
			// 遍历所有节点，将父菜单编码与传过来的编码进行比较、若相同则继续查看该节点下是否还有子节点
			if (root.getIpid()!=null) {
				if (root.getIpid().equals(id)) {
					SysAtreeParamCategoryBean grid = getAtreeParamCategoryBean(root, rootMenu);
					childList.add(grid);
				}
			}
		}
		return childList;
	}

	public Map<String, Object> sysParamTable(Integer page, Integer limit, Integer paramCategoryId){
		Map<String,Object> map=Maps.newHashMap();
		PageHelper.startPage(page, limit);
		List<SysParamVO> sysParamVOS = dao.getSysParamTable(paramCategoryId);
		PageInfo<SysParamVO> pageList = new PageInfo<>(sysParamVOS);
		map.put("code", 0);
		map.put("msg", "查询成功");
		map.put("count", pageList.getTotal() );
		map.put("data", pageList.getList());
		return map;
	}
}
