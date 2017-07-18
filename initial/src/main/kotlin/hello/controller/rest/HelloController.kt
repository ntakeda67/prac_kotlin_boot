package hello.controller.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping


@RestController
class HelloController {

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot with Kotlin!"
    }

}
