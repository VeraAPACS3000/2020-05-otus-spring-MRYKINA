package ru.otus.spring.homework12.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/resources/**");
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                //не сохраняю сессию между запросами, чтобы работал ремембе ми
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //здесь говорим какие страницы только через проверку аутентификацию
                .authorizeRequests().antMatchers("/listBooks").authenticated()
                .and()
                .authorizeRequests().antMatchers("/addNewBook").authenticated()
                .and()
                .authorizeRequests().antMatchers("/bookInfo/**").authenticated()
                .and()
                .formLogin()
                .failureForwardUrl("/error")
                .and()
                .logout()
                .and()
                .rememberMe()
                .key("keykeysecreet").tokenValiditySeconds(60);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
