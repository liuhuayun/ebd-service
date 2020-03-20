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
public class BcEbmDispatch {
    /**
     * 调用资源数据Id
     */
    @Id
    @Column(name = "dispatchId")
    private String dispatchid;

    /**
     * 语种代码
     */
    @Column(name = "languageCode")
    private String languagecode;

    /**
     * 应急广播平台Id
     */
    @Column(name = "psEbrId")
    private String psebrid;

    /**
     * 播出系统Id
     */
    @Column(name = "bsEbrId")
    private String bsebrid;

    @Column(name = "stEbrId")
    private String stebrid;

    /**
     * 适配器资源Id
     */
    @Column(name = "asEbrId")
    private String asebrid;

    /**
     * 播出系统类型
     */
    @Column(name = "brdSysType")
    private String brdsystype;

    /**
     * 播出系统信息
     */
    @Column(name = "brdSysInfo")
    private String brdsysinfo;

    /**
     * 消息匹配后生成的EBD数据包的ID
     */
    @Column(name = "matchEbdId")
    private String matchebdid;

    /**
     * 数据包Id
     */
    @Column(name = "ebdId")
    private String ebdid;

    /**
     * 关联EBM
     */
    @Column(name = "ebmId")
    private String ebmid;

    /**
     * 调度分发状态（0:待调度 1:已调度 2:调度失败）
     */
    private Byte state;

    /**
     * 调度分发时间
     */
    @Column(name = "dispatchTime")
    private Date dispatchtime;

    /**
     * 响应时间
     */
    @Column(name = "responseTime")
    private Date responsetime;

    /**
     * 失败次数
     */
    @Column(name = "failCount")
    private Integer failcount;

    /**
     * 播发时间
     */
    @Column(name = "playTime")
    private Date playtime;
}