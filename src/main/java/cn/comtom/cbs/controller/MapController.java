package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.SysParams;
import cn.comtom.cbs.service.SysParamsService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangbingyan
 * @desc 地图资源接口
 */
@RestController
@RequestMapping("bc/map")
@Api(tags = "地图轮播模块接口")
public class MapController {

    @Autowired
    private SysParamsService sysParamsService;

    /**
     * 预警阶段轮播时长的配置ID
     */
    private final String SCROLL_TIME = "scroll_time";

    /**
     * 界面无操作就自动轮播的时长配置ID
     */
    private final String NO_OPERATION = "no_operation";

    /**
     * 获取地图边界信息
     * @return
     * @throws IOException
     */
    @GetMapping("/geo")
    public Result mapGeo() throws IOException {
        ClassPathResource mapResource = new ClassPathResource("geojson.geojson");
        InputStream inputStream = mapResource.getInputStream();
        String geojson = IOUtils.toString(inputStream, "UTF-8");
        JSONObject obj = JSONObject.parseObject(geojson);
        return ResultGenerator.genSuccessResult(obj);
    }

    /**
     * 获取应急广播配置表中配置的轮播时间
     * @return
     */
    @GetMapping("/settingTime")
    public Result getSettingTime() {
        Map<String, String> confgs = new HashMap<>();
        String scrollTime = sysParamsService.getValByKey(SCROLL_TIME);
        confgs.put(SCROLL_TIME, scrollTime);
        String noOperation = sysParamsService.getValByKey(NO_OPERATION);
        confgs.put(NO_OPERATION, noOperation);
        return ResultGenerator.genSuccessResult(confgs);
    }
}
