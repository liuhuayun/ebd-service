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
@Table(name = "t_dev_status_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevStatusLog {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 所属系统
     */
    @Column(name = "sys_id")
    private String sysId;

    /**
     * 所属组织
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 设备序列号
     */
    @Column(name = "dev_sn")
    private String devSn;

    /**
     * 设备名称
     */
    @Column(name = "dev_name")
    private String devName;

    /**
     * Offline(0),Idle(1),TaskPlay(2),Upgrade(3),Fault(4)
     */
    @Column(name = "biz_status")
    private Byte bizStatus;

    @Column(name = "hard_status")
    private Integer hardStatus;

    /**
     * 文件名、直播
     */
    private String content;

    /**
     * 记录时间
     */
    @Column(name = "record_time")
    private Date recordTime;

    @Column(name = "work_type")
    private Byte workType;

    @Column(name = "work_uuid")
    private String workUuid;
}