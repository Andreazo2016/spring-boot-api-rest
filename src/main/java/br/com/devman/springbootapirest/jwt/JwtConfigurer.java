package br.com.devman.springbootapirest.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void configure(HttpSecurity http) {
		JwtTokenFilter customFilter = new JwtTokenFilter( jwtTokenProvider );
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
