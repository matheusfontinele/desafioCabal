package cabal.desafio.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import cabal.desafio.controller.CepController;

public class CepControllerTest {
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void cepValido() {
		CepController cepController = new CepController();
		String response = cepController.buscarCep("71908720");
		assertTrue(response.contains("71908-720"));
	}
	
	@Test(expected = RuntimeException.class)
	public void cepInvalido() {
		CepController cepController = new CepController();
		cepController.buscarCep("asdsd");
	}
	
}
