package ifrn.pi.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ifrn.pi.tcc.models.Usuario;
import ifrn.pi.tcc.repositories.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private LoginRepository lr; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = lr.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return user;
	}
}
