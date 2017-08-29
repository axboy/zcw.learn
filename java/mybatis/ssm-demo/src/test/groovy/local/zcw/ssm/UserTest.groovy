package local.zcw.ssm

import local.zcw.ssm.rest.UserController
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * 作者 zcw
 * 时间 2017/8/29 17:08
 * 描述 TODO
 */
class UserTest extends BaseTest {

    @Autowired
    UserController userController

    @Test
    void show() {
        def user = userController.show(1)
        println user
    }
}
