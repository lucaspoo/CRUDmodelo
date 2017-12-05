package br.com.rapidonet.template.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe respons�vel por implementar Servlets de controle que est�o
 * relacionados ao Login do sistema.
 * 
 * @author Lucas Fernandes
 *
 */

@Controller
public class LoginController {
	
	/**
	 * M�todo Servlet respons�vel por permitir acesso a p�gina de login.
	 */
	@RequestMapping(value = {"/","/login",""})
	public String execute(){
		return "login";
	}
	
	/**
	 * M�todo Servlet respons�vel por permitir logoff a p�gina de login.
	 */
	@RequestMapping("/logout")
	public String logoff(HttpSession session){
			session.invalidate();
		return "redirect:Login";
	}
}

