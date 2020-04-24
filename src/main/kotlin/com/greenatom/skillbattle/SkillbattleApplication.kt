package com.greenatom.skillbattle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.greenatom.*"])
@EnableSwagger2
class SkillbattleApplication

fun main(args: Array<String>) {
	ApiContextInitializer.init()

	runApplication<SkillbattleApplication>(*args)
}
