package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/12/28
* @author William
*/
@Table(name = "t_dev")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dev {
    /**
     * 设备uuid
     */
    @Id
    @Column(name = "dev_id")
    private String devId;

    /**
     * 设备名称
     */
    @Column(name = "dev_name")
    private String devName;

    /**
     * 终端序列号
     */
    @Column(name = "dev_sn")
    private String devSn;

    @Column(name = "dev_type")
    private String devType;

    /**
     * 终端版本号
     */
    @Column(name = "software_version")
    private String softwareVersion;

    /**
     * 硬件版本号
     */
    @Column(name = "hardware_version")
    private String hardwareVersion;

    /**
     * 产品型号
     */
    @Column(name = "product_type")
    private String productType;

    /**
     * 终端音量
     */
    private Byte volume;

    /**
     * 设备IP
     */
    @Column(name = "dev_ip")
    private String devIp;

    /**
     * 设备MAC地址
     */
    private String mac;

    /**
     * 服务器IP
     */
    @Column(name = "nat_ip")
    private String natIp;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 启用、停用
     */
    private Boolean enabled;

    /**
     * 所属组织
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 所属系统
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * 绑定时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 在线状态
     */
    @Column(name = "online_status")
    private String onlineStatus;

    /**
     * 业务状态(1.BIZSTATUS_IDEL,2.BIZSTATUS_PLAY,3.BIZSTATUS_UPGRADE)
     */
    @Column(name = "biz_status")
    private Integer bizStatus;

    /**
     * 业务内容
     */
    @Column(name = "biz_content")
    private String bizContent;

    /**
     * 硬件状态
     */
    @Column(name = "hardware_status")
    private Integer hardwareStatus;

    /**
     * 删除状态
     */
    private Boolean deleted;

    @Column(name = "dn_type")
    private Boolean dnType;

    /**
     * 维护人
     */
    @Column(name = "maintain_by")
    private String maintainBy;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 联系地址
     */
    private String address;
}