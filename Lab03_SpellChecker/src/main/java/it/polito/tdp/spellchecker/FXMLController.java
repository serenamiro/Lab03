package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Dictionary dictionary;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboSpell;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtOutput;

    @FXML
    private TextField txtFrase;

    @FXML
    private Button btnClear;

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.clear();
    	txtFrase.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	txtOutput.clear();
    	dictionary.loadDictionary(comboSpell.getSelectionModel().getSelectedItem());
    	String inserita = txtInput.getText();
    	List<String> input = dictionary.ordineInput(inserita);
    	List<RichWord> output = dictionary.spellCheckTest(input);
    	String s = "";
    	if(output != null) {
    		for(RichWord w : output) {
    			s += w.getParolaInserita()+"\n";
    		}
    		txtOutput.appendText(s);
        	txtFrase.appendText("The text contains "+output.size()+" errors.\n");
        	return;
    	} else {
    		txtFrase.appendText("The text contains 0 errors.\n");
    	}
    }

    @FXML
    void initialize() {
        assert comboSpell != null : "fx:id=\"comboSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFrase != null : "fx:id=\"txtFrase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        comboSpell.getItems().addAll("English", "Italian");
        comboSpell.setValue("English");
    }

	public void setDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		this.dictionary = dictionary;
	}
}

