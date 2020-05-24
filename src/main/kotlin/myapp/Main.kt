package myapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("infrastructure")
class Main

fun main(args: Array<String>) {
	runApplication<Main>(*args)
}
