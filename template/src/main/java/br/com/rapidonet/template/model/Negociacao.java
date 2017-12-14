package br.com.rapidonet.template.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(name="Negociacao.pesquisaBanco", 
				query="SELECT dado FROM Negociacao dado")
})

@Entity
@Table(name = "tb_negociacao")
public class Negociacao {

	@Id
	@NotNull
	@GeneratedValue
	  private int id;
	
	  private double preco;
	  
	  private int quantidade;
	  
	  private Calendar data;

	 // public Negociacao(double preco, int quantidade, Calendar data) {
	   // this.preco = preco;
	    //this.quantidade = quantidade;
	    //this.data = data;
	  //}

	  public double getPreco() {
	    return preco;
	  }

	  public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getQuantidade() {
	    return quantidade;
	  }

	  public Calendar getData() {
	    return data;
	  }
	}