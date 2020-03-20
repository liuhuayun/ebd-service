package cn.comtom.cbs.dto.ebm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EbrQueryRequest implements Serializable {

    private String ebrId;

    private String ebrName;

    private String ebrType;

    private List<String> areaCodes;

    private String ebrState;

    private String areaCode;

    private String ebrLevel;


    private String localAreaCode;

}
