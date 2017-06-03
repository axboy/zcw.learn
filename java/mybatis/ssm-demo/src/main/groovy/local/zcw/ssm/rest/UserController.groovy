package local.zcw.ssm.rest

import local.zcw.ssm.domain.User
import local.zcw.ssm.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

/**
 * Created by zcw on 2017/06/03.
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Resource
    UserService userService

    @RequestMapping("/show")
    @ResponseBody
    User show(int id) {
        return userService.getUserById(id)
    }
}
