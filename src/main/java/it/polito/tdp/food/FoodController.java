/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...\n");
    	
    	Integer n;
    	
    	try {
    		n = Integer.parseInt(this.txtPassi.getText());
    		if(n<1) {
    			this.txtResult.setText("inserire un numero positivo di passi");
    			return;
    		}
    		
    		String partenza = this.boxPorzioni.getValue();
        	
        	if(partenza == null) {
        		this.txtResult.appendText("selezionare un elemento");
        		return;
        	}
        	
    		List<String> cammino = this.model.cercaCammino(partenza, n);
    		
    		if(cammino.size()==0) {
    			this.txtResult.appendText("impossibile trovare un cammino della lunghezza desiderata");
    			return;
    		}
    		
    		this.txtResult.appendText("Cammino trovato di peso: " + this.model.getPesoBest()+"\n");
    		
    		for (String s : cammino) {
    			this.txtResult.appendText(s+"\n");
    		}
    		
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("inserire un numero intero di passi");
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	this.txtCalorie.clear();
    	this.txtPassi.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n");
    	
    	String porzione = this.boxPorzioni.getValue();
    	
    	if(porzione == null) {
    		this.txtResult.appendText("selezionare un elemento");
    		return;
    	}
    	
    	List<Result> adiacenti = this.model.getAdiacenti(porzione);
    	if(adiacenti.size()==0) {
    		this.txtResult.appendText("vertice isolato");
    		return;
    	}
    	for(Result r : adiacenti) {
    		this.txtResult.appendText(r.toString());
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n");
    	
    	Integer calorie;
    	
    	try {
    		calorie = Integer.parseInt(this.txtCalorie.getText());
    		if(calorie<1) {
    			this.txtResult.setText("inserire un numero positivo di calorie");
    			return;
    		}
    		
    		this.model.creaGrafo(calorie);
    		
    		this.txtResult.appendText("grafo creato\n#vertici: "+this.model.getNVertici()+"\n");
    		this.txtResult.appendText("#archi: " +this.model.getNArchi());
    		
    		this.boxPorzioni.getItems().clear();
    		this.boxPorzioni.getItems().addAll(this.model.getVertici());
    		
    		this.btnCammino.setDisable(false);
    		this.btnCorrelate.setDisable(false);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("inserire un numero intero di calorie");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
