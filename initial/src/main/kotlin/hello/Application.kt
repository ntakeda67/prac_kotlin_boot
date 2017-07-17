package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(Application::class.java, *args)

    System.out.println("Let's inspect the beans provided by Spring Boot:")

    val beanNames = ctx.getBeanDefinitionNames()

    for (beanName in beanNames) {
        System.out.println(beanName)
    }
}