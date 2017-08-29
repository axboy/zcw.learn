package local.zcw.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作者 zcw
 * 时间 2017/8/29 17:05
 * 描述 TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml", "classpath*:spring-mybatis.xml"})
public class BaseTest {

    @Test
    public void test() {
        System.out.println("test");
    }
}
