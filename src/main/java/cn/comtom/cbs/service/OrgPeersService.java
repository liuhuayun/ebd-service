package cn.comtom.cbs.service;
import cn.comtom.cbs.model.OrgPeers;

import java.util.List;

import cn.comtom.cbs.core.Service;


/**
 * OrgPeers Service接口，Created on 2018-12-29.
 * @author William 
 */
public interface OrgPeersService extends Service<OrgPeers> {
	/**
	 * 获取所有下属区域ID（包括自己、子ID、子子ID...)
	 * @param parentOrgId 当前区域ID
	 * @return list 区域ID列表
	 */
	List<String> getAllSubOrgIds(String parentOrgId);
	
	/**
	 * 生成按orgId过滤条件
	 * 
	 * @return String
	 */
	public String createOrgFilter(String parentOrgId);
	/**
	 * 生成按orgId过滤条件
	 * 
	 * @return String
	 */
	public String createOrgFilter(String parentOrgId, String column);
}
