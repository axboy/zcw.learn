package local.zcw.cors.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/test")
@SpringBootApplication
public class Server1Application {

    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class, args);
    }

    @GetMapping("/get1")
    public Map get1() {
        System.out.println("server1.get1 ok");
        return Collections.singletonMap("result", "server1.get1 ok");
    }
}
