package br.com.devman.springbootapirest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="marca")
public class Marca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@SequenceGenerator(name="marcaSequenceGenerator", sequenceName = "marca_id_seq")
	private Long id;
	
	@Column(name="nome_marca", nullable = false,length = 60)
	private String nomeMarca;
	
	@OneToOne( mappedBy = "marca", cascade = CascadeType.ALL )
	private Automovel automovel;
	
	public Marca() {}
	

	public Marca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}


	@Override
	public String toString() {
		return "Marca [id=" + id + ", nomeMarca=" + nomeMarca + "]";
	}
	
	

}
