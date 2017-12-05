package br.com.rapidonet.template.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.rapidonet.template.controler.Fabrica;
import br.com.rapidonet.template.model.Usuario;



/**
 * Classe responsável pelo DAO (Data Access Object) dos Usuarios, ou seja é uma
 * classe que contém métodos para acesso ao banco de dados com JPA.
 * 
 * @author Lucas Fernandes
 *
 */

@Repository
public class UsuarioDAO {
	
	/**
	 * Método responsável por adicionar um usuario no banco de dados.
	 * 
	 * @param usuario
	 * @return Boolean - True ou False
	 */
	public Boolean adiciona(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		try{
			manager.getTransaction().begin();
			usuario.setSenha(usuario.getSenha());
			manager.persist(usuario); 
			manager.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			manager.close();
		}
	}
	
	/**
	 * Método responsável por editar um usuario no banco de dados.
	 * 
	 * @param usuario
	 * @return Boolean - True ou False
	 */
	public Boolean edita(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		try{
			manager.getTransaction().begin();
			Usuario usuariobuscado = manager.find(Usuario.class, usuario.getMatricula());
			usuario.setSenha(usuariobuscado.getSenha());
			manager.merge(usuario);
			manager.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			manager.close();
		}
	}
	
	/**
	 * Método responsável por alterar senha de um usuario no banco de dados.
	 * 
	 * @param usuario
	 * @return Boolean - True ou False
	 */
	public Boolean alteraSenha(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		try{
			manager.getTransaction().begin();
			Usuario usuariobuscado = manager.find(Usuario.class, usuario.getMatricula());
			usuario.setSenha(usuario.getSenha());
			usuariobuscado.setSenha(usuario.getSenha());
			manager.merge(usuariobuscado);
			manager.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			manager.close();
		}
	}
	
	/**
	 * Método responsável por remover um usuario no banco de dados.
	 * 
	 * @param usuario
	 * @return Boolean - True ou False
	 */
	public Boolean remove(Usuario usuario){
		
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		try{
			manager.getTransaction().begin();
			Usuario usuariobuscado = manager.find(Usuario.class, usuario.getMatricula());
			manager.remove(usuariobuscado);
			manager.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			manager.close();
		}
	}

	/**
	 * Método responsável por verificar se existe o usuário no banco de dados.
	 * 
	 * @param usuario
	 * @return Boolean - True ou False
	 */
	public Boolean isRegistrado(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		Usuario dadosusuario = manager.find(Usuario.class, usuario.getMatricula());
		if(dadosusuario == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Método responsável por buscar todos usuarios no banco de dados
	 * 
	 * @return ArrayList<Usuarios> - Lista de usuarios
	 */
	public ArrayList<Usuario> getListaTodosUsuarios(){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.pesquisaTodosUsuarios", Usuario.class);
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) typedQuery.getResultList();
		return usuarios;
	}

	/** Método responsável por pegar a matrícula do usuário, fazer uma busca
	 * no banco de dados pela matricula, pegar o nome e deixar apenas o primeiro
	 * nome do usuário.
	 * 
	 * @return String - Nome do usuário Editado
	 */
	public String getNomeUsuarioLogado(){
		Integer matriculasessaologado = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		Usuario dadosusuario = manager.find(Usuario.class, matriculasessaologado);
		String nomeusuariocompleto = dadosusuario.getNome();
		String strUpperCase = nomeusuariocompleto.substring(0, nomeusuariocompleto.indexOf(" "));
		String strLowerCase = strUpperCase.toLowerCase();
		String strFinalUsuario = StringUtils.capitalize(strLowerCase); 
		return strFinalUsuario;
	}
	
	/** Método responsável por buscar os dados do usuario no banco de dados
	 * de acordo com a matricula do usuario informado.
	 * 
	 * @param usuario
	 * @return ArrayList<Usuario> - Lista de usuarios
	 */
	public ArrayList<Usuario> pesquisaPelaMatricula(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.pesquisaPelaMatricula",Usuario.class);
		typedQuery.setParameter("matricula", usuario.getMatricula());// Setando parametro da Query
		ArrayList<Usuario> usuarioArrayList = (ArrayList<Usuario>) typedQuery.getResultList();  // Pega resultado
		return usuarioArrayList;
	}
	
	/** Método responsável por buscar os dados do usuario no banco de dados
	 * de acordo com o nome do usuario informado.
	 * 
	 * @param usuario
	 * @return ArrayList<Usuario> - Lista de usuarios
	 */
	public ArrayList<Usuario> pesquisaPeloNome(Usuario usuario){
		EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
		TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.pesquisaPeloNome",Usuario.class);
		typedQuery.setParameter("nome", "%"+usuario.getNome()+"%");// Setando parametro da Query
		ArrayList<Usuario> usuarioArrayList = (ArrayList<Usuario>) typedQuery.getResultList();  // Pega resultado
		return usuarioArrayList;
	}
	
}