package com.vanillwa.sbp.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vanillwa.sbp.domain.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {

	private final UserPrincipalDetailsService userPrincipalDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserPrincipalDetails userPrincipalDetails = (UserPrincipalDetails) userPrincipalDetailsService
				.loadUserByUsername(username);

		User user = userPrincipalDetails.getUser();
		if (user == null) {
			System.out.println("계정이 존재하지 않습니다");
			throw new BadCredentialsException("계정이 존재하지 않습니다");
		}
		System.out.println("계정 존재");

		String dbPassword = userPrincipalDetails.getPassword();

		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

		if (!pwdEncoder.matches(password, dbPassword)) {
			System.out.println("비밀번호 불일치");
			throw new BadCredentialsException("비밀번호 불일치");
		}

		return new UsernamePasswordAuthenticationToken(userPrincipalDetails, null,
				userPrincipalDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
