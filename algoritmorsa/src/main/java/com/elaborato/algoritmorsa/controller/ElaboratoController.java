
package com.elaborato.algoritmorsa.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elaborato.algoritmorsa.model.Docente;
import com.elaborato.algoritmorsa.model.Materia;
import com.elaborato.algoritmorsa.repository.DocenteRepository;
import com.elaborato.algoritmorsa.rsaUtil.RSAOld;

@RestController
//@RequestMapping("/rsa")
public class ElaboratoController {

	
	@Value("classpath:homePage.html")
	private Resource homePage;
	
	@Value("classpath:DocenteSconosciuto.html")
	private Resource docenteSconoscito;
	
	@Value("classpath:DocenteTrovato.html")
	private Resource docenteTrovato;
	
	
	
	@Autowired
	private RSAOld rsa;
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
		public String portalAccess(HttpServletRequest request) throws UnsupportedEncodingException, IOException  {

		String userAgent=request.getHeader("User-Agent");
        Reader reader = new InputStreamReader(homePage.getInputStream());
        String paginaHome = FileCopyUtils.copyToString(reader);
        
		return paginaHome;
		//http://www.java2s.com/Code/Java/Security/SimpleRSApublickeyencryptionalgorithmimplementation.htm
	}
	
	
	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
		public String logInImpiegato(@RequestParam(required = false) String nome,
				@RequestParam(required = false) String cognome,
				@RequestParam(required = false) String matricola,
				HttpServletRequest request) throws IOException  {
		
		
		
		
		Docente  docente = 	docenteRepository.findByNomeAndCognomeAndMatricola(nome.toLowerCase(), cognome.toLowerCase(), Integer.valueOf(matricola));
		
		
		if(docente == null) {
			 Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
		        String docenteSconosicuto = FileCopyUtils.copyToString(reader);
		        
		        return docenteSconosicuto;
		}
		
		/*Set<Materia> d = docente.getMateria();
	
		for (Materia materia : d) {
			System.out.println("materia " + materia.getArgomento());
		}
		*/
		System.out.println();
		
		
		

		 Reader reader = new InputStreamReader(docenteTrovato.getInputStream());
        String docenteTrovato = FileCopyUtils.copyToString(reader);
        docenteTrovato = docenteTrovato.replace("dd%nomeU%dd", docente.getNome());
        docenteTrovato = docenteTrovato.replace("dd%congomeU%dd", docente.getCognome());
        docenteTrovato = docenteTrovato.replace("dd%tokenU%dd", "");
        
        return docenteTrovato;
		

	}
	
	@RequestMapping(value = "/homeRichiestaMaterie", method = RequestMethod.GET)
	public String richiestaMaterie() throws UnknownHostException  {
		
		System.out.println();
		return "";
	}
	
	
	
	@RequestMapping(value = "/praticheImpiegato", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam(required = false) String rsaToken) throws UnknownHostException  {
		
		System.out.println();
	String dec=	rsa.bytesToString(rsa.decrypt(rsaToken.getBytes(Charset.forName("UTF-8"))));

	return dec;
}
	
	
	

}
