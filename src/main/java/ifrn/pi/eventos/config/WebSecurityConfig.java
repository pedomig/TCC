package ifrn.pi.eventos.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/telaInicial").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/")
				.permitAll();
	}
}
