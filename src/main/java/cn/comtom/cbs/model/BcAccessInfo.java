package cn.comtom.cbs.model;

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
public class BcAccessInfo {
    /**
     * 广播信息ID
     */
    @Id
    @Column(name = "infoId")
    private String infoid;

    /**
     * ebd关联ID
     */
    @Column(name = "ebdId")
    private String ebdid;

    /**
     * 关联的消息id（接入信息唯一标识）
     */
    @Column(name = "relatedEbmId")
    private String relatedebmid;

    /**
     * relatedEbIId
     */
    @Column(name = "relatedEbIId")
    private String relatedebiid;

    /**
     * 接入节点ID
     */
    @Column(name = "nodeId")
    private String nodeid;

    /**
     * 发布时间
     */
    @Column(name = "sendTime")
    private Date sendtime;

    /**
     * 事件类型编码
     */
    @Column(name = "eventType")
    private String eventtype;

    /**
     * 事件级别
     */
    private Byte severity;

    /**
     * 播发开始时间
     */
    @Column(name = "startTime")
    private Date starttime;

    /**
     * 播发结束时间
     */
    @Column(name = "endTime")
    private Date endtime;

    /**
     * 信息类型（1:应急 2:日常 3:演练）
     */
    @Column(name = "infoType")
    private Integer infotype;

    /**
     * 信息标题
     */
    @Column(name = "infoTitle")
    private String infotitle;

    /**
     * 信息级别（1,2,3,4,10）
     */
    @Column(name = "infoLevel")
    private Integer infolevel;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 语种代码
     */
    @Column(name = "msgLanguageCode")
    private String msglanguagecode;

    /**
     * 审核结果(0:未审核 1:审核通过 2:审核不通过)
     */
    @Column(name = "auditResult")
    private Byte auditresult;

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
     * 审核人
     */
    private String auditor;

    /**
     * 信息内容
     */
    @Column(name = "infoContent")
    private String infocontent;
}