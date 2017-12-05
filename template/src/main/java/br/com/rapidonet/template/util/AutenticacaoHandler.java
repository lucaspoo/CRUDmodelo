package br.com.rapidonet.template.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.com.rapidonet.template.dao.UsuarioDAO;

/** Classe Respons�vel por ter m�todos de Sucesso depois da autentica��o 
 * do login feito pelo Framework Spring Security
 * 
 * @author Lucas Fernandes
 *
 */
public class AutenticacaoHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication
				.getAuthorities());
		
		  
		UsuarioDAO usuariodao = new UsuarioDAO();
		
		// Criando sess�o para guardar nome do usuario
		request.getSession().setAttribute("nome_usuario_sessao", usuariodao.getNomeUsuarioLogado());
		
		// De acordo com o tipo de permiss�o � redirecionado para a p�gina relacionada.
		if (roles.contains("ROLE_USER")) {
			response.sendRedirect("/template/user");
		}else if  (roles.contains("ROLE_ADMIN")){
			response.sendRedirect("/template/gerenciador");
		}
	}
}