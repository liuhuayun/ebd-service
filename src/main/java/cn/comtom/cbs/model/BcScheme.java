package cn.comtom.cbs.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/08/27
* @author William 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BcScheme {
    /**
     * 调度方案编号
     */
    @Id
    @Column(name = "schemeId")
    private String schemeid;

    /**
     * 方案标题
     */
    @Column(name = "schemeTitle")
    private String schemetitle;

    /**
     * 生成时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 关联会话编号
     */
    @Column(name = "flowId")
    private String flowid;

    /**
     * 覆盖面积
     */
    @Column(name = "totalArea")
    private BigDecimal totalarea;

    /**
     * 覆盖人口
     */
    @Column(name = "totalPopu")
    private BigDecimal totalpopu;

    /**
     * 覆盖区域百分比
     */
    @Column(name = "areaPercent")
    private BigDecimal areapercent;

    /**
     * 覆盖人口百分比
     */
    @Column(name = "popuPercent")
    private BigDecimal popupercent;

    /**
     * 审核结果(0:未审核 1:审核通过 2:审核不通过)
     */
    @Column(name = "auditResult")
    private Integer auditresult;

    /**
     * 审核意见
     */
    @Column(name = "auditOpinion")
    private String auditopinion;

    /**
     * 审核时间
     */
    @Column(name = "auditTime")
    private Date audittime;

    /**
     * 审核人员
     */
    @Column(name = "auditUser")
    private String audituser;

    /**
     * 方案状态(1:新建 2:提交 3:取消)
     */
    private Byte state;

    /**
     * 关联消息编号
     */
    @Column(name = "ebmId")
    private String ebmid;

    /**
     * 关联节目Id
     */
    @Column(name = "programId")
    private String programid;

    /**
     * 关联数据包Id
     */
    @Column(name = "ebdId")
    private String ebdid;

    /**
     * 关联信息Id
     */
    @Column(name = "infoId")
    private String infoid;

    @Column(name = "schemeType")
    private Integer schemetype;

    /**
     * 资源覆盖人口
     */
    @Column(name = "ebrPopu")
    private BigDecimal ebrpopu;

    /**
     * 资源覆盖区域
     */
    @Column(name = "ebrArea")
    private BigDecimal ebrarea;

    /**
     * 匹配的预案ID
     */
    @Column(name = "planId")
    private String planid;
}