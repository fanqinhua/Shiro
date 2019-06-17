package com.base.pojo.constant;

/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>fqhua<br>
 * <b>日期：</b><br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public class MainConstant {
    /**
     * 树形菜单基类PID=0
     */
    public static Integer MODULE_PID=0;
    /**
     * 系统参数基类PID=0
     */
    public static Integer PARAM_CATEGORY_PID=0;
    /**
     * 菜单状态
     * 状态：00为启用，01为禁用',
     */
    public static String MODULE_STATUS_OK="00";
    public static String MODULE_STATUS_NO="01";
    /**
     * 菜单可见
     * 是否可见：0为可见，1为不可见'
     */
    public static String MODULE_VISABLE_OK="0";
    public static String MODULE_VISABLE_NO="1";
    /**
     * 是否叶子结点：1：不是叶子节点 0：叶子节点
     */
    public static String MODULE_IS_LEAF="0";
    public static String MODULE_NO_LEAF="1";

    /**
     * 机构状态：'00：启用，01停用'
     */
    public static String ORG_STATUS_OK="00";
    public static String ORG_STATUS_NO="01";

    /**
     * 角色状态： 00：启用，01：信用
     */
    public static String ROLE_STATUS_OK="00";
    public static String ROLE_STATUS_NO="01";

    /**
     * 用户活动状态:0离线,1在线,2,繁忙
     */
    public static String USER_ACTIVE_NOONLINE="0";
    public static String USER_ACTIVE_ONLINE="1";
    public static String USER_ACTIVE_BUSY="2";

    /**
     * 用户状态：00：启用，01：未启用,02：冻结登陆
     */
    public static String USER_STATUS_OK="00";
    public static String USER_STATUS_NO="01";
    public static String USER_STATUS_CLOSE="02";

    /**
     * 登陆状态：登陆结果：00：成功，01：失败
     */
    public static String USER_LOGIN_STATUS_OK="00";
    public static String USER_LOGIN_STATUS_NO="01";

    /**
     * 操作状态：操作结果：00：成功，01：失败
     */
    public static String USER_OPR_STATUS_OK="00";
    public static String USER_OPR_STATUS_NO="01";
    /**
     * 系统参数状态：结果：00：成功，01：失败
     */
    public static String PARAM_STATUS_OK="00";
    public static String PARAM_STATUS_NO="01";
}
