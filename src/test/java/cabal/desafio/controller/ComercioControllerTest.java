package cabal.desafio.controller;

import java.util.ArrayList;
import java.util.List;

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
import cabal.desafio.service.ComercioService;

public class ComercioControllerTest<MockMvc>{
	
	@InjectMocks
	ComercioController comercioController;
	
	@Mock
	ComercioService comercioService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void criaComercioSemNome() {

		Comercio comercio = getComercio();
		
		comercio.setNome("");
		
		
		comercioController.salvar(comercio, null);		
	}
	
	@Test
	public void pesquisaComercioPorCNPJValido() {
		Mockito.when(comercioService.pesquisarPorCNPJ(87360625000142L)).thenReturn(getComercio());
		comercioController.getComercio(87360625000142L);
	}
	
	@Test
	public void pesquisaComercioPorCNPJNaoExistente() {
		comercioController.getComercio(87360625000142L);
	}
	
	@Test
	public void atualizaComercio() {

		Comercio comercio = getComercio();
		
		comercioController.atualizar(comercio, null);		
	}
	
//	@Test
//	public void atualizaComercioInvalido() throws Exception {
//		
//		  Comercio comercio = getComercio();		
//		  
//		comercioService.atualizar(null);  
//		  
//		comercioController.atualizar(null);		
//	}
	

	public void deletarComercio() throws Exception {
		
		comercioController.deletarComercio(87360625000142L);
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
