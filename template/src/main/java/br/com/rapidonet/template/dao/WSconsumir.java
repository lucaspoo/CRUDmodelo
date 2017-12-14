package br.com.rapidonet.template.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rapidonet.template.controler.Fabrica;
import br.com.rapidonet.template.model.LeitorXML;
import br.com.rapidonet.template.model.Negociacao;
import br.com.rapidonet.template.model.Usuario;

public class WSconsumir {

	 private static final String URL_WEBSERVICE = 
			    "http://argentumws.caelum.com.br/negociacoes";

			  public static List<Negociacao> getNegociacoes() {

			    HttpURLConnection connection = null;
			    
			    try {
			      URL url = new URL(URL_WEBSERVICE);

			      connection = (HttpURLConnection)url.openConnection();
			        
			      InputStream content = (InputStream) connection.getInputStream();
			      
			      
			      EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
			  	  manager.getTransaction().begin();
	
			  	  /**
			  	   * Responsável por persistir o xml no banco.
			  	   * Criado por Lucas Fernandes
			  	   * 14/12/2017
			  	   */
			  	List<Negociacao> dados = new LeitorXML().carrega(content);
			  	for (Negociacao teste : dados) {
			  	    manager.persist(teste);
			  	    
			  	}
			  		manager.getTransaction().commit();

	      return dados;
			      
			    } catch (IOException e) {
			      throw new RuntimeException(e);
			    }
			  }
			  
			  
			  /**
			   * Classe responsável por buscar no banco de dados o resultado do webservice consumido.
			   * @return os dados do banco de dados
			   */
			  public ArrayList<Negociacao> getListaTodosDados(){
					EntityManager manager = Fabrica.getEntityManagerFactory().createEntityManager();
					TypedQuery<Negociacao> typedQuery = manager.createNamedQuery("Negociacao.pesquisaBanco", Negociacao.class);
					ArrayList<Negociacao> webservice = (ArrayList<Negociacao>) typedQuery.getResultList();
					return webservice;
				}
			  
			  
			
			  
}