package cabal.desafio.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Telefone {

	@Column
	private String tipoTelefone;
	
	@Column
	private String numero;
	
	@ManyToOne()
	@JoinColumn(name="comercio_id", nullable=false)
	private Comercio comercio;
	
}
