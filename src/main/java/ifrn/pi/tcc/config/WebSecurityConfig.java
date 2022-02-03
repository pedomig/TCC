package ifrn.pi.tcc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css's/**", "/imagens/**", "/formUser", "/loginCadastrado","/", "/telaInicial","/vacinacao", "/preven", "/boletimRg", "/formUserAdd").permitAll()
			.antMatchers("/{id}/removerBr", "/{id}/removerVc", "/{id}/removerDuvidas", "/{id}/removerBr", "/{id}/removerVc", "/{id}/removerDuvidas", "/{id}/editBr", "/{id}/editVc").hasAnyRole("ADMGERAL", "ADMLOCAL")
			.antMatchers("/formBoletim", "/formVacinacao", "/{id}", "/duvidas").hasAnyRole("ADMGERAL", "ADMLOCAL")
			.antMatchers("/painelUser", "/{id}/removerUser", "/promover").hasAnyRole("ADMGERAL")
			.antMatchers("/formFaleConosco").hasAnyRole("Cidadão")
				.anyRequest()
				.authenticated()
			.and()
			.csrf().ignoringAntMatchers("/boletimAdd")
			.and()
			.csrf().ignoringAntMatchers("/faleConoscoAdd")
			.and()
			.csrf().ignoringAntMatchers("/formVacinacaoAdd")
			.and()
			.csrf().ignoringAntMatchers("/formUserAdd")
			.and()
			.csrf().ignoringAntMatchers("/{idResposta}")
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
	}
}