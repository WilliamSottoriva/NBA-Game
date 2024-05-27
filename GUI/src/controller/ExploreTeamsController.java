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
import model.Teams;


public class ExploreTeamsController extends Controller<Teams> {

    @FXML
    private Button teamsMenuButton;

    @FXML
    private Button viewPlayersButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane buttonGrid;
    
    public Teams getTeams(){
        return this.model;
    }
    
    @FXML
    public void teamsMenu(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/TeamsTable.fxml", "Teams Menu", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex); 
              
        }
    }
    @FXML
    public void viewPlayers(ActionEvent event) {
      try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "Players" , stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void close(ActionEvent event){
        stage.close();
    }
}

