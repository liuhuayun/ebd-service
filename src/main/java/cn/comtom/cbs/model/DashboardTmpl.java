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
@Table(name = "t_dashboard_tmpl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardTmpl {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 模板名称
     */
    @Column(name = "tmpl_name")
    private String tmplName;

    /**
     * 块的个数
     */
    @Column(name = "block_num")
    private Integer blockNum;

    /**
     * 查询按钮所在块的索引
     */
    @Column(name = "search_position")
    private Integer searchPosition;

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