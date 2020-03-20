package cn.comtom.cbs.model;

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
public class BcSchemeEbr {
    /**
     * 方案资源关联编号
     */
    @Id
    private String id;

    /**
     * 关联资源编号
     */
    @Column(name = "ebrId")
    private String ebrid;

    /**
     * 关联调度方案编号
     */
    @Column(name = "schemeId")
    private String schemeid;

    /**
     * 资源类型
     */
    @Column(name = "ebrType")
    private String ebrtype;

    /**
     * 资源覆盖区域
     */
    @Column(name = "ebrArea")
    private String ebrarea;
}