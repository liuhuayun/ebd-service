package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/06/19
* @author William 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    /**
     * ID(UUID)
     */
    @Id
    private String id;

    /**
     * ???????ID
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * ????
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * ????
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * ????
     */
    @Column(name = "resource_code")
    private String resourceCode;

    /**
     * ????
     */
    @Column(name = "hardware_version")
    private String hardwareVersion;

    /**
     * ????
     */
    @Column(name = "software_version")
    private String softwareVersion;

    /**
     * ????
     */
    private String manufacturer;

    /**
     * ????: 0 - ???1 - ???2 - ????3- ??
     */
    private Integer status;

    /**
     * ??
     */
    private Integer volume;

    /**
     * ????
     */
    @Column(name = "fault_info")
    private String faultInfo;

    /**
     * ????
     */
    private String location;

    /**
     * ??
     */
    private String longitude;

    /**
     * ??
     */
    private String latitude;

    /**
     * ??
     */
    @Column(name = "image_urls")
    private String imageUrls;

    /**
     * FM???????0 - disabled, 1 - enabled
     */
    @Column(name = "fm_keep_mode")
    private Integer fmKeepMode;

    /**
     * FM????
     */
    @Column(name = "fm_frequency")
    private String fmFrequency;

    /**
     * IP??
     */
    @Column(name = "device_ip")
    private String deviceIp;

    /**
     * ????
     */
    @Column(name = "ip_mask")
    private String ipMask;

    /**
     * ??
     */
    private String gateway;

    /**
     * ????
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * ????
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * ????: 0 - terminal, 1 - adapter
     */
    @Column(name = "device_type")
    private Integer deviceType;

    /**
     * ???, ????????????
     */
    @Column(name = "sub_type")
    private String subType;

    /**
     * ????
     */
    @Column(name = "passback_mode")
    private Integer passbackMode;

    /**
     * ????
     */
    @Column(name = "passback_ip")
    private String passbackIp;

    /**
     * ????
     */
    @Column(name = "passback_period")
    private String passbackPeriod;

    /**
     * ?????0 - ????1 - ??
     */
    @Column(name = "power_amplifier")
    private Integer powerAmplifier;

    /**
     * 证书ID
     */
    @Column(name = "cert_id")
    private String certId;

    /**
     * 证书签名内容
     */
    @Column(name = "cert_content")
    private String certContent;

    /**
     * 适配器ID-只对终端有效
     */
    @Column(name = "adapter_id")
    private String adapterId;

    /**
     * 模块编号
     */
    @Column(name = "module_id")
    private String moduleId;

    /**
     * 证书信任列表数据
     */
    private String certh;

    /**
     * 区域编码
     */
    @Column(name = "area_code")
    private String areaCode;
}