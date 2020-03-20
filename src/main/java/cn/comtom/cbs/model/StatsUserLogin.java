package cn.comtom.cbs.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gege
* Created by Mybatis Generator on 2018/12/30
*/
@Table(name = "stats_user_login")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsUserLogin {
    /**
     * 日活表
     */
	@Id
    @Column(name = "login_id")
    private String loginId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "login_date")
    private Date loginDate;

    /**
     * 客户端类型(WEB/IOS/ANDROID)
     */
    @Column(name = "client_type")
    private String clientType;
}