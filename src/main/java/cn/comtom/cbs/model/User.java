package cn.comtom.cbs.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.comtom.cbs.model.Dev.DevBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/12/28
* @author William
*/
@Table(name = "t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 用户ID
     */
	@Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

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
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 过期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 删除状态（0.否 1.是）
     */
    private Boolean deleted;

    /**
     * 是否启用（0.是1.否）
     */
    @Column(name = "enabled")
    private Boolean able;
    
   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}