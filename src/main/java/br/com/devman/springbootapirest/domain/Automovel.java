package br.com.devman.springbootapirest.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="automovel")
public class Automovel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@SequenceGenerator(name="automovel_sequence_generator", sequenceName = "automovel_id_seq")
	private Long id;
	
	@Column(name="nome_automovel",nullable = false,length = 60)
	private String nomeAutomovel;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fabricacao", nullable = false)
	private Date dataFabricacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Marca marca;
	
	public Automovel() {}
	
	

	public Automovel(String nomeAutomovel) {
		this.nomeAutomovel = nomeAutomovel;
		this.setDataFabricacao( new Date() );
	}



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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	@Override
	public String toString() {
		return "Automovel [id=" + id + ", nomeAutomovel=" + nomeAutomovel + ", dataFabricacao=" + dataFabricacao
				+ ", marca=" + marca + "]";
	}
	
	

}

