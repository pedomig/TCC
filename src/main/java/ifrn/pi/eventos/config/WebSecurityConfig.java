package ifrn.pi.eventos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css's/**", "/imagens/**", "/form2").permitAll()
			.antMatchers("/form").hasAnyRole("ADMGERAL","ADMLOCAL")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
	}
}
