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
public class BcDispatchFlow {
    /**
     * 流程（会话）编号
     */
    @Id
    @Column(name = "flowId")
    private String flowid;

    /**
     * 流程阶段（1:预警触发2:预警响应3:预警处理4:预警完成）
     */
    @Column(name = "flowStage")
    private Byte flowstage;

    /**
     * 流程状态
     */
    @Column(name = "flowState")
    private Byte flowstate;

    /**
     * 关联节目编号
     */
    @Column(name = "relatedProgramId")
    private String relatedprogramid;

    /**
     * 关联业务数据包编号
     */
    @Column(name = "relatedEbdId")
    private String relatedebdid;

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
}