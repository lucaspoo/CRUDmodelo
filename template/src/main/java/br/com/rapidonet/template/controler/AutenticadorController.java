package br.com.rapidonet.template.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutenticadorController {
	

		/** 
		 * M�todo Servlet para acessar a p�gina home.jsp do professor
		 * @return String - Pagina /admin/home.jsp
		 */
		@RequestMapping(value = "/gerenciador")
		public String executeAdmin(){
			return "/admin/gerenciadorusuario";
		}
		
		
	}

