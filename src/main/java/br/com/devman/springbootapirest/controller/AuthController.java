package br.com.devman.springbootapirest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devman.springbootapirest.domain.Usuario;
import br.com.devman.springbootapirest.jwt.JwtTokenProvider;
import br.com.devman.springbootapirest.repository.UserRepository;
import br.com.devman.springbootapirest.vo.AccountVO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/sign")
	public ResponseEntity< Map<String , String > > sign( @RequestBody AccountVO data) {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
			
			Usuario  usuario = userRepository.findByUserName( data.getUsername() );
			
			String token  = "";
			
			if( usuario != null ) {
				token = jwtTokenProvider.createToken(data.getUsername(), usuario.getRoles());
			}else {
				throw new RuntimeException("Usuário não encontrado.");
			}
			
			Map<String , String > response = new HashMap<String, String>();
			
			response.put("username", data.getUsername() );
			response.put("token", token );
			
			return ResponseEntity.ok( response );
			
		} catch (Exception e) {
			throw new RuntimeException("Credenciais inválidas.");
		}
	} 
	
}
