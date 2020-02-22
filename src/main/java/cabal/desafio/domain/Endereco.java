package cabal.desafio.domain;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

@Entity
public class Endereco {

	private int CEP;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String UF;
	@Nullable
	private String complemento;

	@OneToOne
	@MapsId
	private Comercio comercio;
}
