package br.com.devman.springbootapirest.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomovelVO {
	
	@JsonProperty("id_automovel")
	private Long id;
	
	@JsonProperty("nome_automovel")
	private String nomeAutomovel;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonProperty("data_fabricacao")
	private Date dataFabricacao;
	
	@JsonProperty("nome_marca")
	private String nomeMarca;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeAutomovel() {
		return nomeAutomovel;
	}
	public void setNomeAutomovel(String nomeAutomovel) {
		this.nomeAutomovel = nomeAutomovel;
	}
	public Date getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFabricacao == null) ? 0 : dataFabricacao.hashCode());
		result = prime * result + ((nomeAutomovel == null) ? 0 : nomeAutomovel.hashCode());
		result = prime * result + ((nomeMarca == null) ? 0 : nomeMarca.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutomovelVO other = (AutomovelVO) obj;
		if (dataFabricacao == null) {
			if (other.dataFabricacao != null)
				return false;
		} else if (!dataFabricacao.equals(other.dataFabricacao))
			return false;
		if (nomeAutomovel == null) {
			if (other.nomeAutomovel != null)
				return false;
		} else if (!nomeAutomovel.equals(other.nomeAutomovel))
			return false;
		if (nomeMarca == null) {
			if (other.nomeMarca != null)
				return false;
		} else if (!nomeMarca.equals(other.nomeMarca))
			return false;
		return true;
	}
	

	
}
