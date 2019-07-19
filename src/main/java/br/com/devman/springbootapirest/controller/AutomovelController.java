package br.com.devman.springbootapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devman.springbootapirest.service.AutomovelService;
import br.com.devman.springbootapirest.vo.AutomovelVO;

@RestController
@RequestMapping("/api/automovel")
public class AutomovelController {

	@Autowired
	private AutomovelService automovelService;
	
	@GetMapping
	public ResponseEntity<List<AutomovelVO>> list(){
		return new ResponseEntity<List<AutomovelVO>>( automovelService.list() ,HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<AutomovelVO> create(@RequestBody AutomovelVO automovel){
		return new ResponseEntity<AutomovelVO>( automovelService.create(automovel),HttpStatus.CREATED);
	}
}
