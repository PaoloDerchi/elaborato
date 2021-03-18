package com.elaborato.algoritmorsa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rsa")
public class ElaboratoController {

	
	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
	public void logInImpiegato(@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome
		)  {
		
	System.out.println();	
		
		
		
	}
	

}
