package com.base.web.utils;

import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.basic.SysRoleModuleVO;

import java.util.Iterator;
import java.util.List;

public class ZtreeUtil {

    public static List<SysModuleVO> toSysModuleDTOsFromSysModules(List<SysModuleVO> sysModules) {
        for (SysModuleVO vo : sysModules) {
            if ("1".equals(vo.getId())) {
                vo.setOpen(true);
            }
            if ("0".equals(vo.getCisLeaf())) {
                vo.setParent(false);
            } else {
                vo.setParent(true);
            }
        }
        return sysModules;
    }


    public static void dealRoleIsChecked(List<SysModuleVO> sysModuleVOS, List<SysRoleModuleVO> sysRoleModuleVOS) {
        Iterator var2 = sysRoleModuleVOS.iterator();
        while (true) {
            while (var2.hasNext()) {
                SysRoleModuleVO sysRoleModuleVO = (SysRoleModuleVO) var2.next();
                Iterator var4 = sysModuleVOS.iterator();
                while (var4.hasNext()) {
                    SysModuleVO sysModuleVO = (SysModuleVO) var4.next();
                    if (sysRoleModuleVO.getImoduleId().equals(sysModuleVO.getId())) {
                        sysModuleVO.setChecked(true);
                        sysModuleVO.setDataBaseChecked(true);
                        break;
                    }
                }
            }
            return;
        }
    }

    public static void dealOpen(List<SysModuleVO> sysModuleVOS) {
        Iterator var1 = sysModuleVOS.iterator();
        while (var1.hasNext()) {
            SysModuleVO sysModuleVO = (SysModuleVO) var1.next();
            sysModuleVO.setOpen(true);
        }
    }
}
