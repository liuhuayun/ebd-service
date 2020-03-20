package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gege
* Created by Mybatis Generator on 2018/12/31
*/
@Table(name = "stats_biz_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsBizSummary {
    /**
     * 统计ID
     */
	@Id
    @Column(name = "summary_id")
    private String summaryId;

    /**
     * 区域ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 业务日期
     */
    @Column(name = "biz_date")
    private Date bizDate;

    /**
     * 播单次数
     */
    @Column(name = "schedule_quantity")
    private Integer scheduleQuantity;

    /**
     * 喊话次数
     */
    @Column(name = "live_quantity")
    private Integer liveQuantity;
}