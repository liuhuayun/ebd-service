/**
 * 
 */
package cn.comtom.cbs.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置文件，注意修改controller所在的包路径
 * @author william
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("cn.comtom.cbs.controller"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiInfo());
	    }
	
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("云广播项目api文档")
	                .description("restful 风格接口")
	                .version("1.0")
	                .build();
	    }

}
