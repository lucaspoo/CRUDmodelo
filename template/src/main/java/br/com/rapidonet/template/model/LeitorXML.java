package br.com.rapidonet.template.model;


import java.io.InputStream;
import java.util.List;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.rapidonet.template.model.Negociacao;

public class LeitorXML {

	  public List<Negociacao> carrega(InputStream inputStream) {
	    XStream stream = new XStream(new DomDriver());
	    stream.alias("negociacao", Negociacao.class);
	    return (List<Negociacao>) stream.fromXML(inputStream);
	  }
	}