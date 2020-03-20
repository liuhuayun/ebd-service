package cn.comtom.cbs.dto.ebr;

import java.util.List;

import cn.comtom.cbs.dto.ebm.EbrDto;
import lombok.Data;

@Data
public class EbrTypeDto {

    private String ebrTypeName;

    private String ebrType;
    
    private String resType;
    
    /**
           * 总数
     */
    private Long totalCount;
    /**
          * 运行数
	*/
	private Long runCount;

	/**
	 * 停止数
	*/
	private Long stopCount;
	
	/**
	 * 故障数
	*/
	private Long failureCount;
	
	/**
	 * 故障恢复数
	*/
	private Long failureRecCount;
    
    /**
	 *大喇叭管控下面的资源统计
	*/
    //private List<EbrTypeDto> ebrtypeDtoList;
    private EbrTypeDto ebrtypeDto;
    /**
   	 *具体资源
   	*/
    private List<EbrDto> ebrDtoList;
}
