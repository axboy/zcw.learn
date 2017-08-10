package local.zcw.demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 作者 zcw
 * 时间 2017/8/10 19:21
 * 描述 TODO
 */
@RestController
@RequestMapping("/")
class HomeController {

    @RequestMapping("/")
    fun index() = "SUCCESS"
}