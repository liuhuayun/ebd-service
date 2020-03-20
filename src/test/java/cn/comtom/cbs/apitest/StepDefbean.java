package cn.comtom.cbs.apitest;
/**
 * 
 */

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import net.sf.json.JSONObject;

/**
 * cucumber步骤定义的基类
 * 
 * @author cailitian
 * 不加ContextConfiguration注解，bean会注入不进去
 * 不加SpringBootTest注解会找不到bean
 */
@ContextConfiguration  
@SpringBootTest  
public class StepDefbean {
	protected JSONObject res = null;
}
