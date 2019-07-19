package br.com.devman.springbootapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devman.springbootapirest.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

	
	Usuario findByUserName(String username);
	
}
