package cn.comtom.cbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.UserMapper;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.User;
import cn.comtom.cbs.service.OrgPeersService;
import cn.comtom.cbs.service.UserService;

/**
 * User Service实现类，Created on 2018-12-28.
 * 
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
	final String SUM_IN_TIME = "time";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserMapper tUserMapper;
	
	@Resource
	private OrgPeersService orgPeersService;

	@Override
	public List<StatsUserDto> findByPeriod(String beginDate, String endDate, String orgId, String period) {
		// TODO Auto-generated method stub
		return tUserMapper.findByPeriod(beginDate, endDate, orgId, period);
	}

	@Override
	public List<StatsUserDto> findByIncreaseUserPeriod(String beginDate, String endDate, String orgId, String period) {
		// TODO Auto-generated method stub
		return tUserMapper.findByIncreaseUserPeriod(beginDate, endDate, orgId, period);
	}
	public String createSqlStr(String sumType, String parentOrgId) {

		String sqlStr = "select SUM(1) as count";
		boolean groupBy = false;
		
		if (sumType.contains(SUM_IN_TIME)) {
			sqlStr += ", DATE_FORMAT(create_time,'%Y-%m-%d') time ";
			groupBy = true;
		}
		
		sqlStr += " from t_user WHERE 1=1";
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
		
		if (groupBy) {
			sqlStr += " group by ";
		}

		boolean firstDim = true;
		
		if (sumType.contains(SUM_IN_TIME)) {
			if (!firstDim) {
				sqlStr += ", ";
			}
			sqlStr += " DATE_FORMAT(create_time,'%Y-%m-%d')";
			
			firstDim = false;
		}
		if (sumType.contains(SUM_IN_TIME)) {
			sqlStr += " order by DATE_FORMAT(create_time,'%Y-%m-%d') asc";
		}
		return sqlStr;
	}

	@Override
	public List<StatsUserDto> summary(String sumType, String parentOrgId) {
		String sqlStr = this.createSqlStr(sumType, parentOrgId);
		logger.info("dev summary sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return tUserMapper.summary(map);
	}
	
	public String createTotalSubSqlStr(String sumType, String parentOrgId) {
		String sqlStr = "select sum(1) as count, DATE_FORMAT(create_time,'%Y-%m-%d') time ";
		
		sqlStr += " from t_user WHERE 1=1";
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
		sqlStr += " group by DATE_FORMAT(create_time,'%Y-%m-%d') ";

		return sqlStr;
	}	
	public String createTotalSqlStr(String sumType, String parentOrgId) {
		/*
		 * select a.time, sum(b.count) as total from 
		 * (select sum(1) as count,
		 * DATE_FORMAT(create_time,'%Y-%m-%d') time from t_user group by
		 * DATE_FORMAT(create_time,'%Y-%m-%d')) a 
		 * join 
		 * (select sum(1) as count,
		 * DATE_FORMAT(create_time,'%Y-%m-%d') time from t_user group by
		 * DATE_FORMAT(create_time,'%Y-%m-%d')) b 
		 * where a.time >= b.time group
		 * by a.time order by a.time asc;
		 */
		
		String sqlStr = "select a.time, sum(b.count) as total ";
		
		sqlStr += " from (";
		sqlStr += this.createTotalSubSqlStr(sumType, parentOrgId);
		sqlStr += ") a join (";
		sqlStr += this.createTotalSubSqlStr(sumType, parentOrgId);
				
		sqlStr += ") b where a.time >= b.time ";
		
		sqlStr += " group by a.time ";

		sqlStr += " order by a.time asc";
		return sqlStr;
	}
	@Override
	public List<StatsUserDto> total(String sumType, String parentOrgId) {
		String sqlStr = this.createTotalSqlStr(sumType, parentOrgId);
		logger.info("user total sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return tUserMapper.summary(map);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return tUserMapper.findByUserName(username);
	}

}
