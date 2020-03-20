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
public class BcEbmBrdRecord {
    /**
     * 播发记录条目ID
     */
    @Id
    @Column(name = "brdItemId")
    private String brditemid;

    /**
     * 信息上报资源的ID
     */
    @Column(name = "resourceId")
    private String resourceid;

    /**
     * 下发最新消息ID
     */
    @Column(name = "newEbmId")
    private String newebmid;

    /**
     * 接收上级消息ID
     */
    @Column(name = "ebmId")
    private String ebmid;

    /**
     * 消息类型
     */
    @Column(name = "msgType")
    private Byte msgtype;

    /**
     * 发布机构名称
     */
    @Column(name = "sendName")
    private String sendname;

    /**
     * 发布机构编码
     */
    @Column(name = "senderCode")
    private String sendercode;

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
     * 语种代码
     */
    @Column(name = "languageCode")
    private String languagecode;

    /**
     * 消息标题文本
     */
    @Column(name = "msgTitle")
    private String msgtitle;

    /**
     * 消息内容文本
     */
    @Column(name = "msgDesc")
    private String msgdesc;

    /**
     * 覆盖区域编码
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 参考业务节目号
     */
    @Column(name = "programNum")
    private Integer programnum;

    /**
     * 播发状态码
     */
    @Column(name = "brdStateCode")
    private Byte brdstatecode;

    /**
     * 播发状态描述
     */
    @Column(name = "brdStateDesc")
    private String brdstatedesc;

    /**
     * 覆盖率
     */
    @Column(name = "coverageRate")
    private Double coveragerate;

    @Column(name = "coverageAreaCode")
    private String coverageareacode;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 记录是否已同步标识（1：未同步 2：已同步）
     */
    @Column(name = "syncFlag")
    private Byte syncflag;

    /**
     * 实际调用资源统计
     */
    @Column(name = "resBrdStat")
    private String resbrdstat;
}