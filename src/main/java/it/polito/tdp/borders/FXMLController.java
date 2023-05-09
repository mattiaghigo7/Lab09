
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.control.ComboBox;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
	@FXML
	private Button btnCalcolaConfini;

    @FXML
    private Button btnCalcolaRaggiungibili;
    
    @FXML
    private Button btnCreaGrafo;
	    
	@FXML
    private ComboBox<Country> cmbStati;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	String input = this.txtAnno.getText();
    	Integer num = 0;
    	
    	if(input == "") {
    		this.txtResult.setText("Nessun anno inserito! Inserire un valore tra 1816 e 2016");
    		return;
    	}
    	try {
    		Integer temp = Integer.parseInt(input);
    		if(temp>=1816 && temp<=2016) {
    			num = temp;
    		} else {
    			this.txtAnno.clear();
    			this.txtResult.setText("Inserire un valore tra 1816 e 2016");
    			return;
    		}
    	} catch (NumberFormatException e){
    		this.txtAnno.clear();
    		this.txtResult.setText("Inserire un valore tra 1816 e 2016");
    		e.printStackTrace();
    		return;
    	}
    	
    	model.creaGrafo(num);
    	this.txtResult.appendText("Grafo creato!");
    	this.btnCalcolaConfini.setDisable(false);
    	this.cmbStati.setDisable(false);
    	this.btnCalcolaRaggiungibili.setDisable(false);
    	this.txtAnno.setDisable(true);
    	this.btnCreaGrafo.setDisable(true);
    	
    	Set<Country> stati = this.model.getVertici();
    	this.cmbStati.getItems().addAll(stati);
    }
    
    @FXML
    void doCalcolaRaggiungibili(ActionEvent event) {
    	this.txtResult.clear();
    	    	
    	Country input = this.cmbStati.getValue();
    	if(input!=null) {
    		List<Country> ragg =this.model.raggiungibili(input);
    		this.txtResult.appendText("Gli Stati raggiungibili dallo Stato "+input+" sono:\n");
    		for(Country c : ragg) {
    			if(!c.equals(input)) {
    				this.txtResult.appendText(""+c+"\n");
    			}
    		}
    	} else {
    		this.txtResult.appendText("Devi selezionare uno Stato");
    	}
    }
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	this.txtResult.clear();
    	
    	List<String> gradi = model.stampaGradoStati();
    	for(String s : gradi) {
    		this.txtResult.appendText(s+"\n");
    	}
    	
    	this.txtResult.appendText("\nIl numero di componenti connesse del grafo è: ");
    	this.txtResult.appendText(""+model.calcolaConnesse());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
