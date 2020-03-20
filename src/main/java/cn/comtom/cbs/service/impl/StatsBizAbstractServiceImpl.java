package cn.comtom.cbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.StatsBizAbstractMapper;
import cn.comtom.cbs.dto.StatsBizAbstractDto;
import cn.comtom.cbs.model.StatsBizAbstract;
import cn.comtom.cbs.service.OrgPeersService;
import cn.comtom.cbs.service.StatsBizAbstractService;

/**
 * StatsBizAbstract Service实现类，Created on 2018-12-31.
 * 
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatsBizAbstractServiceImpl extends AbstractService<StatsBizAbstract> implements StatsBizAbstractService {
	@Resource
	private StatsBizAbstractMapper statsBizAbstractMapper;

	// @Autowired
	// private WebSocket webSocket;
	@Resource
	private OrgPeersService orgPeersService;

	public String createSqlStr(String dateStr, String parentOrgId) {

		String sqlStr = "SELECT s.*, o.org_name from stats_biz_abstract s, t_org o WHERE s.org_id = o.org_id ";

		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId, "s.org_id");
		}
		
		if (!dateStr.isEmpty()) {
			sqlStr += " AND DATE_FORMAT(s.biz_time,'%Y-%m-%d')=#{date} ";
		}
		sqlStr += " ORDER BY s.biz_time desc";

		return sqlStr;
	}

	@Override
	public List<StatsBizAbstractDto> findByDay(String date, String orgId) {
		String sqlStr = this.createSqlStr(date, orgId);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);

		List<StatsBizAbstractDto> list = statsBizAbstractMapper.findByDay(map);
		return list;
	}

}
