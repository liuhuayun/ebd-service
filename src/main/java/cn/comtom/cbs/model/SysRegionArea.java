package cn.comtom.cbs.model;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/06/19
* @author William 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRegionArea {
    /**
     * 区域编码
     */
    @Id
    @Column(name = "areaCode")
    private String areacode;

    /**
     * 区域名称
     */
    @Column(name = "areaName")
    private String areaname;

    /**
     * 上级区域
     */
    @Column(name = "parentAreaCode")
    private String parentareacode;

    /**
     * 区域级别(省、市、县...）
     */
    @Column(name = "areaLevel")
    private Integer arealevel;

    /**
     * 区域面积， 单位平方公里
     */
    @Column(name = "areaSquare")
    private BigDecimal areasquare;

    /**
     * 区域人口， 单位万
     */
    @Column(name = "areaPopulation")
    private BigDecimal areapopulation;

    /**
     * 应急中心经度
     */
    @Column(name = "centerLongitude")
    private String centerlongitude;

    /**
     * 应急中心纬度
     */
    @Column(name = "centerLatitude")
    private String centerlatitude;
}