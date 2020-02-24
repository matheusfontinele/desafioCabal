package cabal.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabal.desafio.domain.Telefone;
import cabal.desafio.repository.TelefoneRepository;

@Service
public class TelefoneServiceImpl implements TelefoneService{

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Override
	public void atualizar(Telefone telefone) {
		telefoneRepository.save(telefone);
	}
}
