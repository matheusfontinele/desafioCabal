package cabal.desafio.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.domain.Endereco;
import cabal.desafio.domain.Telefone;
import cabal.desafio.repository.ComercioRepository;
import cabal.desafio.repository.EmailRepository;
import cabal.desafio.repository.EnderecoRepository;
import cabal.desafio.repository.TelefoneRepository;

public class ComercioServiceTest {

	@InjectMocks
	ComercioService comercioService;

	@Mock
	ComercioRepository comercioRepository;

	@Mock
	EmailRepository emailRepository;

	@Mock
	TelefoneRepository telefoneRepository;

	@Mock
	EnderecoRepository enderecoRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void criarComercioComSucesso() throws Exception {
		Comercio comercio = getComercio();

		Mockito.when(comercioRepository.save(comercio)).thenReturn(comercio);
				
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
	public void alterarComercioExistente() throws Exception {

		Comercio comercio = getComercio();
		
		Mockito.when(comercioRepository.findById(comercio.getCnpj())).thenReturn(Optional.of(comercio));

		comercioService.atualizar(comercio);

	}

	@Test
	public void alterarComercioNaoExistente() throws Exception {

		Comercio comercio = getComercio();
		
		Mockito.when(comercioRepository.findById(comercio.getCnpj())).thenReturn(Optional.empty());

		assertThrows(Exception.class, () ->{
			comercioService.atualizar(comercio);
		});

	}
	
	@Test
	public void listarComercios() {
		List<Comercio> comercios = new ArrayList<Comercio>();
		
		Comercio comercio1 = getComercio();
		Comercio comercio2 = getComercio();
		comercio2.setCnpj("52659578000158");
		Comercio comercio3 = getComercio();
		comercio3.setCnpj("44872443000148");
		
		comercios.add(comercio1);
		comercios.add(comercio2);
		comercios.add(comercio3);
		
		Mockito.when(comercioRepository.findAll()).thenReturn(comercios);

		List<Comercio> expectedComercios = comercioService.listarComercio();
		
		assertEquals(expectedComercios, comercios);
		
	}
	
	@Test
	public void pesquisarComercioExistente() {
		
		Comercio comercio = getComercio();
		
		Mockito.when(comercioRepository.findById(comercio.getCnpj())).thenReturn(Optional.of(comercio));
		
		Optional<Comercio> expectedComercio = comercioService.pesquisarPorCNPJ(comercio.getCnpj());
		
		assertEquals(expectedComercio.get(), comercio);
	}
	
	@Test
	public void pesquisarComercioInexistente() {
		
		Comercio comercio = getComercio();
		
		Mockito.when(comercioRepository.findById(comercio.getCnpj())).thenReturn(Optional.empty());
		
		Optional<Comercio> expectedComercio = comercioService.pesquisarPorCNPJ(comercio.getCnpj());
		
		//Valida se o comercio não está presente
		assertFalse(expectedComercio.isPresent());
	}
	
	@Test
	public void excluirComercioExistente() throws Exception {
		
		Mockito.when(comercioRepository.findById(getComercio().getCnpj())).thenReturn(Optional.of(getComercio()));
		
		comercioService.excluir(getComercio().getCnpj());
	}
	
	@Test
	public void excluirComercioInexistente() throws Exception {
		
		Mockito.when(comercioRepository.findById(getComercio().getCnpj())).thenReturn(Optional.empty());
		
		assertThrows(Exception.class, () -> {
			comercioService.excluir(getComercio().getCnpj());
		});
	}
	
	private Comercio getComercio() {

		Comercio comercio = new Comercio();

		comercio.setCnpj("87360625000142");
		comercio.setNome("Empresa de Teste");

		Telefone telefone1 = new Telefone();
		telefone1.setTipoTelefone("RESIDENCIAL");
		telefone1.setNumero("984848484");
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone1);

		Endereco endereco = new Endereco();
		endereco.setCep(71908720);
		endereco.setLogradouro("Rua das Pitanguerias Res. Easy");
		endereco.setBairro("Norte");
		endereco.setCidade("Águas Claras");
		endereco.setUf("DF");

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
