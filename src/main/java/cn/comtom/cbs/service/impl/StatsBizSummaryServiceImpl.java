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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.StatsBizSummaryMapper;
import cn.comtom.cbs.dto.StatsBizSummaryDto;
import cn.comtom.cbs.model.StatsBizSummary;
import cn.comtom.cbs.service.StatsBizSummaryService;
import cn.comtom.cbs.utils.MyUUID;

/**
 * StatsBizSummary Service实现类，Created on 2018-12-31.
 * 
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatsBizSummaryServiceImpl extends AbstractService<StatsBizSummary> implements StatsBizSummaryService {
	@Resource
	private StatsBizSummaryMapper statsBizSummaryMapper;

	@Override
	public List<StatsBizSummaryDto> findByDayBizStatus(String beginDate, String endDate, String orgId) {
		return statsBizSummaryMapper.findByDayBizStatus(beginDate, endDate, orgId);
	}

	public List<StatsBizSummary> createRandomItems(int count) {
		// 长沙；株州；湘潭；衡阳；邵阳
		String[] orgIds = { "40a99ff0f24b11e8a35b0242ac110006", "40be5a56f24b11e8a35b0242ac110006",
				"40cf2167f24b11e8a35b0242ac110006", "40db12c3f24b11e8a35b0242ac110006",
				"40fcbeeaf24b11e8a35b0242ac110006" };
		String[] bizDates = { "2019-01-01", "2019-01-02", "2019-01-03", "2019-01-04", "2019-01-05" };
		Random random = new Random();
		List<StatsBizSummary> list = new ArrayList<>();
		int i = 0;
		while (i < count) {
			i++;
			StatsBizSummary statsBizSummary = new StatsBizSummary();

			statsBizSummary.setSummaryId(MyUUID.randomUUID().substring(0, 31));

			int idx = random.nextInt(orgIds.length);
			statsBizSummary.setOrgId(orgIds[idx]);

			idx = random.nextInt(bizDates.length);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = ft.parse(bizDates[idx]);
				statsBizSummary.setBizDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statsBizSummary.setLiveQuantity(random.nextInt(9999 - 1000 + 1) + 1000);
			statsBizSummary.setScheduleQuantity(random.nextInt(9999 - 1000 + 1) + 1000);
			list.add(statsBizSummary);
		}

		return list;
	}

	public String createSqlStr(List<StatsBizSummary> list) {
		String sqlStr = "insert into stats_biz_summary(summary_id, org_id, biz_date, schedule_quantity, live_quantity) VALUES ";
		if (list.size() == 0) {
			return sqlStr + "()";
		}
		boolean first = true;

		for (StatsBizSummary s : list) {
			if (first) {
				first = false;
				sqlStr += "(";
			} else {
				sqlStr += ",(";
			}
			sqlStr += "'" + s.getSummaryId() + "'";
			sqlStr += ",'" + s.getOrgId() + "'";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sqlStr += ",'" + sdf.format(s.getBizDate()) + "'";
			sqlStr += "," + s.getScheduleQuantity();
			sqlStr += "," + s.getLiveQuantity();
			sqlStr += ")";
		}

		return sqlStr;
	}

	@Override
	public void batchAdd(int count) {
		List<StatsBizSummary> list = this.createRandomItems(count);
		String sqlStr = this.createSqlStr(list);
		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);

		statsBizSummaryMapper.batchAdd(map);
	}

}
