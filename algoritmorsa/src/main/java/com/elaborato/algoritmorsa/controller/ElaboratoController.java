package com.elaborato.algoritmorsa.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elaborato.algoritmorsa.rsaUtil.RSA;

@RestController
//@RequestMapping("/rsa")
public class ElaboratoController {

	
	@Value("classpath:homePage.html")
	private Resource resource;
	
	@Autowired
	private RSA rsa;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
		public String portalAccess(HttpServletRequest request) throws UnsupportedEncodingException, IOException  {

		String userAgent=request.getHeader("User-Agent");
        Reader reader = new InputStreamReader(resource.getInputStream());
        String paginaHome = FileCopyUtils.copyToString(reader);
        
		return paginaHome;
		
	}
	
	
	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
		public String logInImpiegato(@RequestParam(required = false) String nome,
				@RequestParam(required = false) String cognome,
				@RequestParam(required = false) String matricola,
				HttpServletRequest request) throws UnknownHostException  {
		String totale = nome+cognome+matricola;

		return rsa.encrypt(totale.getBytes()).toString();
	}
	
	
	@RequestMapping(value = "/praticheImpiegato", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam(required = false) String rsaToken) throws UnknownHostException  {
		
		System.out.println();
	String dec=	rsa.decrypt(rsaToken.getBytes()).toString();

	return dec;
}
	
	
	

}
