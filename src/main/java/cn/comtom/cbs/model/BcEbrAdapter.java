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
public class BcEbrAdapter {
    @Id
    @Column(name = "ebrAsId")
    private String ebrasid;

    /**
     * 适配器名称
     */
    @Column(name = "ebrAsName")
    private String ebrasname;

    /**
     * 资源类型(大类)
     */
    @Column(name = "asType")
    private String astype;

    /**
     * 所属区域编码
     */
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 资源级别
     */
    @Column(name = "platLevel")
    private Byte platlevel;

    @Column(name = "resType")
    private String restype;

    @Column(name = "subResType")
    private String subrestype;

    /**
     * 应急广播平台ID
     */
    @Column(name = "ebrpsId")
    private String ebrpsid;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 联系人
     */
    private String url;

    /**
     * 平台经度
     */
    private String longitude;

    /**
     * 平台纬度
     */
    private String latitude;

    /**
     * 关联台站ID
     */
    @Column(name = "ebrStId")
    private String ebrstid;

    /**
     * 适配器信息同步标识（1：未同步 2：已同步）
     */
    @Column(name = "syncFlag")
    private Byte syncflag;

    /**
     * 适配器状态同步标识（1：未同步 2：已同步）
     */
    @Column(name = "statusSyncFlag")
    private Byte statussyncflag;

    /**
     * 适配器状态（1:运行2:停止3:故障4:故障恢复）
     */
    @Column(name = "adapterState")
    private Byte adapterstate;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;
}