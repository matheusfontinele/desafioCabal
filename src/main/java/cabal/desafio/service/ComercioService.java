package cabal.desafio.service;

import java.util.List;

import cabal.desafio.domain.Comercio;

public interface ComercioService {

	public void salvar(Comercio comercio) throws Exception;

	public List<Comercio> listarComercio();
	
	public Comercio pesquisarPorCNPJ(long cnpj);

	public void atualizar(Comercio comercio) throws Exception;

	public void excluir(long cnpj) throws Exception;
}
