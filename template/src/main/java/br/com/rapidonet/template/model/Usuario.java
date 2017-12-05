package br.com.rapidonet.template.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(name="Usuario.pesquisaPelaMatricula", 
				query="SELECT usu FROM Usuario usu WHERE usu.matricula = :matricula"),
	@NamedQuery(name="Usuario.pesquisaPeloNome", 
				query="SELECT usu FROM Usuario usu WHERE usu.nome LIKE :nome"),
	@NamedQuery(name="Usuario.pesquisaTodosUsuarios", 
				query="SELECT usu FROM Usuario usu")
})

/** Classe Modelo de Usuario
 * 
 * @author Lucas Fernandes
 *
 */

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@NotNull
	private Integer matricula;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private String permissao ;
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}