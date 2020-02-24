package cabal.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabal.desafio.domain.Comercio;
import cabal.desafio.repository.ComercioRepository;
import cabal.desafio.utils.ValidaCNPJ;

@Service
public class ComercioServiceImpl implements ComercioService {

	@Autowired
	private ComercioRepository comercioRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TelefoneService TelefoneService;

	@Autowired
	private EnderecoService enderecoService;

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
			if (comercioRepository.findById(comercio.getCnpj()) != null) {
				// Atualiza primeiro as tabelas que contém chave estrangeira
				comercio.getEmails().stream().forEach(email -> emailService.atualizar(email));
				comercio.getTelefones().stream().forEach(telefone -> TelefoneService.atualizar(telefone));
				enderecoService.atualizar(comercio.getEndereco());

				comercioRepository.save(comercio);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void excluir(long cnpj) throws Exception {
		try {
			comercioRepository.deleteById(cnpj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void validaEntidade(Comercio comercio) throws Exception {

		ValidaCNPJ validaCnpj = new ValidaCNPJ();

		if (!validaCnpj.isCNPJ(String.valueOf(comercio.getCnpj()))) {
			throw new Exception("CNPJ não é válido.");
		}

		if (comercio.getEmails().isEmpty() || comercio.getEmails() == null)
			throw new Exception("Pelo menos um e-mail deve ser informado.");

		if (comercio.getTelefones() == null || comercio.getTelefones().isEmpty()) {
			throw new Exception("Pelo menos um telefone deve ser informado");
		}
	}

}
