package com.elaborato.algoritmorsa.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elaborato.algoritmorsa.rsaUtil.RSAOld;

@RestController
//@RequestMapping("/rsa")
public class ElaboratoController {

	
	@Value("classpath:homePage.html")
	private Resource resource;
	
	@Autowired
	private RSAOld rsa;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
		public String portalAccess(HttpServletRequest request) throws UnsupportedEncodingException, IOException  {

		String userAgent=request.getHeader("User-Agent");
        Reader reader = new InputStreamReader(resource.getInputStream());
        String paginaHome = FileCopyUtils.copyToString(reader);
        
		return paginaHome;
		//http://www.java2s.com/Code/Java/Security/SimpleRSApublickeyencryptionalgorithmimplementation.htm
	}
	
	
	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
		public String logInImpiegato(@RequestParam(required = false) String nome,
				@RequestParam(required = false) String cognome,
				@RequestParam(required = false) String matricola,
				HttpServletRequest request) throws UnknownHostException  {
		//String totale = nome+cognome+matricola;
	//System.out.println(rsa.bytesToString(rsa.encrypt(totale.getBytes())));
		//rsa.bytesToString(rsa.encrypt(totale.getBytes()));
		return "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"  <head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <script>\r\n" + 
				"     /*\r\n" + 
				"	 * La seguente funzione rimuove dal paragrafo con id='messaggioDiSuccesso il messaggio inserito'\r\n" + 
				"	 */\r\n" + 
				"     function EraseMessage(){\r\n" + 
				"		var par = document.getElementById('messaggioDiSuccesso');\r\n" + 
				"		if (par != null ){par.parentNode.removeChild(par);}\r\n" + 
				"	 }\r\n" + 
				" 	/*\r\n" + 
				"	 * La seguente funzione resetta il form principale\r\n" + 
				"	 * ed elimina, se presente, il paragrafo con id='messaggioDiSuccesso'\r\n" + 
				"	 */\r\n" + 
				"   /*   function CancelEntry() {\r\n" + 
				"		document.getElementById(\"myForm\").reset();\r\n" + 
				"		EraseMessage();\r\n" + 
				"      }*/\r\n" + 
				"	/*\r\n" + 
				"	 * La seguente funzione esegue un controllo sul valore inserito. \r\n" + 
				"	 * Accetta solo il range di caratteri minuscoli e maiuscoli e lo spazio.\r\n" + 
				"	 */\r\n" + 
				"	  function removeNumber(inputTesto) { inputTesto.value=inputTesto.value.replace(/[^a-zA-Z ]/g,''); }\r\n" + 
				"	/*\r\n" + 
				"	 * La seguente funzione esegue un controllo sul valore inserito. \r\n" + 
				"	 * Se è presente qualcosa diverso da numeri e il carattere / lo rimuove.\r\n" + 
				"	 */\r\n" + 
				"	  function removeCaracter(inputTesto) { inputTesto.value=inputTesto.value.replace(/[^0-9/]/g,''); }\r\n" + 
				"	  function Validate() {\r\n" + 
				"		EraseMessage();\r\n" + 
				"		var nome = document.myForm.nome.value;\r\n" + 
				"		var cognome = document.myForm.cognome.value;\r\n" + 
				"		var matricola = document.myForm.matricola.value;\r\n" + 
				"		if ((nome == \"\") || (nome == \"undefined\")) {\r\n" + 
				"			alert(\"Devi inserire un nome\");\r\n" + 
				"			document.myForm.nome.focus();\r\n" + 
				"			return false;\r\n" + 
				"		} else if ((cognome == \"\") || (cognome == \"undefined\")) {\r\n" + 
				"			alert(\"Devi inserire un cognome\");\r\n" + 
				"			document.myForm.cognome.focus();\r\n" + 
				"			return false;\r\n" + 
				"		} else if ((matricola == \"\") || (matricola == \"undefined\")) {\r\n" + 
				"			alert(\"Devi inserire un matricola\");\r\n" + 
				"			document.myForm.matricola.focus();\r\n" + 
				"			return false;\r\n" + 
				"		} else {\r\n" + 
				"			/*document.getElementById(\"messaggistica\").innerHTML +=  \r\n" + 
				"              ' <p align=\"center\" id=\"messaggioDiSuccesso\"><font face=\"Verdana\" color=\"blue\" size=\"10\">Inserimento Eseguito con successo!</font></p>'; 	*/\r\n" + 
				"     \r\n" + 
				"	  		myForm.submit();\r\n" + 
				"\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	</script>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"	h1 {\r\n" + 
				"	color:rgb(3, 10, 109) ; /*setup il colore del titolo 1*/\r\n" + 
				"	text-align:center;        /*l'allineamento al centro*/\r\n" + 
				"	font-weight:bold;		  /*il grassetto*/\r\n" + 
				"	}\r\n" + 
				"	fieldset{\r\n" + 
				"	border-color:rgb(179, 75, 6); /*setup il colore del fieldset con i colori rbg*/\r\n" + 
				"	}\r\n" + 
				"	table {\r\n" + 
				"	  margin-left: auto;  /*setup del margine tabella*/\r\n" + 
				"	  margin-right: auto;\r\n" + 
				"	}\r\n" + 
				"	td.mytdPersonal {\r\n" + 
				"		border: 1px solid black;\r\n" + 
				"   }\r\n" + 
				"	legend {\r\n" + 
				"		color:rgb(3, 10, 109)  ; /*setup del colore della legenda*/\r\n" + 
				"		text-align: center !important;\r\n" + 
				"		font-weight:bold;\r\n" + 
				"		font-size: 120%;\r\n" + 
				"	}\r\n" + 
				"	body{\r\n" + 
				"	background-color:rgb(71, 177, 124); /* setup del colore di sfondo */\r\n" + 
				"	}\r\n" + 
				"	.testo { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 100%; color:rgb(179, 75, 6); font-weight:bold}\r\n" + 
				"	a.bottone {\r\n" + 
				"	color: #fff; /*colore del testo*/\r\n" + 
				"	text-decoration: none; /*eliminare la sottolineatura del testo */\r\n" + 
				"	background: rgb(212,75,56); /*sfondo del bottone*/\r\n" + 
				"	padding: .01em 1.5em; /*padding dal testo dal bordo del bottone*/\r\n" + 
				"	outline: none; /*eliminare bordo*/\r\n" + 
				"	}\r\n" + 
				"	a.bottone:hover { background: rgb(232,95,76); } /*cambio dello sfondo al passagio del mouse*/\r\n" + 
				"	a.bottone:active { background: rgb(152,15,0); } /*cambiare lo sfondo al premere bottone*/\r\n" + 
				"    </style>\r\n" + 
				"<title >Gestione Attività Impiegati</title>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"      <h1>Gestione Attività Impiegati</h1>\r\n" + 
				"      <form  action=\"/logInImpiegato\" method=\"GET\"  id=\"myForm\" name=\"myForm\">\r\n" + 
				"      <fieldset> \r\n" + 
				"        <legend >Ciao</legend>\r\n" + 
				" <legend >" + nome+" "+cognome+" Con la matricola matricola :"+matricola+ " </legend>\r\n"+
				"					\r\n" + 
				"      </fieldset> \r\n" + 
				"  </body>\r\n" + 
				"</html>";
	}
	
	
	@RequestMapping(value = "/praticheImpiegato", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam(required = false) String rsaToken) throws UnknownHostException  {
		
		System.out.println();
	String dec=	rsa.bytesToString(rsa.decrypt(rsaToken.getBytes(Charset.forName("UTF-8"))));

	return dec;
}
	
	
	

}
