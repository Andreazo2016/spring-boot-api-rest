package br.com.devman.springbootapirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devman.springbootapirest.domain.Automovel;
import br.com.devman.springbootapirest.domain.Marca;
import br.com.devman.springbootapirest.repository.AutomovelRepository;
import br.com.devman.springbootapirest.vo.AutomovelVO;

@Service
public class AutomovelService {

	@Autowired
	private AutomovelRepository automovelRepository;

	public AutomovelVO create( AutomovelVO automovelVO ) {
		Automovel automovel =  converterFrom(automovelVO);
		automovel = automovelRepository.save(automovel);
		return converterFrom(automovel);
	}

	
	public List<AutomovelVO> list(){
		return automovelRepository.findAll()
				.stream()
				.map(vo -> this.converterFrom(vo) )
				.collect(Collectors.toList());
	}




	private Automovel converterFrom( AutomovelVO automovelVO ) {

		Automovel automovel = new Automovel();
		Marca marca = new Marca();
		marca.setNomeMarca(automovelVO.getNomeMarca());
		automovel.setNomeAutomovel( automovelVO.getNomeAutomovel() );
		automovel.setMarca(marca);
		automovel.setDataFabricacao( automovelVO.getDataFabricacao() );
		automovel.setId( automovelVO.getId() );
		marca.setAutomovel(automovel);

		return automovel;
	}

	private AutomovelVO converterFrom( Automovel automovel ) {

		AutomovelVO automovelVo = new AutomovelVO();
		
		automovelVo.setDataFabricacao( automovel.getDataFabricacao() );
		automovelVo.setNomeAutomovel( automovel.getNomeAutomovel() );
		automovelVo.setNomeMarca( automovel.getMarca().getNomeMarca() );
		automovelVo.setId( automovel.getId() );
		
		return automovelVo;
	}
}
