/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
//	Set<Corso> corsi = (HashSet<Corso>) this.model.getTuttiICorsi();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnVerificaIscrittoCorso;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ComboBox<String> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCercaCorsi(ActionEvent event) {

    	//cerco i corsi a cui è iscritta la matricola passata come parametro
    	String matr = txtMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtResult.setText("inserisci un periodo numerico!");
    		return;
    	}
    	List<Corso> corsi = model.getCorsiStudente(matricola);
    	if(corsi.size()!=0) {
    		for(Corso c : corsi) {
    			txtResult.appendText(c.getCodins()+"\t"+c.getCrediti()+"\t"+c.getNome()+"\t"+c.getPd()+"\n");
    		}
    	}else
    		txtResult.setText("Questa matricola non è iscritta a nessun corso, o non esiste");
    }

    @FXML
    void handleCercaIscritti(ActionEvent event) {

    	String scelta = cmbCorsi.getValue();
    	String codins = scelta.substring(0,7);
    
    	//metodo per cercare iscritti
    	List<Studente> studenti = this.model.getIscrittiCorso(codins);
    	
    	for(Studente s : studenti) {
    		txtResult.appendText(s.getMatricola()+"\t"+s.getCognome()+"\t"+s.getNome()+"\t"+s.getCDS()+"\n");
    	}
    }

    @FXML
    void handleCompletamentoMatricola(ActionEvent event) {
    	String matr = txtMatricola.getText();
    	//verifico sia corretta
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtResult.setText("inserisci una matricola numerica!");
    		return;
    	}
    	
    	Studente s = model.getStudenteMatricola(matricola);
    	if(s!=null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}else {
    		txtResult.setText("STUDENTE INESISTENTE");
    	}
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    	//iscrizione di uno studente al corso
    	String matricolaS = txtMatricola.getText();
    	//verifico correttezza matricola
    	if(matricolaS.length()==0 || matricolaS.equals(null)) 
    		txtResult.setText("Per favore, inserire matricola");
    	
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matricolaS);
    	}catch(NumberFormatException e) {
    		txtResult.setText("inserisci una matricola numerica!");
    		return;
    	}
    	//verifico esistenza studente nel sistema
    	Studente s = this.model.getStudenteMatricola(matricola);
    	if(s.equals(null))
    		txtResult.appendText("La matricola non è presente nel sistema");
    	
    	//prendo il corso
    	String corso = cmbCorsi.getValue();
    	String codins = corso.substring(0,7);
    	
    	//iscrivo
    	String result = this.model.iscrivi(matricola, codins);
    	txtResult.appendText(result);
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	cmbCorsi.getItems().clear();
    	cmbCorsi.getItems().add(" ");
    	for(Corso c : model.getTuttiICorsi()) {
        	cmbCorsi.getItems().add(c.getCodins()+" "+c.getNome()); }
    }

    @FXML
    void handleVerificaIscrizione(ActionEvent event) {
    	String corso = cmbCorsi.getValue();
    	String codins = corso.substring(0,7);
    	String matr = txtMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtResult.setText("inserisci un periodo numerico!");
    		return;
    	}
    	
    	boolean b = model.verificaIscrizione(codins, matricola);
    	if(b == true) {
    		txtResult.appendText("LO STUDENTE E' ISCRITTO AL CORSO");
    	}else
    		txtResult.appendText("LO STUDENTE NON E' ISCRITTO AL CORSO");
    	
    }

    @FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVerificaIscrittoCorso != null : "fx:id=\"btnVerificaIscrittoCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		cmbCorsi.getItems().add(" ");
		for(Corso c : model.getTuttiICorsi()) {
        	cmbCorsi.getItems().add(c.getCodins()+" "+c.getNome()); }
		
	}

}

