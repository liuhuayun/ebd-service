package cn.comtom.cbs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.OrgPeersMapper;
import cn.comtom.cbs.model.OrgPeers;
import cn.comtom.cbs.service.OrgPeersService;
import tk.mybatis.mapper.entity.Condition;


/**
 * OrgPeers Service实现类，Created on 2018-12-29.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgPeersServiceImpl extends AbstractService<OrgPeers> implements OrgPeersService {
    @Resource
    private OrgPeersMapper tOrgPeersMapper;

	@Override
	public List<String> getAllSubOrgIds(String parentOrgId) {
		// 首先把parentOrgId加入列表
		List<String> orgIdList = new ArrayList<>();
		orgIdList.add(parentOrgId);
		
		parentOrgId = "'" + parentOrgId + "'";
		Condition cond1 = new Condition(OrgPeers.class);
		cond1.createCriteria().andCondition("parent_id=" + parentOrgId);
		List<OrgPeers> orgPeersList = this.findByCondition(cond1);

		// 取出org_id并去重			
		for (OrgPeers s : orgPeersList) {
			String orgId = s.getOrgId();
			if (Collections.frequency(orgIdList, orgId) < 1) {
				orgIdList.add(orgId);
			}
		}
		return orgIdList;
	}
	
	/**
	 * 生成按orgId过滤条件
	 * 
	 * @return String
	 */
	public String createOrgFilter(String parentOrgId) {
		List<String> orgIdList = this.getAllSubOrgIds(parentOrgId);
		String ids = "";
		boolean first = true;
		for (String s : orgIdList) {
			if (first) {
				ids += "'" + s + "'";
				first = false;
			} else {
				ids += ",'" + s + "'";
			}
		}
		return "org_id in (" + ids + ")";
	}
	/**
	 * 生成按orgId过滤条件
	 * 
	 * @return String
	 */
	public String createOrgFilter(String parentOrgId, String column) {
		List<String> orgIdList = this.getAllSubOrgIds(parentOrgId);
		String ids = "";
		boolean first = true;
		for (String s : orgIdList) {
			if (first) {
				ids += "'" + s + "'";
				first = false;
			} else {
				ids += ",'" + s + "'";
			}
		}
		return column + " in (" + ids + ")";
	}
}
