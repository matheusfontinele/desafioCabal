package cabal.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabal.desafio.domain.Endereco;
import cabal.desafio.repository.EnderecoRepository;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void atualizar(Endereco endereco) {
		enderecoRepository.save(endereco);
	}
}
