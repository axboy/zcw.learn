package cn.wazitang.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zcw on 2016/12/08.
 */
@RestController
@SpringBootApplication
public class Application {

    @RequestMapping("/")
    public String index() {
        return "OK";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

}
