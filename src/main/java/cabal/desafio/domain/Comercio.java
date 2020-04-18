package cabal.desafio.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Comercio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@Size(min = 20, max = 100, message = "Nome deve ter entre 20 e 100 caracteres")
	private String nome;
	
	@Id
	@Column(nullable=false,unique = true)
	@NotNull(message = "CNPJ não pode ser nulo")
	@CNPJ(message = "CNPJ inválido")
	private String cnpj;
	
	@OneToOne(mappedBy="comercio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Endereco endereco;
	
	@OneToMany(mappedBy="comercio",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy="comercio", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Email> emails;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	
	
}
