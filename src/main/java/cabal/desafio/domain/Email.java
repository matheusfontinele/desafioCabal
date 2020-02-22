package cabal.desafio.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Email {

	@Column
	private String email;
	
	@ManyToOne()
	@JoinColumn(name="comercio_id", nullable=false)
	private Comercio comercio;
}
