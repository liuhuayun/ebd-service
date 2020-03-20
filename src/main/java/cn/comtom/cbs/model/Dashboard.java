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
@Table(name = "t_dashboard")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 大屏名称
     */
    @Column(name = "dashboard_name")
    private String dashboardName;

    /**
     * 模块名称
     */
    @Column(name = "module_name")
    private String moduleName;

    /**
     * 模板ID
     */
    @Column(name = "tmpl_id")
    private Integer tmplId;

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