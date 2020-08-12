package com.rulesengine.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder().encode("pass"))
                .roles("DOCTOR", "ADMIN")
                .and()
                .withUser("doctor")
                .password(encoder().encode("pass"))
                .roles("DOCTOR")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/rest/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/rest/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rest/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/rest/**").hasAnyRole("ADMIN", "DOCTOR")
                .and()
                .csrf().disable()
                .formLogin().disable()
    }

}