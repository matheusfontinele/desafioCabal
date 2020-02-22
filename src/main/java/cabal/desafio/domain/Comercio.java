package cabal.desafio.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Comercio {

	@Column(nullable=false)
	@Min(20)
	@Max(100)
	private String nome;
	
	@Id
	@Column(nullable=false)
	private long cnpj;
	
	@OneToOne(mappedBy="comercio")
	private Endereco endereco;
	
	@OneToMany(mappedBy="comercio")
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy="comercio")
	private List<Email> emails;
}
