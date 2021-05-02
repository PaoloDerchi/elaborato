package com.elaborato.algoritmorsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.elaborato.algoritmorsa.rsaUtil.RSA;

@SpringBootApplication
public class AlgoritmorsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoritmorsaApplication.class, args);
	}
	
	
	
	@Bean
	public RSA getRsa() {
		return new RSA(10);
	}

}
