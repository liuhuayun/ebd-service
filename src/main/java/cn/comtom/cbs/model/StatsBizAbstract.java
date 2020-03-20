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
@Table(name = "stats_biz_abstract")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsBizAbstract {
    /**
     * 业务动态ID
     */
    @Id
    private String id;

    /**
     * 业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 业务时间
     */
    @Column(name = "biz_time")
    private Date bizTime;

    /**
     * 业务摘要
     */
    @Column(name = "biz_abstract")
    private String bizAbstract;

    /**
     * 操作人
     */
    @Column(name = "op_user")
    private String opUser;

    /**
     * 区域ID
     */
    @Column(name = "org_id")
    private String orgId;
}