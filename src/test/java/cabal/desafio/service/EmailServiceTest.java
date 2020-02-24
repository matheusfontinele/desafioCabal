package cabal.desafio.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.repository.EmailRepository;

public class EmailServiceTest {
	
	@InjectMocks
	EmailServiceImpl emailServiceImpl;
	
	@Mock
	EmailRepository emailRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void atualizarEmail() {
		
		emailServiceImpl.atualizar(getEmail());
		
	}
	
	private Email getEmail() {
		Email email = new Email();
		email.setEmail("matheus.cabal@com.br");
		email.setId(1L);
		email.setComercio(new Comercio());
		return email;
	}
}
