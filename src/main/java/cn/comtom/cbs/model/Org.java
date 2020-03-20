package cn.comtom.cbs.model;

import java.io.Serializable;
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
@Table(name = "t_org")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Org implements Serializable{
 
	private static final long serialVersionUID = 1L;

	/**
     * 组织ID
     */
	@Id
    @Column(name = "org_id")
    private String orgId;

    /**
     * 组织名称
     */
    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_code")
    private String orgCode;

    /**
     * 所属组织
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 所属系统
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * 是否为根节点（1.是 0.不是）
     */
    private Boolean rooted;

    /**
     * 是否为叶子节点（1.是 0.不是）
     */
    private Boolean leafed;

    /**
     * 组织联系人
     */
    private String contact;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 删除状态（1.是 0.否）
     */
    private Boolean deleted;
}