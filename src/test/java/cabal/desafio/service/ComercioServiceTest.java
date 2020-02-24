package cabal.desafio.service;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.domain.Endereco;
import cabal.desafio.domain.Telefone;
import cabal.desafio.repository.ComercioRepository;

public class ComercioServiceTest {

	@InjectMocks
	ComercioServiceImpl comercioService;

	@Mock
	ComercioRepository comercioRepository;

	@Mock
	EmailServiceImpl emailService;

	@Mock
	TelefoneServiceImpl telefoneServiceImpl;

	@Mock
	EnderecoServiceImpl enderecoServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void criaComercioSemNome() throws Exception {

		Comercio comercio = getComercio();

		comercio.setNome("");

		comercioService.salvar(comercio);

		assertNull(comercioService.pesquisarPorCNPJ(comercio.getCnpj()));
	}

	@Test(expected = Exception.class)
	public void criaComercioComCnpjInvalido() throws Exception {

		Comercio comercio = getComercio();

		comercio.setCnpj(23213211L);

		comercioService.salvar(comercio);
	}
	
	@Test(expected = Exception.class)
	public void criaComercioSemTelefone() throws Exception {

		Comercio comercio = getComercio();

		comercio.setTelefones(null);

		comercioService.salvar(comercio);
	}

	@Test(expected = Exception.class)
	public void criaComercioSemEmail() throws Exception {

		Comercio comercio = getComercio();

		comercio.setEmails(null);

		comercioService.salvar(comercio);
	}
	
	@Test
	public void alterarComercio() throws Exception {

		Comercio comercio = getComercio();

		comercioService.atualizar(comercio);
		
	}
	
	@Test(expected = Exception.class)
	public void alterarComercioSemTelefone() throws Exception {

		Comercio comercio = getComercio();

		comercio.setTelefones(null);
		
		comercioService.atualizar(comercio);
		
	}

	@Test(expected = Exception.class)
	public void alterarComercioSemEmail() throws Exception {

		Comercio comercio = getComercio();

		comercio.setEmails(null);
		
		comercioService.atualizar(comercio);
		
	}
	
	private Comercio getComercio() {

		Comercio comercio = new Comercio();

		comercio.setCnpj(87360625000142L);
		comercio.setNome("Empresa de Teste");

		Telefone telefone1 = new Telefone();
		telefone1.setTipoTelefone("RESIDENCIAL");
		telefone1.setNumero("984848484");
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone1);

		Endereco endereco = new Endereco();
		endereco.setCEP(71908720);
		endereco.setLogradouro("Rua das Pitanguerias Res. Easy");
		endereco.setBairro("Norte");
		endereco.setCidade("Águas Claras");
		endereco.setUF("DF");

		Email email1 = new Email();
		email1.setEmail("matheus.fontinele@cabal.com.br");
		List<Email> emails = new ArrayList<Email>();
		emails.add(email1);

		comercio.setTelefones(telefones);
		comercio.setEndereco(endereco);
		comercio.setEmails(emails);

		return comercio;
	}

}
