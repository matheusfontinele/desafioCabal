package cabal.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import cabal.desafio.domain.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
	
}
