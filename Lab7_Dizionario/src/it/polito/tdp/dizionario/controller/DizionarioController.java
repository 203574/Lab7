package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.dizionario.model.DizionarioModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController
{
	DizionarioModel dm = new DizionarioModel();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button buttonGenera;

    @FXML
    private Button buttonVicini;

    @FXML
    private Button buttonConnessi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button buttonReset;

    @FXML
    void doGenera(ActionEvent event)
    {
    	buttonVicini.setDisable(false);
        buttonConnessi.setDisable(false);
    	dm.setNumero(Integer.parseInt(txtLettere.getText()));
    	dm.generaGrafo();
    }

    @FXML
    void doReset(ActionEvent event) 
    {
    	buttonVicini.setDisable(true);
        buttonConnessi.setDisable(true);
    	txtLettere.clear();
    	txtParola.clear();
    	txtResult.clear();
    }

    @FXML
    void doTrovaConnessi(ActionEvent event) 
    {
    	String parola = txtParola.getText();
    	if(parola.length()==dm.getNumero())
    	{
    		dm.trovaConnessi(parola);
    		List<String> connessi = dm.getConnessi();
    		txtResult.appendText("Connessi: "+ connessi+"\n");
    	}
    	else
    	{
    		txtResult.setText("Parola troppo lunga");
    	}
    }

    @FXML
    void doTrovaVicini(ActionEvent event)
    {
    	String parola = txtParola.getText();
    	if(parola.length()==dm.getNumero())
    	{
    		List<String> vicini = dm.trovaVicini(parola);
    		txtResult.appendText("Vicini: "+ vicini+"\n");
    	}
    	else
    	{
    		txtResult.setText("Parola troppo lunga");
    	}
    }

    @FXML
    void initialize() 
    {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert buttonGenera != null : "fx:id=\"buttonGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert buttonVicini != null : "fx:id=\"buttonVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert buttonConnessi != null : "fx:id=\"buttonConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert buttonReset != null : "fx:id=\"buttonReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
        buttonVicini.setDisable(true);
        buttonConnessi.setDisable(true);
    }
}
