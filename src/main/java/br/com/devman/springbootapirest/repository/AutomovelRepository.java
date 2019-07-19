package br.com.devman.springbootapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devman.springbootapirest.domain.Automovel;


@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {

}
