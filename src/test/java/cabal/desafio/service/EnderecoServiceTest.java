package cabal.desafio.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cabal.desafio.domain.Endereco;
import cabal.desafio.repository.EnderecoRepository;

public class EnderecoServiceTest {
	
	@InjectMocks
	EnderecoServiceImpl enderecoServiceImpl;
	
	@Mock
	EnderecoRepository enderecoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void atualizarEmail() {
		
		enderecoServiceImpl.atualizar(getEndereco());
		
	}
	
	private Endereco getEndereco() {
		Endereco endereco = new Endereco();
		endereco.setCEP(71908720);
		endereco.setLogradouro("Rua das Pitanguerias Res. Easy");
		endereco.setBairro("Norte");
		endereco.setCidade("Águas Claras");
		endereco.setUF("DF");
		return endereco;
	}
}
