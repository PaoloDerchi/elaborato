
package com.elaborato.algoritmorsa.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
import com.elaborato.algoritmorsa.rsaUtil.RSA;

@RestController
//@RequestMapping("/rsa")
public class ElaboratoController {

	@Value("classpath:homePage.html")
	private Resource homePage;

	@Value("classpath:DocenteSconosciuto.html")
	private Resource docenteSconoscito;

	@Value("classpath:DocenteTrovato.html")
	private Resource docenteTrovato;

	@Value("classpath:homeRichiestaMaterie.html")
	private Resource homeRichiestaMaterie;

	@Value("classpath:MaterieDiCompetenza.html")
	private Resource MaterieDiCompetenza;

	@Autowired
	private RSA rsa;

	@Autowired
	private DocenteRepository docenteRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String portalAccess(HttpServletRequest request) throws UnsupportedEncodingException, IOException {

		String userAgent = request.getHeader("User-Agent");
		Reader reader = new InputStreamReader(homePage.getInputStream());
		String paginaHome = FileCopyUtils.copyToString(reader);

		return paginaHome;
	}

	@RequestMapping(value = "/logInImpiegato", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String cognome, @RequestParam(required = false) String matricola,
			HttpServletRequest request) throws IOException {
		/*
		 * Ricerca del Docente per Nome, Cognome e matricola
		 */
		Docente docente = docenteRepository.findByNomeAndCognomeAndMatricola(nome.toLowerCase(), cognome.toLowerCase(),
				Integer.valueOf(matricola));

		if (docente == null) {
			/*
			 * Utente non trovato
			 */
			Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
			String docenteSconosicuto = FileCopyUtils.copyToString(reader);
			return docenteSconosicuto;
		}

		Reader reader = new InputStreamReader(docenteTrovato.getInputStream());
		String docenteTrovato = FileCopyUtils.copyToString(reader);
		docenteTrovato = docenteTrovato.replace("dd%nomeU%dd", docente.getNome());
		docenteTrovato = docenteTrovato.replace("dd%congomeU%dd", docente.getCognome());
		/*
		 * Esecuzione Encript RSA
		 */
		String encryptDocente = rsa
				.RSAencrypt(docente.getNome() + "_" + docente.getCognome() + "_" + docente.getMatricola());
		docenteTrovato = docenteTrovato.replace("dd%tokenU%dd", encryptDocente);

		return docenteTrovato;

	}

	@RequestMapping(value = "/homeRichiestaMaterie", method = RequestMethod.GET)
	public String richiestaMaterie() throws IOException {
		Reader reader = new InputStreamReader(homeRichiestaMaterie.getInputStream());
		String paginaHome = FileCopyUtils.copyToString(reader);
		return paginaHome;
	}

	@RequestMapping(value = "/materieDocente", method = RequestMethod.GET)
	public String logInImpiegato(@RequestParam(required = false) String token) throws IOException {

		String decript;
		try {
			decript = rsa.RSAdecrypt(token);
		} catch (NumberFormatException e) {
			Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
			String docenteSconosicuto = FileCopyUtils.copyToString(reader);
			return docenteSconosicuto;
		}

		String[] val = decript.split("_");

		if (val.length != 3) {
			Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
			String docenteSconosicuto = FileCopyUtils.copyToString(reader);
			return docenteSconosicuto;
		}

		String nome = val[0];
		String cognome = val[1];
		String matricola = val[2];

		try {
			Integer.parseInt(matricola);
		} catch (NumberFormatException e) {
			Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
			String docenteSconosicuto = FileCopyUtils.copyToString(reader);
			return docenteSconosicuto;
		}

		Docente docente = docenteRepository.findByNomeAndCognomeAndMatricola(nome.toLowerCase(), cognome.toLowerCase(),
				Integer.valueOf(matricola));

		if (docente == null) {
			Reader reader = new InputStreamReader(docenteSconoscito.getInputStream());
			String docenteSconosicuto = FileCopyUtils.copyToString(reader);
			return docenteSconosicuto;
		}

		Set<Materia> materie = docente.getMateria();
		String htmldelleMaterie = "";
		for (Materia materia : materie) {
			htmldelleMaterie += "<tr> <td  style=\"text-align:center;\"  colspan=\"2\" class=\"materia\">";
			htmldelleMaterie += materia.getArgomento() + "</td> </tr>";
		}

		Reader reader = new InputStreamReader(MaterieDiCompetenza.getInputStream());
		String htmlritornoMaterie = FileCopyUtils.copyToString(reader);
		htmlritornoMaterie = htmlritornoMaterie.replace("dd%materieU%dd", htmldelleMaterie);

		htmlritornoMaterie = htmlritornoMaterie.replace("dd%nomeU%dd", docente.getNome());
		htmlritornoMaterie = htmlritornoMaterie.replace("dd%congomeU%dd", docente.getCognome());

		System.out.println(decript);

		return htmlritornoMaterie;
	}

}
