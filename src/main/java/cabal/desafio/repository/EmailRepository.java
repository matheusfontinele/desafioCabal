package cabal.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import cabal.desafio.domain.Email;

public interface EmailRepository extends CrudRepository<Email, Long>{
	
}
