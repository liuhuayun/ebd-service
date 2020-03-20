/**
 * 
 */
package cn.comtom.cbs.unittest;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.Application;

/**
 * 单元测试继承该类即可
 * @author william
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional(rollbackFor = Exception.class)
@Rollback
public abstract class BaseUnitTester {
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
