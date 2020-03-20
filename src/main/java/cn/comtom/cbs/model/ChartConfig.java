package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/01/01
* @author William
*/
@Table(name = "t_chart_config")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartConfig {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 图表配置名称
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 模块名称
     */
    @Column(name = "module_name")
    private String moduleName;

    /**
     * 接口名称
     */
    @Column(name = "api_name")
    private String apiName;

    /**
     * 图表类型
     */
    @Column(name = "chart_type")
    private String chartType;

    /**
     * 图表名称
     */
    @Column(name = "chart_name")
    private String chartName;

    /**
     * 统计周期
     */
    @Column(name = "period")
    private String period;
    
    /**
     * 维度
     */
    private String dimension;

    /**
     * 维度名称
     */
    @Column(name = "dim_name")
    private String dimName;

    /**
     * 性能指标
     */
    @Column(name = "perf_index")
    private String perfIndex;

    /**
     * 性能指标名称
     */
    @Column(name = "index_name")
    private String indexName;

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