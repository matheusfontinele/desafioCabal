package cabal.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.VoRetornoComercio;
import cabal.desafio.service.ComercioService;

@RestController()
@RequestMapping("/comercio")
public class ComercioController {

	@Autowired
	private ComercioService comercioService;

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VoRetornoComercio> salvar(@Valid @RequestBody Comercio comercio, Errors errors) {
		
		VoRetornoComercio voRetorno = new VoRetornoComercio();

		if(errors != null && errors.hasErrors()) {
			voRetorno.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			comercioService.salvar(comercio);	
			voRetorno.setMessage("Cadastro realizado com sucesso.");
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			voRetorno.setMessage(e.getMessage());
			return new ResponseEntity<VoRetornoComercio>(voRetorno,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}

	@GetMapping(value = "/")
	public List<Comercio> listarComercios() {

		return comercioService.listarComercio();
	}

	@GetMapping(value = "/{cnpj}")
	public ResponseEntity<Comercio> getComercio(@PathVariable long cnpj) {
		Comercio comercio = comercioService.pesquisarPorCNPJ(cnpj);
		if (comercio == null) {
			return new ResponseEntity<Comercio>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Comercio>(comercio, HttpStatus.OK);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VoRetornoComercio> atualizar(@Valid @RequestBody Comercio comercio, Errors errors) {
		VoRetornoComercio voRetorno = new VoRetornoComercio();
		
		if(errors != null && errors.hasErrors()) {
			voRetorno.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.BAD_REQUEST);
		}
		
		try {
			comercioService.atualizar(comercio);
			voRetorno.setMessage("Atualizado com sucesso.");
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.OK);
		} catch (Exception e) {
			voRetorno.setMessage(e.getMessage());
			return new ResponseEntity<VoRetornoComercio>(voRetorno,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}

	@DeleteMapping(value = "/{cnpj}")
	public ResponseEntity<VoRetornoComercio> deletarComercio(@PathVariable(value = "cnpj") long cnpj) {

		VoRetornoComercio voRetorno = new VoRetornoComercio();
		try {
			comercioService.excluir(cnpj);
			voRetorno.setMessage("Removido com sucesso.");
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			voRetorno.setMessage(e.getMessage());
			return new ResponseEntity<VoRetornoComercio>(voRetorno, HttpStatus.NOT_FOUND);
		}


	}

}
