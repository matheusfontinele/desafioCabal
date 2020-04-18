package cabal.desafio.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.domain.Endereco;
import cabal.desafio.domain.Telefone;
import cabal.desafio.repository.ComercioRepository;
import cabal.desafio.repository.EmailRepository;
import cabal.desafio.repository.EnderecoRepository;
import cabal.desafio.repository.TelefoneRepository;


@Service
public class ComercioService {

	@Autowired
	private ComercioRepository comercioRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	public Comercio salvar(Comercio comercio) throws Exception {

		try {
			validaEntidade(comercio);
			return comercioRepository.save(comercio);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public List<Comercio> listarComercio() {
		return (List<Comercio>) comercioRepository.findAll();
	}

	public Optional<Comercio> pesquisarPorCNPJ(String cnpj) {
		return comercioRepository.findById(cnpj);
	}

	public Comercio atualizar(Comercio comercio) throws Exception {

		try {
			validaEntidade(comercio);
			if (comercioRepository.findById(comercio.getCnpj()).isPresent()) {
				// Atualiza primeiro as tabelas que contém chave estrangeira
				comercio.getEmails().stream().forEach(email -> atualizar(email));
				comercio.getTelefones().stream().forEach(telefone -> atualizar(telefone));
				atualizar(comercio.getEndereco());

				return comercioRepository.save(comercio);
			} else {
				throw new Exception("Comercio não cadastrado.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void excluir(String cnpj) throws Exception {
		
		Optional<Comercio> optComercio = pesquisarPorCNPJ(cnpj);
		
		if(optComercio.isPresent()) {
			comercioRepository.deleteById(cnpj);
		}else {
			throw new Exception("Não foi encontrado registros para esse CNPJ");
		}
	}

	public void validaEntidade(Comercio comercio) throws Exception {

		if (comercio.getEmails() == null || comercio.getEmails().isEmpty()) {
			throw new Exception("Pelo menos um e-mail deve ser informado.");
		}

		if (comercio.getTelefones() == null || comercio.getTelefones().isEmpty()) {
			throw new Exception("Pelo menos um telefone deve ser informado");
		} else { // Tem telefone
			comercio.getTelefones().stream().forEach(telefone -> {
				String[] tiposTelefone = { "RESIDENCIAL", "COMERCIAL", "CELULAR" };
				List<String> list = Arrays.asList(tiposTelefone);
				if (!list.contains(telefone.getTipoTelefone())) {
//						throw new Exception("Telefone deve ser: RESIDENCIAL,COMERCIAL ou CELULAR");
				}
			});
		}

	}

	public void atualizar(Email email) {
		emailRepository.save(email);
	}

	public void atualizar(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	public void atualizar(Telefone telefone) {
		telefoneRepository.save(telefone);
	}

}
