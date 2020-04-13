package cabal.desafio.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
import cabal.desafio.utils.ValidaCNPJ;

@Service
public class ComercioServiceImpl implements ComercioService {

	@Autowired
	private ComercioRepository comercioRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	public void salvar(Comercio comercio) throws Exception {

		try {
			validaEntidade(comercio);
			comercioRepository.save(comercio);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public List<Comercio> listarComercio() {
		return (List<Comercio>) comercioRepository.findAll();
	}

	public Comercio pesquisarPorCNPJ(long cnpj) {
		return comercioRepository.findById(cnpj).orElse(null);
	}

	public void atualizar(Comercio comercio) throws Exception {

		try {
			validaEntidade(comercio);
			if (comercioRepository.findById(comercio.getCnpj()).isPresent()) {
				// Atualiza primeiro as tabelas que contém chave estrangeira
				comercio.getEmails().stream().forEach(email -> atualizar(email));
				comercio.getTelefones().stream().forEach(telefone -> atualizar(telefone));
				atualizar(comercio.getEndereco());

				comercioRepository.save(comercio);
			} else {
				throw new Exception("Comercio não cadastrado.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void excluir(long cnpj) throws Exception {

		try {
			comercioRepository.findById(cnpj).get();
			comercioRepository.deleteById(cnpj);
		} catch (NoSuchElementException e1) {
			throw new NoSuchElementException("Não foi encontrado registros para esse CNPJ");
		}
	}

	private void validaEntidade(Comercio comercio) throws Exception {

		ValidaCNPJ validaCnpj = new ValidaCNPJ();

		if (!validaCnpj.isCNPJ(String.valueOf(comercio.getCnpj()))) {
			throw new Exception("CNPJ não é válido.");
		}

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

	@Override
	public void atualizar(Email email) {
		emailRepository.save(email);
	}

	@Override
	public void atualizar(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	@Override
	public void atualizar(Telefone telefone) {
		telefoneRepository.save(telefone);
	}

}
