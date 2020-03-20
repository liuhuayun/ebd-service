//encoding:utf-8
package cn.comtom.cbs.apitest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/**
 * 运行cucumber测试
 * @author william
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber.json", "pretty", "html:target/cucumber"}, 
				tags= {"@chart_crud"},features = "src/test/resources")
public class RunCucumberTests {
	
}
