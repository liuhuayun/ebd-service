package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.OrgMapper;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.OrgService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Org Service实现类，Created on 2018-12-28.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImpl extends AbstractService<Org> implements OrgService {
    @Resource
    private OrgMapper tOrgMapper;

}
