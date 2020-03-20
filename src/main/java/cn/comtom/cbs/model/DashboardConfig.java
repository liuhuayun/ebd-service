package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/01/02
* @author William
*/
@Table(name = "t_dashboard_config")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardConfig {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 大屏ID
     */
    @Column(name = "dashboard_id")
    private Integer dashboardId;

    /**
     * 块的索引
     */
    @Column(name = "block_index")
    private Integer blockIndex;

    /**
     * 对应的图表配置ID
     */
    @Column(name = "config_id")
    private Integer configId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}