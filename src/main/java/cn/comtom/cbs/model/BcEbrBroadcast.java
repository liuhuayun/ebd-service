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
public class BcEbrBroadcast {
    /**
     * 播出系统ID
     */
    @Id
    @Column(name = "bsEbrId")
    private String bsebrid;

    /**
     * 播出系统名称
     */
    @Column(name = "bsName")
    private String bsname;

    /**
     * 播出系统URL
     */
    @Column(name = "bsUrl")
    private String bsurl;

    @Column(name = "subResType")
    private String subrestype;

    /**
     * 资源级别
     */
    @Column(name = "platLevel")
    private Byte platlevel;

    /**
     * 播出系统类型
     */
    @Column(name = "bsType")
    private String bstype;

    /**
     * 应急广播资源类型
     */
    @Column(name = "resType")
    private String restype;

    /**
     * 播出系统经度
     */
    private String longitude;

    /**
     * 播出系统纬度
     */
    private String latitude;

    /**
     * 播放系统覆盖面积
     */
    private BigDecimal square;

    /**
     * 覆盖区域
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 覆盖人口
     */
    private BigDecimal population;

    /**
     * 原播语种
     */
    @Column(name = "languageCode")
    private String languagecode;

    /**
     * 机房名称
     */
    @Column(name = "equipRoom")
    private String equiproom;

    /**
     * 电台频道名称
     */
    @Column(name = "radioChannelName")
    private String radiochannelname;

    /**
     * 电台频道频率
     */
    @Column(name = "radioFreq")
    private Long radiofreq;

    /**
     * 电台发射功率
     */
    @Column(name = "radioPower")
    private Integer radiopower;

    /**
     * 是否是备机
     */
    private Boolean backup;

    /**
     * 是否自动切换备机
     */
    @Column(name = "autoSwitch")
    private Boolean autoswitch;

    /**
     * 能否遥控开机
     */
    @Column(name = "remoteControl")
    private Boolean remotecontrol;

    /**
     * 实验/覆盖发射
     */
    private Boolean experiment;

    /**
     * 电视台频道名称
     */
    @Column(name = "tvChannelName")
    private String tvchannelname;

    /**
     * 电视台频道频率
     */
    @Column(name = "tvFreq")
    private Integer tvfreq;

    /**
     * 节目号
     */
    @Column(name = "programNum")
    private String programnum;

    /**
     * 频道号
     */
    @Column(name = "tvChannelNum")
    private String tvchannelnum;

    /**
     * 播出系统状态（1:运行2:停止3:故障4:故障恢复）
     */
    @Column(name = "bsState")
    private Boolean bsstate;

    /**
     * 关联平台资源编码
     */
    @Column(name = "relatedPsEbrId")
    private String relatedpsebrid;

    /**
     * 关联台站资源编码
     */
    @Column(name = "relatedRsEbrId")
    private String relatedrsebrid;

    /**
     * 关联适配器资源编码
     */
    @Column(name = "relatedAsEbrId")
    private String relatedasebrid;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 是否已同步标识（1：未同步 2：已同步）
     */
    @Column(name = "syncFlag")
    private Byte syncflag;

    /**
     * 播出系统状态是否已同步标识（1：未同步 2：已同步）
     */
    @Column(name = "statusSyncFlag")
    private Byte statussyncflag;

    /**
     * 归属地
     */
    private String belong;

    /**
     * 单位名称
     */
    @Column(name = "orgName")
    private String orgname;

    /**
     * 单位类型
     */
    @Column(name = "orgType")
    private String orgtype;

    /**
     * 备注
     */
    private String remark;
}