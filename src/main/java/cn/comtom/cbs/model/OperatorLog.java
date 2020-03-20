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
@Table(name = "t_operator_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperatorLog {
    /**
     * 主键
     */
    @Id
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
     * 模块名称
     */
    private String module;

    /**
     * 操作标题
     */
    private String operation;

    /**
     * 操作用户姓名
     */
    @Column(name = "o_name")
    private String oName;

    /**
     * 操作用户账号
     */
    @Column(name = "o_account")
    private String oAccount;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * IP归属地
     */
    @Column(name = "ip_city")
    private String ipCity;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 日志类型
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 浏览器名称
     */
    @Column(name = "browser_name")
    private String browserName;

    /**
     * 操作详细 Json数据 包括路径，请求方式，入参
     */
    @Column(name = "o_context")
    private String oContext;
}
