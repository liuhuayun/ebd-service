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
@Table(name = "t_dashboard_tmpl_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardTmplDetail {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 模板ID
     */
    @Column(name = "tmpl_id")
    private Integer tmplId;

    /**
     * 当前块左上角X坐标
     */
    private Integer x;

    /**
     * 当前块左上角Y坐标
     */
    private Integer y;

    /**
     * 当前块的宽度
     */
    private Integer w;

    /**
     * 当前块的高度
     */
    private Integer h;

    /**
     * 当前块的索引
     */
    private Integer i;

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