package com.base.web.controller.system;

import com.base.pojo.basic.SysParamVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.JsonResVo;
import com.base.web.service.ISysParamService;
import com.base.web.utils.ConfigConstant;
import com.base.web.utils.OprLogUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sysParam")
public class SysParamController extends SysBaseController{
    private Logger logger = LoggerFactory.getLogger(SysParamController.class);
    @Autowired
    private ISysParamService sysParamService;
    @Autowired
    private ConfigConstant config;

    @RequestMapping("/main")
    public String login(HttpServletRequest request) {
        return "system/sysParam/main";
    }

    @RequestMapping(value = "/categoryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> categoryList() {
        Map<String, Object> map = sysParamService.categoryList();
        return map;
    }

    @RequestMapping(value = "/sysParamTable", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> sysParamTable(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, @RequestParam(value = "paramCategoryId", required = false) Integer paramCategoryId, HttpServletRequest request) {
        Map<String, Object> map = sysParamService.sysParamTable(page, limit, paramCategoryId);
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public JsonResVo delete(@RequestParam(value = "paramIds", required = false) String paramIds) {
        JsonResVo result = new JsonResVo();
        String[] paramId = StringUtils.split(paramIds, ",");
        for (String id : paramId) {
            SysParamVO vo=sysParamService.findById( id);
            vo.setCstatus(MainConstant.PARAM_STATUS_NO);
            sysParamService.update(null,vo);
            OprLogUtil.insert("禁用参数:{"+vo.getCcode()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
        }
        result.setSuccess(true);
        result.setMsg("操作成功");
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, Model model) {
        String paramCategoryId = request.getParameter("paramCategoryId");
        model.addAttribute("paramCategoryId", paramCategoryId);
        return new ModelAndView("system/sysParam/edit", null);
    }

    /**
     * 图片上传业务逻辑
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadPic(HttpServletRequest request,
                                         @RequestParam("file") MultipartFile file
    ) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String rootPath = config.getRootDir();
        String fileServerPath=config.getFileServer();
        String basePath = fileServerPath + rootPath;
        String dayPath = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String coverPathDB = rootPath + "/" + dayPath + "/";
        String productPicPath = basePath + "/" + dayPath + "/";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                File folder = new File(productPicPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                /* 生成完整的图像名 */
                String imageName = String.valueOf(System.currentTimeMillis()) + RandomStringUtils.randomAlphanumeric(4)
                        + ".png";
                //保存图片到本地工程
                fileOutputStream = new FileOutputStream(productPicPath + imageName);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
                //设置保存的路径
                coverPathDB = coverPathDB + imageName;
            } else {
                map.put("code", "1");
                return map;
            }
        } catch (Exception e) {
            map.put("code", "1");
            logger.info(e.getMessage());
            e.getStackTrace();
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        map.put("code", "0");
        map.put("pic", coverPathDB);
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResVo save(SysParamVO paramVO) {
        JsonResVo result = new JsonResVo();
        paramVO.setDcreateTime(new Date());
        paramVO.setDupdateTime(new Date());
        if (paramVO.getIparamCategoryId()==null){
            paramVO.setIparamCategoryId(MainConstant.PARAM_CATEGORY_PID);
        }
        int i=sysParamService.insert(null,paramVO);
        if (i==1){
            OprLogUtil.insert("新增参数:{"+paramVO.getCcode()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
            result.setSuccess(true);
        }else {
            OprLogUtil.insert("新增参数:{"+paramVO.getCcode()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
            result.setSuccess(false);
        }
        return result;
    }
}
