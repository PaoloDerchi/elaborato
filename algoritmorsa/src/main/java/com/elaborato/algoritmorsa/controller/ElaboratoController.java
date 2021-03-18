package com.elaborato.algoritmorsa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rsa")
public class ElaboratoController {

	
	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,HttpServletRequest request
		) throws UnknownHostException  {
		
		
		String remoteHost = "";
		String remoteAddr = "";
		request.getHeader("User-Agent");
		
		remoteHost=	request.getRemoteUser();
		
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
		String referrer = request.getHeader("referer");
		//remoteHost= 	request.getRemoteAddr();
        
       // remoteHost= InetAddress.getLoopbackAddress().getHostName();
		String a = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"body {\r\n" + 
				"background-image: url(\"https://www.istitutimezzacapo.it/website/wp-content/uploads/2018/09/istituti-mezzacapo.jpg\");\r\n" +
				"background-repeat: no-repeat;\r\n"+
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<h1>Ciao Utente</h1>\r\n" + 
				"<p>"+request.getHeader("User-Agent")+"</p>\r\n" + 
				"</body>"+
				"</html>";
		return a;
		
	}
	

}
