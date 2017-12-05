package br.com.rapidonet.template.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe responsável por implementar Servlets de controle que estão
 * relacionados ao Login do sistema.
 * 
 * @author Lucas Fernandes
 *
 */

@Controller
public class LoginController {
	
	/**
	 * Método Servlet responsável por permitir acesso a página de login.
	 */
	@RequestMapping(value = {"/","/login",""})
	public String execute(){
		return "login";
	}
	
	/**
	 * Método Servlet responsável por permitir logoff a página de login.
	 */
	@RequestMapping("/logout")
	public String logoff(HttpSession session){
			session.invalidate();
		return "redirect:Login";
	}
}

