package cabal.desafio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.domain.Endereco;
import cabal.desafio.domain.Telefone;
import cabal.desafio.service.ComercioService;

@WebMvcTest(ComercioController.class)
public class ComercioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ComercioService comercioService;
	
	@Autowired
	private ObjectMapper objectMapper;

	private List<Comercio> comercioList;

	@BeforeEach
	public void init() {
		this.comercioList = new ArrayList<Comercio>();
		comercioList.add(getComercio("87360625000142"));
		comercioList.add(getComercio("52659578000158"));
		comercioList.add(getComercio("44872443000148"));

	}
	
	@Test
	public void shouldCreateACommerceSucessufully() throws JsonProcessingException, Exception {
		final Comercio commerce = getComercio("87360625000142");

		Mockito.when(comercioService.salvar(Mockito.any(Comercio.class) )).thenReturn(commerce);
		
		this.mockMvc
				.perform(post("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerce)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.message").value("Cadastro realizado com sucesso."));
	}

	@Test
	public void shouldReturnErrorCommerceWithNameSmallerThan20() throws JsonProcessingException, Exception {
		final Comercio commerce = getComercio("87360625000142");

		commerce.setNome("Nome pequeno");

		this.mockMvc
				.perform(post("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerce)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Nome deve ter entre 20 e 100 caracteres"));
	}

	@Test()
	public void shouldNotCreateCommerceWithoutPhone() throws
	 JsonProcessingException, Exception { 
		final Comercio commerce = getComercio("87360625000142");
	 
	 commerce.setTelefones(null);
	 
	 Mockito.when(comercioService.salvar(Mockito.any(Comercio.class))).thenThrow(Exception.class);
	 
	 this.mockMvc.perform(post("/comercio/")
	 .contentType(MediaType.APPLICATION_JSON)
	 .content(objectMapper.writeValueAsString(commerce)))
	 .andExpect(status().isUnprocessableEntity()); 
	 }

	@Test
	public void shouldReturnErrorCommerceWithInvalidCnpj() throws JsonProcessingException, Exception {

		// CNPJ Inválido
		final Comercio commerce = getComercio("123456");

		this.mockMvc
				.perform(post("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerce)))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("CNPJ inválido"));
	}
	
	@Test
	public void shouldReturnAllCommerces() throws Exception {
		Mockito.when(comercioService.listarComercio()).thenReturn(comercioList);

		this.mockMvc.perform(get("/comercio/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(comercioList.size()));
	}
	
	@Test
	public void shouldReturnOneCommerceById() throws Exception {
		final String commerceId = "87360625000142";
		final Comercio commerce = getComercio("87360625000142");

		Mockito.when(comercioService.pesquisarPorCNPJ(commerceId)).thenReturn(Optional.of(commerce));

		this.mockMvc.perform(get("/comercio/{cnpj}", commerceId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(commerce.getNome()));
	}

	@Test
	public void shouldReturn404WhenFindCommerceById() throws Exception {
		final String commerceId = "87360625000142";

		Mockito.when(comercioService.pesquisarPorCNPJ(commerceId)).thenReturn(Optional.empty());

		this.mockMvc.perform(get("/comercio/{cnpj}", commerceId)).andExpect(status().isNotFound());
	}
	

	@Test
	public void shouldUpdateCommerceSucessufully() throws JsonProcessingException, Exception {
		final Comercio commerce = getComercio("87360625000142");

		Mockito.when(comercioService.pesquisarPorCNPJ(commerce.getCnpj())).thenReturn(Optional.of(commerce));

		// Realizar alterações no comércio
		final Comercio commerceToBeUpdated = getComercio("87360625000142");
		commerceToBeUpdated.setNome("Burguer King do Brasil S.A");
		commerceToBeUpdated.getEndereco().setCidade("Uberlândia");

		Mockito.when(comercioService.atualizar(Mockito.any(Comercio.class))).thenReturn(commerceToBeUpdated);
		
		this.mockMvc
				.perform(put("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerceToBeUpdated)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Atualizado com sucesso."));
	}

	@Test
	public void shouldReturnErrorWhenUpdateCommerceWithNameSmallerThan20() throws JsonProcessingException, Exception {
		final Comercio commerce = getComercio("87360625000142");

		Mockito.when(comercioService.pesquisarPorCNPJ(commerce.getCnpj())).thenReturn(Optional.of(commerce));

		// Realizar alterações no comércio
		final Comercio commerceToBeUpdated = getComercio("87360625000142");
		commerceToBeUpdated.setNome("Nome pequeno");
		commerceToBeUpdated.getEndereco().setCidade("Uberlândia");

		this.mockMvc
				.perform(put("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerceToBeUpdated)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Nome deve ter entre 20 e 100 caracteres"));
	}
	
	@Test
	public void shouldReturnErrorWhenUpdateCommerceWithInvalidCnpj() throws JsonProcessingException, Exception {
		final Comercio commerce = getComercio("87360625000142");

		Mockito.when(comercioService.pesquisarPorCNPJ(commerce.getCnpj())).thenReturn(Optional.of(commerce));

		// Realizar alterações no comércio
		final Comercio commerceToBeUpdated = getComercio("87360625000142");
		// CNPJ Inválido
		commerceToBeUpdated.setCnpj("12355");

		this.mockMvc
				.perform(put("/comercio/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(commerceToBeUpdated)))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("CNPJ inválido"));
	}
	
	@Test
	public void shouldDeleteCommerceSucessfully() throws Exception {
		final String commerceId = "87360625000142";
		

		this.mockMvc.perform(delete("/comercio/{cnpj}", commerceId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Removido com sucesso."));
	}
	
	@Test
	public void shouldReturnNotFoundCommerce() throws Exception {
		final String commerceId = "87360625000142";

		Mockito.doThrow(Exception.class).when(comercioService).excluir(commerceId);
		
		this.mockMvc.perform(delete("/comercio/{cnpj}", commerceId))
		.andExpect(status().isNotFound());
	}
	
	// Utils
	private Comercio getComercio(String cnpj) {

		Comercio comercio = new Comercio();

		comercio.setCnpj(cnpj);
		comercio.setNome("Arcos dourados do Brasil S.A");

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
