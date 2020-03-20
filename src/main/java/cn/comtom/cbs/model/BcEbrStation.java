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
public class BcEbrStation {
    @Id
    @Column(name = "ebrStId")
    private String ebrstid;

    @Column(name = "resType")
    private String restype;

    /**
     * 资源级别
     */
    @Column(name = "platLevel")
    private Byte platlevel;

    /**
     * 所属区域编码
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 资源类型(大类)
     */
    @Column(name = "stType")
    private String sttype;

    /**
     * 台站（前端）名称
     */
    @Column(name = "ebrStName")
    private String ebrstname;

    @Column(name = "subResType")
    private String subrestype;

    /**
     * 关联应急广播平台ID
     */
    @Column(name = "ebrPsId")
    private String ebrpsid;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 台站地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
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
     * 台站信息同步标识（1：未同步 2：已同步）
     */
    @Column(name = "syncFlag")
    private Byte syncflag;

    /**
     * 台站状态同步标识（1：未同步 2：已同步）
     */
    @Column(name = "statusSyncFlag")
    private Byte statussyncflag;

    /**
     * 台站状态（1:运行2:停止3:故障4:故障恢复）
     */
    @Column(name = "stationState")
    private Byte stationstate;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;
}