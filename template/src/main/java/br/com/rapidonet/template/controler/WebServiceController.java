package br.com.rapidonet.template.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rapidonet.template.dao.WSconsumir;
import br.com.rapidonet.template.model.Negociacao;

@Controller
public class WebServiceController {
	/**
	 * Método Servlet responsável por controlar o consumir do ws.
	 * 
	 * @param usuario - Modelo negociacao
	 * 
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/consumirws", method = RequestMethod.POST)
	public ModelAndView consumirWS(@ModelAttribute WSconsumir negociacao) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorusuario");
		
		if (WSconsumir.getNegociacoes().isEmpty() == false) {
			String mensagem = "WebService Consumido com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "WebService Não consumido!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}
}
