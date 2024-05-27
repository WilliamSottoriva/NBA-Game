

package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.InputException;
import model.Team;
import model.Teams;

public class AddTeamController extends Controller<Teams> {

    @FXML
    private Button add;
    
    @FXML
    private TextField nameTf;
   
    
    private final String getName() { return nameTf.getText();}
    
    public Teams getTeams(){
        return this.model;
    }
       
    @FXML
    public void add(ActionEvent event){
        Validator v = new Validator();
        v.clear();
          
        
        v.generateErrors(getName());
        if (getTeams().hasTeam(getName())) { v.addError(getName() + " Team already exists");}

        
        if (getTeams().hasTeam(getName()) || !v.isValid(getName())) {
            try {
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(v, "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        else {
            getTeams().addTeam(new Team(getName()));
            stage.close();
        }
    }
}
