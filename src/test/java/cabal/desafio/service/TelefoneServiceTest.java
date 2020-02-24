package cabal.desafio.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cabal.desafio.domain.Telefone;
import cabal.desafio.repository.TelefoneRepository;

public class TelefoneServiceTest {
	
	@InjectMocks
	TelefoneServiceImpl telefoneServiceImpl;
	
	@Mock
	TelefoneRepository telefoneRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void atualizarEmail() {
		
		telefoneServiceImpl.atualizar(getTelefone());
		
	}
	
	private Telefone getTelefone() {
		Telefone telefone1 = new Telefone();
		telefone1.setTipoTelefone("RESIDENCIAL");
		telefone1.setNumero("984848484");
		return telefone1;
	}
}
