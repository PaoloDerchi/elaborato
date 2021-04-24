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
@Table(name="materia")
public class Materia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String argomento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docente_id")
    private Docente docente;
    
    public Materia(){
    }
    
    public Materia(String argomento, Docente docente){
    	this.argomento = argomento;
    	this.docente = docente;
    }
    
    public String getArgomento() {
		return argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}


	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.argomento);
        
        JSONObject companyObj = new JSONObject();
        companyObj.put("nome", this.docente.getNome());
        jsonInfo.put("company", companyObj);
        
        info = jsonInfo.toString();
        return info;
    }
}
