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
public class BcEbm {
    /**
     * 广播消息Id
     */
    @Id
    @Column(name = "ebmId")
    private String ebmid;

    /**
     * 广播消息协议版本号
     */
    @Column(name = "ebmVersion")
    private String ebmversion;

    /**
     * 关联广播消息Id
     */
    @Column(name = "relatedEbmId")
    private String relatedebmid;

    /**
     * 关联广播信息Id
     */
    @Column(name = "relatedEbIId")
    private String relatedebiid;

    /**
     * 消息类型
     */
    @Column(name = "msgType")
    private Integer msgtype;

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
    @Column(name = "msgLanguageCode")
    private String msglanguagecode;

    /**
     * 消息标题文本
     */
    @Column(name = "msgTitle")
    private String msgtitle;

    /**
     * 覆盖区域编码
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 参考业务节目Id
     */
    @Column(name = "programNum")
    private Integer programnum;

    /**
     * 0:未处理，1：等待播发，2：播发中，3：播发成功，4：播发失败，5：播发取消
     */
    @Column(name = "broadcastState")
    private Byte broadcaststate;

    /**
     * 消息状态(1:创建 2:发送成功 3:发送失败 4:取消)
     */
    @Column(name = "ebmState")
    private Byte ebmstate;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 发送标志（1：接收 2：发送）
     */
    @Column(name = "sendFlag")
    private Byte sendflag;

    /**
     * 用于标记该条消息是否超过开始时间(1: 未超过；2：已超过;)
     */
    @Column(name = "timeOut")
    private Byte timeout;

    /**
     * 审核结果
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
     * 审核人
     */
    @Column(name = "auditUser")
    private String audituser;

    @Column(name = "schemeId")
    private String schemeid;

    @Column(name = "flowId")
    private String flowid;

    /**
     * 接入状态，1：未到时间，2：已到时未过期，3：已过期
     */
    @Column(name = "accessState")
    private Byte accessstate;

    /**
     * 对应音频文件路径
     */
    @Column(name = "audioPath")
    private String audiopath;

    /**
     * 消息内容文本
     */
    @Column(name = "msgDesc")
    private String msgdesc;
}