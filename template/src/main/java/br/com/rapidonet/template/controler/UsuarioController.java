package br.com.rapidonet.template.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rapidonet.template.dao.UsuarioDAO;
import br.com.rapidonet.template.model.Usuario;

/**
 * Classe responsável por implementar Servlets de controle que estão
 * relacionados a usuario do sistema.
 * 
 * @author Lucas Fernandes
 *
 */

@Controller
public class UsuarioController {

	private UsuarioDAO usuarioDAO;

	/**
	 * Construtor da Classe aplicando injeção de dependencia do Spring MVC
	 * 
	 * @param usuario
	 */
	@Autowired
	public UsuarioController(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Método Servlet responsável por permitir acesso a página do gerenciador
	 * de usuários.
	 * 
	 * @return ModelAndView - Lista de usuarios
	 */
	@RequestMapping(value = "/gerenciadorusuario")
	public ModelAndView executeUsuario() {
		ModelAndView mv = new ModelAndView("/admin/gerenciadorusuario");
		mv.addObject("listausuarios", usuarioDAO.getListaTodosUsuarios());
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o adicionar novo usuario.
	 * 
	 * @param usuario - Modelo usuario. 
	 *            
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/adicionausuario", method = RequestMethod.POST)
	public ModelAndView adicionaUsuario(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorusuario");
		if (usuarioDAO.adiciona(usuario)) {
			String mensagem = "Usuario adicionado com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Erro ao cadastrar usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/** Controlador Servlet responsável por adicionar usuário, é verificado
	 * a quantidade de dígitos, se for menor ou igual a 6 é adicionado o privilégio
	 * de aluno, senão é setado o privilégio de professor.
	 * 
	 * @param usuario
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/adicionausuariologin", method = RequestMethod.POST)
	public ModelAndView adicionaUsuarioTelaLogin(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/login");
		if (usuarioDAO.isRegistrado(usuario)) {
			String mensagem = "Usuario já está cadastrado!";
			mv.addObject("erro", mensagem);
		} else {
				usuario.setPermissao("ROLE_USER");
		}
	
			if (usuarioDAO.adiciona(usuario)) {
				String mensagem = "Usuario adicionado com Sucesso! Faça Login!";
				mv.addObject("sucesso", mensagem);
			} else {
				String mensagem = "Erro ao cadastrar usuario!";
				mv.addObject("erro", mensagem);
			}
			return mv;
	}
	
	

	/**
	 * Método Servlet responsável por controlar o editar usuario.
	 * 
	 * @param usuario - Modelo usuario
	 * 
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/editausuario", method = RequestMethod.POST)
	public ModelAndView editaUsuario(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorusuario");
		if (usuarioDAO.edita(usuario)) {
			String mensagem = "Usuario editado com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Erro ao editar usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o alterarsenha do usuario.
	 * 
	 * @param usuario - Modelo usuario
	 * 
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro e redireciona para Gerenciador do Usuario.
	 */
	@RequestMapping(value = "/alterasenha", method = RequestMethod.POST)
	public ModelAndView alteraSenha(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorusuario");
		if (usuarioDAO.alteraSenha(usuario)) {
			String mensagem = "Senha alterada com sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Erro ao alterar senha do usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o alterarsenha do usuario.
	 * 
	 * @param usuario - Modelo usuario
	 * 
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro e redireciona para Login.
	 */
	@RequestMapping(value = "/alterasenhamenu", method = RequestMethod.POST)
	public ModelAndView alteraSenhaMenu(@ModelAttribute Usuario usuario) {
		ModelAndView mv;
		if (usuarioDAO.alteraSenha(usuario)) {
			String mensagem = "Senha alterada com sucesso!";
			mv = new ModelAndView("forward:/admin");
			mv.addObject("sucesso", mensagem);
		} else {
			mv = new ModelAndView("forward:/admin");
			String mensagem = "Erro ao alterar senha do usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o remover usuario.
	 * 
	 * @param usuario - Modelo usuario
	 *            
	 * @return ModelAndView - Somente redireciona para Gerenciador do Usuario.
	 */
	@RequestMapping(value = "/removeusuario", method = RequestMethod.POST)
	public ModelAndView removeDisciplina(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorusuario");
		usuarioDAO.remove(usuario);
		return mv;
	}


	/**
	 * Método Servlet responsável por controlar o filtro (Pesquisa) da pagina
	 * gerenciador do livro.
	 * 
	 * @param usuario - Modelo usuario
	 *         
	 * @param opcaopesquisa - Seleciona a busca pela matrícula ou pelo nome.       
	 *            
	 * @return ModelAndView - Retorna uma lista de usuarios de acordo com o filtro.
	 */
	@RequestMapping(value = "/pesquisausuario", method = RequestMethod.POST)
	public ModelAndView pesquisaDisciplina(@ModelAttribute Usuario usuario,
			@RequestParam String opcaopesquisa) {
		ModelAndView mv = new ModelAndView("/admin/gerenciadorusuario");
		opcaopesquisa = opcaopesquisa.toLowerCase();
		if (opcaopesquisa.equals("matricula")) {
			mv.addObject("listausuarios",
					usuarioDAO.pesquisaPelaMatricula(usuario));
		} else if (opcaopesquisa.equals("nome")) {
			mv.addObject("listausuarios", usuarioDAO.pesquisaPeloNome(usuario));
		}
		return mv;
	}

}