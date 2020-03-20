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
public class BcEbrPlatform {
    /**
     * 平台ID
     */
    @Id
    @Column(name = "psEbrId")
    private String psebrid;

    /**
     * 平台网络地址
     */
    @Column(name = "psUrl")
    private String psurl;

    /**
     * 平台名称
     */
    @Column(name = "psEbrName")
    private String psebrname;

    /**
     * 平台状态（1:运行2:停止3:故障4:故障恢复）
     */
    @Column(name = "psState")
    private Byte psstate;

    /**
     * 平台所属区域编码
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 资源类型(大类) 01-应急广播平台；02-广播电视频率频道播出系统；03-传输覆盖台站/前端
     */
    @Column(name = "psType")
    private String pstype;

    /**
     * 资源类型(小类)
     */
    @Column(name = "resType")
    private String restype;

    /**
     * 资源子类型
     */
    @Column(name = "subResType")
    private String subrestype;

    /**
     * 父平台资源编号
     */
    @Column(name = "parentPsEbrId")
    private String parentpsebrid;

    /**
     * 平台地址
     */
    @Column(name = "psAddress")
    private String psaddress;

    /**
     * 联系人名称
     */
    private String contact;

    /**
     * 联系人电话
     */
    @Column(name = "phoneNumber")
    private String phonenumber;

    /**
     * 平台经度
     */
    private String longitude;

    /**
     * 平台纬度
     */
    private String latitude;

    /**
     * 平台级别
     */
    @Column(name = "platLevel")
    private Byte platlevel;

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
     * 覆盖面积
     */
    private BigDecimal square;

    /**
     * 覆盖人口
     */
    private BigDecimal population;

    @Column(name = "statusSyncFlag")
    private Integer statussyncflag;

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

    private String remark;
}