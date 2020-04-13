package cabal.desafio.service;

import java.util.List;

import cabal.desafio.domain.Comercio;
import cabal.desafio.domain.Email;
import cabal.desafio.domain.Endereco;
import cabal.desafio.domain.Telefone;

public interface ComercioService {

	public void salvar(Comercio comercio) throws Exception;

	public List<Comercio> listarComercio();
	
	public Comercio pesquisarPorCNPJ(long cnpj);

	public void atualizar(Comercio comercio) throws Exception;

	public void excluir(long cnpj) throws Exception;
	
	public void atualizar(Email email);
	
	public void atualizar(Endereco endereco);
	
	public void atualizar(Telefone telefone);
}
