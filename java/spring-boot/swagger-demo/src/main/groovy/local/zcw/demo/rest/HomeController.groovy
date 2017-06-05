package local.zcw.demo.rest

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

/**
 * Created by zcw on 2017/06/05.
 */
@RestController
@RequestMapping("/api/home")
@Api(
        tags = "home",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        protocols = "http,https",
        description = "home"
)
class HomeController {

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String index() {
        return "OK"
    }

    @RequestMapping(path = "/testIgnore",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiIgnore
    Map testIgnore(String value) {
        return [
                code : "SUCCESS",
                value: value
        ]
    }

    @RequestMapping(path = "/testObject",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User testObject(String name) {
        return new User(name: name, age: 23, sex: "man")
    }

    @RequestMapping(path = "/testPostObject",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User testPostObject(@RequestBody User user) {
        return user
    }

    @ApiModel
    static class User {
        @ApiModelProperty(value = "用户名")
        String name

        @ApiModelProperty(value = "年龄")
        int age

        @ApiModelProperty(value = "性别")
        String sex
    }
}
