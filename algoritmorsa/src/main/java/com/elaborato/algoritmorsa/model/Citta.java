package com.elaborato.algoritmorsa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="citta")
public class Citta {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	 @OneToMany(mappedBy = "citta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private Set<Impiegato> impiegato;
	
	
	private String cap;
	private String nome;
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<Impiegato> getImpiegato() {
		return impiegato;
	}
	public void setImpiegato(Set<Impiegato> impiegato) {
		this.impiegato = impiegato;
	}
	
	
	
	
}
