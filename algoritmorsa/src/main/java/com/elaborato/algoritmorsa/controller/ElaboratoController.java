package com.elaborato.algoritmorsa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/rsa")
public class ElaboratoController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	/*public String logInImpiegato(@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,HttpServletRequest request
		) throws UnknownHostException  {*/
		
		public String logInImpiegato(HttpServletRequest request) throws UnknownHostException  {
		
		String remoteHost = "";
		String remoteAddr = "";
		//request.getHeader("User-Agent");
		
		//remoteHost=	request.getRemoteUser();
		
        /*if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        } 
		String referrer = request.getHeader("referer"); */
		//remoteHost= 	request.getRemoteAddr();
        
		
       // remoteHost= InetAddress.getLoopbackAddress().getHostName();
		String a = "<!DOCTYPE html>\r\n" + 
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
				"      function CancelEntry() {\r\n" + 
				"		document.getElementById(\"myForm\").reset();\r\n" + 
				"		EraseMessage();\r\n" + 
				"      }\r\n" + 
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
				"			document.getElementById(\"messaggistica\").innerHTML +=  \r\n" + 
				"              ' <p align=\"center\" id=\"messaggioDiSuccesso\"><font face=\"Verdana\" color=\"blue\" size=\"10\">Inserimento Eseguito con successo!</font></p>'; 	\r\n" + 
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
				"      <form action=\"\" method=\"get\"  id=\"myForm\" name=\"myForm\">\r\n" + 
				"      <fieldset> \r\n" + 
				"        <legend>Device :"+request.getHeader("User-Agent")+"</legend>\r\n" + 
				"        <legend>Log In Impiegato</legend>\r\n"+
				"					<table border=\"2\" >\r\n" + 
				"						<tr>\r\n" + 
				"							<!--Per l'input di testo viene eseguita la funzione removeNumber().\r\n" + 
				"							Per ogni input di testo viene settata la massima lunghezza con l'attributo maxlength\r\n" + 
				"							-->\r\n" + 
				"						 <td class=\"testo\">Nome</td> <td> <input type=\"text\" name=\"nome\"  maxlength=\"25\"  onchange=\"removeNumber(this)\" onkeyup=\"removeNumber(this)\" title=\"Inserisci qui il tuo nome con al massimo 25 caratteri\"/> </td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>    \r\n" + 
				"						 <td class=\"testo\">Cognome</td> <td> <input type=\"text\" name=\"cognome\" maxlength=\"25\"  onchange=\"removeNumber(this)\" onkeyup=\"removeNumber(this)\"  title=\"Inserisci qui il tuo cognome con al massimo 25 caratteri\" /></td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"						<td class=\"testo\">Matricola</td> <td><input type=\"text\" name=\"matricola\" maxlength=\"8\"  title=\"Inserisci qui la tua matricola con al massimo 8 caratteri\" /></td>\r\n" + 
				"						</tr>\r\n" + 
				"				  </table>\r\n" + 
				"      </fieldset> \r\n" + 
				"      <table>\r\n" + 
				"        <tr>\r\n" + 
				"          <td></td>\r\n" + 
				"          <td>\r\n" + 
				"		  <!--La funzione Validate() esegue una Validazione sui campi inseriti-->\r\n" + 
				"		  <a href=\"javascript:Validate();\" class=\"bottone\">Invio</a>\r\n" + 
				"		  <!--La funzione CancelEntry() esegue un refresh della form-->\r\n" + 
				"		  <a href=\"javascript:CancelEntry();\" class=\"bottone\">Annulla</a>\r\n" + 
				"        </td>\r\n" + 
				"        <td></td>\r\n" + 
				"        </tr>\r\n" + 
				"      </table>\r\n" + 
				"	</form>\r\n" + 
				"	<table >\r\n" + 
				"		<tr ><div id=\"messaggistica\"></div>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr >\r\n" + 
				"			<!--Riferimenti all'autore del software-->\r\n" + 
				"			<td align=\"justify\">	\r\n" + 
				"			<dl>\r\n" + 
				"				<dd >Creato da</dd>\r\n" + 
				"				<dd><address><a href=\"mailto:derchipaolo73@gmail.com\">Paolo Derchi</a></address></dd>\r\n" + 
				"				<dd>Ultimo aggiornamento</dd>\r\n" + 
				"				<dd>Venerdì 4 Giugno 2021</time></dd>\r\n" + 
				"				<dd>\r\n" + 
				"			</dl>\r\n" + 
				"			<small>Tutti i contenuti sono prottetti dalla licenza creative commons</small>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td align=\"center\" > <img src=\"https://www.istitutimezzacapo.it/website/wp-content/uploads/2018/09/istituti-mezzacapo.jpg\" width=\"200\" height=\"100\"></td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		
		
		
		
		
		return a;
		
	}
	

}
