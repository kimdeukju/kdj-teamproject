package org.spring.teamproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

        private final UserDetailSecurity userDetailSecurity;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

                http.csrf().disable(); //페이지 보안 설정 Exception 예외 처리 필수
                http.userDetailsService(userDetailSecurity);

                //권한설정
                http.authorizeHttpRequests()
                        .antMatchers("/","/item/main,/login,/join","board/main").permitAll()
                        .antMatchers("/member/**").authenticated()
                        .antMatchers("/member/**").hasAnyRole("MEMBER","ADMIN")
                        .antMatchers("/admin/**").hasAnyRole("ADMIN");

                //login&logout
                http.formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/loginOk")
                        .usernameParameter("username") //id정해지면
                        .passwordParameter("password")
                        .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/");

                return http.build();
        }



}
