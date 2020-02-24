package cabal.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cabal.desafio.domain.Comercio;
import cabal.desafio.service.ComercioService;

@RestController()
@RequestMapping("/comercio")
public class ComercioController {

	@Autowired
	private ComercioService comercioService;

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comercio> salvar(@Valid @RequestBody Comercio comercio) {

		try {
			comercioService.salvar(comercio);
			return new ResponseEntity<Comercio>(comercio, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Comercio>(HttpStatus.NOT_ACCEPTABLE);
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
	public ResponseEntity<Comercio> atualizar(@RequestBody Comercio comercio) {
		try {
			comercioService.atualizar(comercio);
			return new ResponseEntity<Comercio>(comercio, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Comercio>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	@DeleteMapping(value = "/{cnpj}")
	public ResponseEntity<String> deletarComercio(@PathVariable(value = "cnpj") long cnpj) {

		try {
			comercioService.excluir(cnpj);
			return new ResponseEntity<String>("Removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}


	}

}
