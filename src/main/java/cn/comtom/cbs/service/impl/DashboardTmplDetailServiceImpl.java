package cn.comtom.cbs.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.DashboardTmplDetailMapper;
import cn.comtom.cbs.model.DashboardTmplDetail;
import cn.comtom.cbs.service.DashboardTmplDetailService;


/**
 * DashboardTmplDetail Service实现类，Created on 2019-01-02.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardTmplDetailServiceImpl extends AbstractService<DashboardTmplDetail> implements DashboardTmplDetailService {
    @Resource
    private DashboardTmplDetailMapper tDashboardTmplDetailMapper;

    public String createSqlStr(List<DashboardTmplDetail> list) {
		String sqlStr = "insert into t_dashboard_tmpl_detail(tmpl_id, x, y, w, h, i, create_time, update_time) VALUES ";
		if(list.size() == 0) {
			return sqlStr + "()";
		}
		boolean first = true;
		
		for (DashboardTmplDetail s:list) {
			if (first) {
				first = false;
				sqlStr += "(";
			} else {
				sqlStr += ",(";
			}
			sqlStr += s.getTmplId();
			sqlStr += "," + s.getX();
			sqlStr += "," + s.getY();
			sqlStr += "," + s.getW();
			sqlStr += "," + s.getH();
			sqlStr += "," + s.getI();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sqlStr += ",'" + sdf.format(s.getCreateTime()) + "'";
			sqlStr += ",'" + sdf.format(s.getUpdateTime()) + "'";
			
			sqlStr += ")";			
		}
		
		return sqlStr;
	}
	@Override
	public void batchAdd(List<DashboardTmplDetail> list) {
		String sqlStr = this.createSqlStr(list);
		Map<String, String> map = new HashMap<>(4);
		map.put("sql", sqlStr);
		
		tDashboardTmplDetailMapper.batchAdd(map);
	}

}
