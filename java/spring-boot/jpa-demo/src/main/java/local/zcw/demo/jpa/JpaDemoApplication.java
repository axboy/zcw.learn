package local.zcw.demo.jpa;

import local.zcw.demo.jpa.domain.User;
import local.zcw.demo.jpa.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SpringBootApplication
@EnableJpaAuditing
public class JpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @RequestMapping("/testTx")
    public String testTx() {
        User user = userRepo.findOne(1L);
        user.setUsername("bbb");
        return "Success";
    }
}
