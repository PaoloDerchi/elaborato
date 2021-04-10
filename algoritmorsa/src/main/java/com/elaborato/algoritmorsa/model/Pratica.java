package com.elaborato.algoritmorsa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="pratica")
public class Pratica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String argomento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "impiegato_id")
    private Impiegato impiegato;
    
    public Pratica(){
    }
    
    public Pratica(String argomento, Impiegato impiegato){
    	this.argomento = argomento;
    	this.impiegato = impiegato;
    }
    
    public String getArgomento() {
		return argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}

	public Impiegato getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(Impiegato impiegato) {
		this.impiegato = impiegato;
	}

	public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.argomento);
        
        JSONObject companyObj = new JSONObject();
        companyObj.put("nome", this.impiegato.getNome());
        jsonInfo.put("company", companyObj);
        
        info = jsonInfo.toString();
        return info;
    }
}
