package com.base.web.utils;

import com.base.pojo.basic.SysModuleSourceVO;
import java.util.Iterator;
import java.util.List;

public class PermissionUtil {
    public static List<SysModuleSourceVO> toSysModuleSourceDTOsFromSysModuleSources(List<SysModuleSourceVO> sysModuleSources, List<Integer> moduleSourceIds) {
        Iterator var2 = sysModuleSources.iterator();
        while (var2.hasNext()){
            SysModuleSourceVO sysModuleSource = (SysModuleSourceVO)var2.next();
            if (moduleSourceIds != null && moduleSourceIds.size() > 0 && moduleSourceIds.contains(sysModuleSource.getId())) {
                sysModuleSource.setChecked(true);
            }
        }
        return sysModuleSources;
    }
}
