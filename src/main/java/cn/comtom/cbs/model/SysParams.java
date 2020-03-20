package cn.comtom.cbs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/08/13
* @author William 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysParams {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 参数名
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 参数键
     */
    @Column(name = "param_key")
    private String paramKey;

    /**
     * 参数值
     */
    @Column(name = "param_value")
    private String paramValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 序号
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 1:界面显示 2：业务参数 3：平台配置 4：路径配置 5：审核配置
     */
    @Column(name = "param_type")
    private Integer paramType;
}