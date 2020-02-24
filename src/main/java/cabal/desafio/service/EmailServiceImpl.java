package cabal.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabal.desafio.domain.Email;
import cabal.desafio.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Override
	public void atualizar(Email email) {
		emailRepository.save(email);
	}

}
