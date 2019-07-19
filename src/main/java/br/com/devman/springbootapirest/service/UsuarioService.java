package br.com.devman.springbootapirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.devman.springbootapirest.domain.Usuario;
import br.com.devman.springbootapirest.repository.UserRepository;

@Service
public class UsuarioService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario  = userRepository.findByUserName(username);
		if( usuario == null ) {
			throw new UsernameNotFoundException("Usuario: " + username + " não encontrado."); 
		}
		return usuario;
	}

}
