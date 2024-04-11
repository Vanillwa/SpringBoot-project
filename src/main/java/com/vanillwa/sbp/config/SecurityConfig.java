package com.vanillwa.sbp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vanillwa.sbp.auth.UserAuthProvider;
import com.vanillwa.sbp.auth.UserPrincipalDetailsService;
import com.vanillwa.sbp.auth.handler.UserAccessDeniedHandler;
import com.vanillwa.sbp.auth.handler.UserAuthFailureHandler;
import com.vanillwa.sbp.auth.handler.UserAuthSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	final UserAuthProvider userAuthProvider;
	final UserPrincipalDetailsService userPrincipalDetailsService;
	final UserAuthSuccessHandler userAuthSuccessHandler;
	final UserAuthFailureHandler userAuthFailureHandler;
	final UserAccessDeniedHandler userAccessDeniedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable()).headers((headers) -> headers.frameOptions((options) -> options.disable()))
				.authorizeHttpRequests((request) -> request.requestMatchers("/post/create", "/post/list/{postId}/update", "/user/mypage").authenticated()
						.requestMatchers("/static/**", "/css/**", "/images/**", "/js/**", "/auth/**", "/", "/post/list",
								"/error", "/api/**", "/user/**")
						.permitAll().anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin.loginPage("/auth/login").usernameParameter("username")
						.passwordParameter("password").loginProcessingUrl("/auth/login-proc")
						.successHandler(userAuthSuccessHandler).failureHandler(userAuthFailureHandler).permitAll())
				.logout((logout) -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID"))
				.userDetailsService(userPrincipalDetailsService)
				.exceptionHandling(exception -> exception.accessDeniedHandler(userAccessDeniedHandler));

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
