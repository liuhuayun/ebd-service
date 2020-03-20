package cn.comtom.cbs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/12/28
* @author William
*/
@Table(name = "t_org_peers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgPeers {
	@Id
    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "parent_id")
    private String parentId;
}