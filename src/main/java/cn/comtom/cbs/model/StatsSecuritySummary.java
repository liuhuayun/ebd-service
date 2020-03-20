package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gege
* Created by Mybatis Generator on 2018/12/31
*/
@Table(name = "stats_security_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsSecuritySummary {
	@Id
    @Column(name = "security_id")
    private String securityId;

    /**
     * 病毒检测
     */
    @Column(name = "virus_detection")
    private String virusDetection;

    /**
     * 网络防御
     */
    @Column(name = "network_defense")
    private String networkDefense;

    /**
     * 功能服务
     */
    @Column(name = "functional_service")
    private String functionalService;

    /**
     * 证书授权
     */
    @Column(name = "certificate_authorities")
    private String certificateAuthorities;

    /**
     * 加解密机制
     */
    @Column(name = "encipherment_mechanisms")
    private String enciphermentMechanisms;

    /**
     * 扫描时间
     */
    @Column(name = "scan_time")
    private Date scanTime;

    /**
     * 操作人
     */
    @Column(name = "op_user")
    private String opUser;
}