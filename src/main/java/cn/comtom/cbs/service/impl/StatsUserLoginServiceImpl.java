package cn.comtom.cbs.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.StatsUserLoginMapper;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.StatsUserLogin;
import cn.comtom.cbs.service.OrgPeersService;
import cn.comtom.cbs.service.StatsUserLoginService;
import cn.comtom.cbs.utils.MyUUID;
import cn.comtom.cbs.utils.SqlUtils;

/**
 * StatsUserLogin Service实现类，Created on 2018-12-30.
 * 
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatsUserLoginServiceImpl extends AbstractService<StatsUserLogin> implements StatsUserLoginService {
	final String SUM_IN_TYPE = "clientType";
	final String SUM_IN_TIME = "time";
	final String SUM_IN_DAY = "day";
	final String SUM_IN_WEEK = "week";
	final String SUM_IN_MONTH = "month";
	final String SUM_IN_YEAR = "year";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private StatsUserLoginMapper statsUserLoginMapper;
	@Resource
	private OrgPeersService orgPeersService;

	@Override
	public List<StatsUserDto> findByDay(String beginDate, String endDate, String orgId) {
		return statsUserLoginMapper.findByDay(beginDate, endDate, orgId);
	}

	public List<StatsUserLogin> createRandomItems(int count) {
		String[] userIds = { "user1", "user2", "user3", "user4", "user5" };
		String[] clientTypes = { "web", "IOS", "Android", "WeiXin" };
		String[] loginDates = { "2019-01-01", "2019-01-02", "2019-01-03", "2019-01-04", "2019-01-05" };
		Random random = new Random();
		List<StatsUserLogin> list = new ArrayList<>();
		int i = 0;
		while (i < count) {
			i++;
			StatsUserLogin statsUserLogin = new StatsUserLogin();

			statsUserLogin.setLoginId(MyUUID.randomUUID().substring(0, 31));

			int idx = random.nextInt(userIds.length);
			statsUserLogin.setUserId(userIds[idx]);

			idx = random.nextInt(clientTypes.length);
			statsUserLogin.setClientType(clientTypes[idx]);

			idx = random.nextInt(loginDates.length);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = ft.parse(loginDates[idx]);
				statsUserLogin.setLoginDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(statsUserLogin);
		}

		return list;
	}

	public String createBatchAddSqlStr(List<StatsUserLogin> list) {
		String sqlStr = "insert into stats_user_login(login_id, user_id, login_date, client_type) VALUES ";
		if (list.size() == 0) {
			return sqlStr + "()";
		}
		boolean first = true;

		for (StatsUserLogin s : list) {
			if (first) {
				first = false;
				sqlStr += "(";
			} else {
				sqlStr += ",(";
			}
			sqlStr += "'" + s.getLoginId() + "'";
			sqlStr += ",'" + s.getUserId() + "'";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sqlStr += ",'" + sdf.format(s.getLoginDate()) + "'";
			sqlStr += ",'" + s.getClientType() + "'";
			sqlStr += ")";
		}

		return sqlStr;
	}

	@Override
	public void batchAdd(int count) {
		List<StatsUserLogin> list = this.createRandomItems(count);
		String sqlStr = this.createBatchAddSqlStr(list);
		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);

		statsUserLoginMapper.batchAdd(map);
	}

	public String createSubSql(String sumType, String parentOrgId, String beginDate, String endDate) {
		// select SUM(1) as count, user_id from stats_user_login group by user_id
		// select SUM(1) as count, user_id, DATE_FORMAT(login_date,'%Y-%m-%d') time from
		//   stats_user_login group by user_id, DATE_FORMAT(login_date,'%Y-%m-%d')
		String sqlStr = "select SUM(1) as count, user_id";
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", client_type";
		}
		if (sumType.contains(SUM_IN_DAY)) {
			sqlStr += ", " + SqlUtils.createDateString("login_date") + " time";
		}
		if (sumType.contains(SUM_IN_WEEK)) {
			sqlStr += ", " + SqlUtils.createWeekString("login_date") + " time";
		}
		if (sumType.contains(SUM_IN_MONTH)) {
			sqlStr += ", " + SqlUtils.createMonthString("login_date") + " time";
		}
		if (sumType.contains(SUM_IN_YEAR)) {
			sqlStr += ", " + SqlUtils.createYearString("login_date") + " time";
		}

		sqlStr += " from stats_user_login WHERE 1=1";
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
		if (!beginDate.isEmpty()) {
			sqlStr += " AND " + SqlUtils.createBeginDateFilter("login_date", beginDate);
		}
		if (!endDate.isEmpty()) {
			sqlStr += " AND " + SqlUtils.createEndDateFilter("login_date", endDate);
		}
		sqlStr += " group by user_id";
		if (sumType.contains(SUM_IN_DAY)) {
			sqlStr += ", " + SqlUtils.createDateString("login_date");
		}
		if (sumType.contains(SUM_IN_WEEK)) {
			sqlStr += ", " + SqlUtils.createWeekString("login_date");
		}
		if (sumType.contains(SUM_IN_MONTH)) {
			sqlStr += ", " + SqlUtils.createMonthString("login_date");
		}
		if (sumType.contains(SUM_IN_YEAR)) {
			sqlStr += ", " + SqlUtils.createYearString("login_date");
		}
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", client_type";
		}
		return sqlStr;
	}

	public String createSummarySqlStr(String sumType, String parentOrgId, String beginDate, String endDate) {

		String sqlStr = "select SUM(1) as count";
		boolean groupBy = false;
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", r.client_type";
			groupBy = true;
		}
		if (sumType.contains(SUM_IN_DAY)) {
			sqlStr += ", r.time as day";
			groupBy = true;
		} else if (sumType.contains(SUM_IN_WEEK)) {
			sqlStr += ", r.time as week";
			groupBy = true;
		} else if (sumType.contains(SUM_IN_MONTH)) {
			sqlStr += ", r.time as month";
			groupBy = true;
		} else if (sumType.contains(SUM_IN_YEAR)) {
			sqlStr += ", r.time as year";
			groupBy = true;
		}

		sqlStr += " from (" + this.createSubSql(sumType, parentOrgId, beginDate, endDate) + ") as r";
		
		if (groupBy) {
			sqlStr += " group by ";
		}

		boolean firstDim = true;
		if (sumType.contains(SUM_IN_TYPE)) {
			if (!firstDim) {
				sqlStr += ",";
			}
			sqlStr += " r.client_type";
			firstDim = false;
		}
		if (sumType.contains(SUM_IN_DAY) || sumType.contains(SUM_IN_WEEK) || sumType.contains(SUM_IN_MONTH) || sumType.contains(SUM_IN_YEAR)) {
			if (!firstDim) {
				sqlStr += ",";
			}
			sqlStr += " r.time order by r.time asc";
			firstDim = false;
		}

		return sqlStr;
	}
	public String createTotalSubSql(String period, String parentOrgId) {
		// 如果period为空，则得到活跃用户总数
		// select SUM(1) as count, user_id from stats_user_login group by user_id
		// select SUM(1) as count, user_id from
		//   stats_user_login where DATE_FORMAT(login_date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') group by user_id
		// select SUM(1) as count, user_id from stats_user_login where YEARWEEK(login_date) = YEARWEEK(now()) group by user_id
		String sqlStr = "select SUM(1) as count, user_id";
		
		sqlStr += " from stats_user_login WHERE 1=1";
		switch (period) {
		case "day":
			sqlStr += " AND DATE_FORMAT(login_date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')";			
			break;
		case "week":
			sqlStr += " AND YEARWEEK(login_date) = YEARWEEK(now())";			
			break;
		case "month":
			sqlStr += " AND DATE_FORMAT(login_date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m')";			
			break;
		case "year":
			sqlStr += " AND DATE_FORMAT(login_date,'%Y') = DATE_FORMAT(now(),'%Y')";			
			break;

		default:
			break;
		}
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
	
		sqlStr += "group by user_id";
		
		return sqlStr;
	}

	public String createTotalSqlStr(String period, String parentOrgId) {
		String sqlStr = "select SUM(1) as count";
		sqlStr += " from (" + this.createTotalSubSql(period, parentOrgId) + ") as r";
		return sqlStr;
	}
	@Override
	public List<StatsUserDto> summary(String sumType, String parentOrgId, String beginDate, String endDate) {
		String sqlStr = this.createSummarySqlStr(sumType, parentOrgId, beginDate, endDate);
		logger.info("user login summary sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return statsUserLoginMapper.summary(map);
	}

	@Override
	public List<StatsUserDto> total(String period, String parentOrgId) {
		String sqlStr = this.createTotalSqlStr(period, parentOrgId);
		logger.info("user total sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return statsUserLoginMapper.summary(map);
	}
}
