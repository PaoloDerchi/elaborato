package com.elaborato.algoritmorsa;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.elaborato.algoritmorsa.rsaUtil.RSAOld;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AlgoritmorsaApplication.class);
	}
	
	
	@Bean
	public RSAOld getRestTemplate() {
		RSAOld rsa = new RSAOld();
		return rsa;
	}

}
