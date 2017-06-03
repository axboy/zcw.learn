package local.zcw.ssm.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zcw on 2017/06/03.
 */
@RestController()
@RequestMapping("/home")
//FIXME 这里不需要/api/home
class HomeController {

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "OK"
    }

    @RequestMapping("/testMap")
    @ResponseBody
    Map testMap() {
        return [
                code: "SUCCESS",
                msg : "test"
        ]
    }
}
