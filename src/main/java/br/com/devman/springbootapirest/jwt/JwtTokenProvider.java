package br.com.devman.springbootapirest.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.devman.springbootapirest.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {

	private String secretKey = "secret";
	private long  validityInMilliseconds = 3600000; //1h;
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		
		Claims claims = Jwts.claims().setSubject(username);
		Date now = new Date();
		Date expireAt = new Date( now.getTime() + validityInMilliseconds );
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt( now )
				.setExpiration(expireAt)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	public Authentication getAuthentication( String token ) {
		UserDetails userDetail = this.usuarioService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetail,"", userDetail.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	public String resolverToken(HttpServletRequest request ) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring( 7, bearerToken.length() );
		}
		return null;
	}
	
	public boolean validadeToken( String token ) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if( claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Token inválido!!");
		}
	}
}
