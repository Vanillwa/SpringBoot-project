package com.vanillwa.sbp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vanillwa.sbp.auth.UserAuthFailureHandler;
import com.vanillwa.sbp.auth.UserAuthProvider;
import com.vanillwa.sbp.auth.UserPrincipalDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	final UserAuthProvider userAuthProvider;
	final UserPrincipalDetailsService userPrincipalDetailsService;

	public final void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthProvider);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable()).headers((headers) -> headers.frameOptions((options) -> options.disable()))
				.authorizeHttpRequests(
						(auth) -> auth.requestMatchers("/css/**", "/images/**", "/js/**", "/login/**", "/").permitAll()
								.anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin.loginPage("/login/loginForm").usernameParameter("username")
						.passwordParameter("password").loginProcessingUrl("/login/login-proc")
						.defaultSuccessUrl("/user/userPage", true).failureHandler(new UserAuthFailureHandler())
						.permitAll())
				.logout((logout) -> logout.logoutUrl("/login/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID"))
				.userDetailsService(userPrincipalDetailsService);
		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}