package br.com.rapidonet.template.controler;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rapidonet.template.controler.Fabrica;

/**
 * Classe responsável por implementar Servlets para gerar relatórios.
 * 
 * @author Kélvin Santiago
 *
 */

@Controller
public class RelatorioController {
	

	private final String CAMINHO_RELATORIO_USUARIO = "/br/com/rapidonet/template/relatorios/RelatorioUsuarios.jrxml";

	
	/** Servlet responsável por gerar relatório de todos os usuarios.
	 * 
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 */
	@RequestMapping(value = "/relatoriousuario")
	public void geraRelatorioUsuario(HttpServletResponse response) throws JRException, IOException{
		
		InputStream inputStreamURLUsuario =this.getClass().getResourceAsStream(CAMINHO_RELATORIO_USUARIO);
		
		Connection conexao = Fabrica.getConnectionDoEntityManager();
		
		response.setContentType("application/pdf");
		
	    JasperReport jasperReport = JasperCompileManager.compileReport(inputStreamURLUsuario);           
	    
		JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap<String, Object>(),conexao);

		JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());
		
	}

	
}