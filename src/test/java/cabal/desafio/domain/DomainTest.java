package cabal.desafio.domain;

import org.junit.Test;

public class DomainTest {

	@Test
	public void getAndSetEmail() {
		Email email = new Email();

		email.setEmail("cabal@cabal.com.br");
		email.getEmail();
		email.setId(1L);
		email.getId();

		Comercio comercio = new Comercio();
		email.setComercio(comercio);
		email.getComercio();
	}

	@Test
	public void getAndSetTelefone() {
		Telefone telefone = new Telefone();

		telefone.setNumero("7283723");
		telefone.getNumero();
		telefone.setTipoTelefone("RESIDENCIAL");
		telefone.getTipoTelefone();
		Comercio comercio = new Comercio();
		telefone.setComercio(comercio);
		telefone.getComercio();
		telefone.setId(1L);
		telefone.getId();
	}

	@Test
	public void getAndSetEndereco() {
		Endereco endereco = new Endereco();

		endereco.setBairro("Bairro");
		endereco.getBairro();
		endereco.setCEP(3245555);
		endereco.getCEP();
		endereco.setCidade("BRASILIA");
		endereco.getCidade();
		Comercio comercio = new Comercio();
		endereco.setComercio(comercio);
		endereco.getComercio();
		endereco.setComplemento("Complemento de teste");
		endereco.getComplemento();
		endereco.setId(1L);
		endereco.getId();
		endereco.setLogradouro("Logradouro de teste");
		endereco.getLogradouro();
		endereco.setUF("DF");
		endereco.getUF();
	}
}
