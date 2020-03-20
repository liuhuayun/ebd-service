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
import cn.comtom.cbs.dao.DevMapper;
import cn.comtom.cbs.dto.DevDto;
import cn.comtom.cbs.model.Dev;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.DevService;
import cn.comtom.cbs.service.OrgPeersService;
import cn.comtom.cbs.utils.MyUUID;
import tk.mybatis.mapper.entity.Condition;

/**
 * Dev Service实现类，Created on 2018-12-28.
 * 
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DevServiceImpl extends AbstractService<Dev> implements DevService {
	final String SUM_IN_TYPE = "devType";
	final String SUM_IN_DATE = "bindDate";
	final String SUM_IN_STATUS = "status";
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private DevMapper tDevMapper;

	@Resource
	private OrgPeersService orgPeersService;

	public String createSqlStr(String sumType, String parentOrgId) {

		String sqlStr = "select SUM(1) as count";
		boolean groupBy = false;
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", dev_type ";
			groupBy = true;
		}
		if (sumType.contains(SUM_IN_DATE)) {
			sqlStr += ", DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date ";
			groupBy = true;
		}
		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += ", online_status, biz_status ";
			groupBy = true;
		}
		sqlStr += " from t_dev WHERE 1=1";
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
		if (groupBy) {
			sqlStr += " group by ";
		}

		boolean firstDim = true;
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += " dev_type ";
			firstDim = false;
		}
		if (sumType.contains(SUM_IN_DATE)) {
			if (!firstDim) {
				sqlStr += ", ";
			}
			sqlStr += " DATE_FORMAT(bind_time,'%Y-%m-%d')";
			firstDim = false;
		}
		if (sumType.contains(SUM_IN_STATUS)) {
			if (!firstDim) {
				sqlStr += ", ";
			}
			sqlStr += "online_status, biz_status";
			firstDim = false;
		}
		if (sumType.contains(SUM_IN_DATE)) {
			sqlStr += " order by bind_date asc";
		}
		return sqlStr;
	}

	public String createTotalSubSqlStr(String sumType, String parentOrgId) {
		String sqlStr = "select sum(1) as count, DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date ";
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", dev_type ";
		}
		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += ", online_status, biz_status ";
		}
		sqlStr += " from t_dev WHERE 1=1";
		if (!parentOrgId.isEmpty()) {
			sqlStr += " AND " + orgPeersService.createOrgFilter(parentOrgId);
		}
		sqlStr += " group by DATE_FORMAT(bind_time,'%Y-%m-%d') ";

		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ",dev_type ";
		}

		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += ",online_status, biz_status";
		}
		return sqlStr;
	}

	public String createTotalSqlStr(String sumType, String parentOrgId) {
		/*
		 * select a.bind_date, sum(b.count) as total from 
		 * (select sum(1) as count,
		 * DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date from t_dev group by
		 * DATE_FORMAT(bind_time,'%Y-%m-%d')) a 
		 * join 
		 * (select sum(1) as count,
		 * DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date from t_dev group by
		 * DATE_FORMAT(bind_time,'%Y-%m-%d')) b 
		 * where a.bind_date >= b.bind_date group
		 * by a.bind_date order by a.bind_date asc;
		 */
		/*
		 * select a.bind_date, a.online_status, a.biz_status, sum(b.count) as total from
		 * (select sum(1) as count, DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date,
		 * online_status, biz_status from t_dev group by
		 * DATE_FORMAT(bind_time,'%Y-%m-%d'), online_status, biz_status) a 
		 * join 
		 * (select
		 * sum(1) as count, DATE_FORMAT(bind_time,'%Y-%m-%d') bind_date, online_status,
		 * biz_status from t_dev group by DATE_FORMAT(bind_time,'%Y-%m-%d'),
		 * online_status, biz_status) b 
		 * where a.bind_date >= b.bind_date and
		 * a.online_status = b.online_status and a.biz_status = b.biz_status group by
		 * a.bind_date, a.online_status, a.biz_status order by a.bind_date asc;
		 */
		String sqlStr = "select a.bind_date, sum(b.count) as total ";
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ", a.dev_type ";
		}
		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += ", a.online_status, a.biz_status ";
		}
		sqlStr += " from (";
		sqlStr += this.createTotalSubSqlStr(sumType, parentOrgId);
		sqlStr += ") a join (";
		sqlStr += this.createTotalSubSqlStr(sumType, parentOrgId);
				
		sqlStr += ") b where a.bind_date >= b.bind_date ";
		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += " and a.dev_type = b.dev_type ";
		}
		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += " and a.online_status = b.online_status and a.biz_status = b.biz_status ";
		}
		sqlStr += " group by a.bind_date ";

		if (sumType.contains(SUM_IN_TYPE)) {
			sqlStr += ",a.dev_type ";
		}		
		if (sumType.contains(SUM_IN_STATUS)) {
			sqlStr += ",a.online_status, a.biz_status ";
		}
		sqlStr += " order by a.bind_date asc";
		return sqlStr;
	}

	@Override
	public List<DevDto> summary(String sumType, String parentOrgId) {
		String sqlStr = this.createSqlStr(sumType, parentOrgId);
		logger.info("dev summary sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return tDevMapper.summary(map);
	}

	@Override
	public Condition createGetDevListCondition(String onlineStatus, String bizStatus, String parentOrgId) {
		Condition condition = new Condition(Org.class);
		onlineStatus = onlineStatus.trim();
		bizStatus = bizStatus.trim();
		parentOrgId = parentOrgId.trim();

		condition.createCriteria().andCondition("1=1");
		if (!onlineStatus.isEmpty()) {
			onlineStatus = "'" + onlineStatus + "'";
			condition.and(condition.createCriteria().andCondition("online_status=" + onlineStatus));
		}
		if (!bizStatus.isEmpty()) {
			// bizStatus的类型是int(11)，不用加单引号
			condition.and(condition.createCriteria().andCondition("biz_status=" + bizStatus));
		}
		if (!parentOrgId.isEmpty()) {
			List<String> orgIdList = orgPeersService.getAllSubOrgIds(parentOrgId);
			condition.and(condition.createCriteria().andIn("orgId", orgIdList));
		}

		return condition;
	}

	@Override
	public List<DevDto> total(String sumType, String parentOrgId) {
		String sqlStr = this.createTotalSqlStr(sumType, parentOrgId);
		logger.info("dev total sql string: " + sqlStr);

		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		return tDevMapper.summary(map);
	}
	public List<Dev> createRandomItems(int count) {
		String[] bindDates = { "2019-01-01", "2019-01-02", "2019-01-03", "2019-01-04", "2019-01-05" };
        String[] onlineStatus = { "online", "offline" };
        int[] bizStatus = { 1, 2 };

	    double minLongitude = 108.47;
	    double maxLongitude = 114.15;
	    double minLatitude = 24.38;
	    double maxLatitude = 30.08;

	    String devType = "数字网络功放";
        String softwareVersion = "1";
        String hardwareVersion = "1";
        String productType = "CT-IP350Z-SJZ 成品（康通）石家庄定制，黑色机箱，银白色面板，350W输出，带SD卡，带开路检测";
        Byte volume = 6;
        String mac = "C8-AA-55-03-18-2B";
        boolean enabled = true;
        String orgId = "40b41c92f24b11e8a35b0242ac110006";
        String systemId = "school10001";
       
        String updateTime = "2019-01-18T19:25:35.000+0000";
        String bizContent = "父亲写的散文诗.aac";
        int hardwareStatus = 0;
        boolean deleted = false;

		Random random = new Random();
		List<Dev> list = new ArrayList<>();
		int i = 0;
		while (i < count) {
			i++;
			Dev dev = new Dev();

			dev.setDevId(MyUUID.randomUUID().substring(0, 31));
			String name = MyUUID.randomUUID().substring(0, 31);
			dev.setDevName(name);
			dev.setDevSn(name);

			int idx = random.nextInt(onlineStatus.length);
			dev.setOnlineStatus(onlineStatus[idx]);

			idx = random.nextInt(bizStatus.length);
			dev.setBizStatus(bizStatus[idx]);
			Double longitude = minLongitude + random.nextDouble() * (maxLongitude - minLongitude);
			dev.setLongitude(longitude.toString());
			Double latitude = minLatitude + random.nextDouble() * (maxLatitude - minLatitude);
			dev.setLatitude(latitude.toString());
			
			idx = random.nextInt(bindDates.length);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = ft.parse(bindDates[idx]);
				dev.setBindTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dev.setDevType(devType);
			dev.setSoftwareVersion(softwareVersion);
			dev.setHardwareVersion(hardwareVersion);
			dev.setOrgId(orgId);
			dev.setSystemId(systemId);
			dev.setVolume(volume);
			dev.setMac(mac);
			dev.setHardwareStatus(hardwareStatus);
			dev.setProductType(productType);
			dev.setEnabled(enabled);
			dev.setDeleted(deleted);
			dev.setBizContent(bizContent);
			
			try {
				Date uTime = ft.parse(updateTime);
				dev.setUpdateTime(uTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			list.add(dev);
		}

		return list;
	}

	public String createBatchAddSqlStr(List<Dev> list) {
		String sqlStr = "insert into t_dev(dev_id, dev_name, dev_sn, dev_type,";
		sqlStr += " org_id, system_id, longitude, latitude, online_status, biz_status, bind_time) VALUES ";
		if (list.size() == 0) {
			return sqlStr + "()";
		}
		boolean first = true;

		for (Dev s : list) {
			if (first) {
				first = false;
				sqlStr += "(";
			} else {
				sqlStr += ",(";
			}
			sqlStr += "'" + s.getDevId() + "'";
			sqlStr += ",'" + s.getDevName() + "'";
			sqlStr += ",'" + s.getDevSn() + "'";
			sqlStr += ",'" + s.getDevType() + "'";
			sqlStr += ",'" + s.getOrgId() + "'";
			sqlStr += ",'" + s.getSystemId() + "'";
			sqlStr += ",'" + s.getLongitude() + "'";
			sqlStr += ",'" + s.getLatitude() + "'";
			sqlStr += ",'" + s.getOnlineStatus() + "'";
			sqlStr += ",'" + s.getBizStatus() + "'";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sqlStr += ",'" + sdf.format(s.getBindTime()) + "'";
			sqlStr += ")";
		}

		return sqlStr;
	}
	@Override
	public void batchAdd(int count) {
		List<Dev> list = this.createRandomItems(count);
		String sqlStr = this.createBatchAddSqlStr(list);
		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);

		tDevMapper.batchAdd(map);
	}

}
