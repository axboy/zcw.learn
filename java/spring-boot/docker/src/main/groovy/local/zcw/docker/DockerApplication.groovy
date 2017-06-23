package local.zcw.docker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class DockerApplication {

	static void main(String[] args) {
		SpringApplication.run DockerApplication, args
	}

	@RequestMapping("/")
	def index() {
		return [
				code: "SUCCESS"
		]
	}
}
