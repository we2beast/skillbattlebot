package com.greenatom.skillbattle.core.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SecurityConfiguration : WebMvcConfigurer {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}
