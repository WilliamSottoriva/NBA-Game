package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.InputException;
import javafx.scene.layout.VBox;


public class ErrorController extends Controller<Validator> {

    @FXML
    private Button okay;

    @FXML
    private Text text;
    
    private String getText() {
        return t;
    }
    
    private String t = "";
    
    @FXML private void initialize() {
        LinkedList<String> errors = this.model.errors();              
        for (String s : errors) {
            t += s;   
        }
        text.setText(t);
    }
    

    
    public Validator getException(){
        return this.model;
    }
    
    @FXML
    public void okay(ActionEvent event){
        stage.close();
    }
}

