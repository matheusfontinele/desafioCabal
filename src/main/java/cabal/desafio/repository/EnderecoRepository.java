package cabal.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import cabal.desafio.domain.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
	
}
