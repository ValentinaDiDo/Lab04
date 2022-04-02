package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioneDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	CorsoDAO corsoDAO = new CorsoDAO();
	StudenteDAO studenteDAO = new StudenteDAO();
	IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	public List<Studente> getTuttiStudenti(){
		return this.studenteDAO.getTuttiStudenti();
			
	}
	
	public Studente getStudenteMatricola(int matricola) {
		List<Studente> studenti =  this.studenteDAO.getTuttiStudenti();

		for(Studente s : studenti) {
			if(s.getMatricola()==matricola) {
				return s;
			}
		}
		return null;
	}
	
	public List<Studente> getIscrittiCorso(String codins){
		return this.iscrizioneDAO.getIscrittiCorso(codins);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.studenteDAO.getCorsiStudente(matricola);
	}
	public boolean verificaIscrizione(String codins, int matricola) {
		return this.iscrizioneDAO.verificaIscrizione(codins, matricola);
	}
	
	public String iscrivi (int matricola, String codins) {
		return this.iscrizioneDAO.iscrivi(matricola, codins);
	}
}
