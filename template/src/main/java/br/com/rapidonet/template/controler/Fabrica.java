package br.com.rapidonet.template.controler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.mysql.jdbc.Connection;

/** Classe para Factory do Entity Manager do Framework JPA.
 *  @author Lucas Fernandes
 */

public class Fabrica {
	
	private static EntityManagerFactory fabrica = null;
	private static Connection conn = null;
	
	/**
	 * Cria o Entity Manager caso n�o esteja criado.
	 * @return EntityManagerFactory - Fabrica do Entity Manager
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if(fabrica == null){
			fabrica = Persistence.createEntityManagerFactory("biblioteca");
		}
		return fabrica; 
	}
	
	/**
	 * Retorna uma conex�o do tipo Connection, atrav�s do EntityManager
	 * @return Connection - Conex�o do Entity Manager.
	 */
	
	public static Connection getConnectionDoEntityManager(){
		try{
			EntityManager em = Fabrica.getEntityManagerFactory().createEntityManager();
			Session secao = em.unwrap(Session.class);
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) secao.getSessionFactory();
			@SuppressWarnings("deprecation")
			ConnectionProvider cp = sfi.getConnectionProvider();
			conn = (Connection) cp.getConnection();
			
		}catch(Exception e){
			System.err.print(e);
		}
		return conn;
	}
	
}