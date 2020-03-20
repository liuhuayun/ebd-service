package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.ChartConfigMapper;
import cn.comtom.cbs.model.ChartConfig;
import cn.comtom.cbs.service.ChartConfigService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * ChartConfig Service实现类，Created on 2019-01-01.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChartConfigServiceImpl extends AbstractService<ChartConfig> implements ChartConfigService {
    @Resource
    private ChartConfigMapper tChartConfigMapper;

}
