/**
 * 
 */
package cn.comtom.cbs.dto;

import java.util.List;

import cn.comtom.cbs.model.ChartConfig;
import lombok.Data;

/**
 * @author william
 *
 */
@Data
public class ChartDataDto {
	private ChartConfig chartConfig;
	private List<?> chartData;
	
}
