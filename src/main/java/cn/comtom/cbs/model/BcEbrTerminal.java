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
public class BcEbrTerminal {
    /**
     * 终端资源编号
     */
    @Id
    @Column(name = "terminalEbrId")
    private Long terminalebrid;

    /**
     * 终端资源名称
     */
    @Column(name = "terminalEbrName")
    private String terminalebrname;

    /**
     * 关联平台编号
     */
    @Column(name = "relatedPsEbrId")
    private String relatedpsebrid;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 终端状态（1:运行2:停止3:故障4:故障恢复）
     */
    @Column(name = "terminalState")
    private Byte terminalstate;

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
     * 终端信息同步标识（1：未同步 2：已同步）
     */
    @Column(name = "syncFlag")
    private Byte syncflag;

    /**
     * 终端状态同步标识（1：未同步 2：已同步）
     */
    @Column(name = "statusSyncFlag")
    private Byte statussyncflag;
}