package cn.comtom.cbs.dto.ebm;

import java.io.Serializable;
import java.util.List;

import cn.comtom.cbs.model.BcEbm;
import lombok.Data;

@Data
public class EbmInfoAllDto extends BcEbm implements Serializable {

   /**
              * 本平台的经纬度
    * */ 
	private String longitude;

    private String latitude;
    
    /**
	     * 消息发送者的经纬度
	 * */ 
	private String pLongitude;

    private String platitude;

    /**
                 * 消息下发资源
     */
    private List<EbrDto> ebrDtoList ;
    /**
     * 流程状态
     */
    private Byte flowstage;

    /**
     * 流程状态
     */
    private Byte flowstate;
    
    /**
     * 区域名称
     */
    private List<String> areaNames;

    /**
     * 本级平台资源id
     */
    private String ebrId;
}
