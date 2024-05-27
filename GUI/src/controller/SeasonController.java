

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
import model.Season;

public class SeasonController extends Controller<Season> {
    @FXML
    private Button roundButton;

    @FXML
    private Button currentButton;

    @FXML
    private Button gameButton;
    
    @FXML
    private Button resultButton;
    
    @FXML
    private Button closeButton;
    
    public Season getSeason(){
        return this.model;
    }
    
    public String res = "";
    

    
    @FXML
    public void round(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void current(ActionEvent event) {
      try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void game(ActionEvent event) {
        Validator v = new Validator();
        v.clear();
        res = "";
        if (getSeason().getCurrentSchedule().size() == 0) {
                        
            v.addError("No Games to play!\nPlease add games to this round.");

        }
        else {
            res = getSeason().playGame();
            if (res.contains("is the Champion!!")) {
            }
        }
        
        
    
        
        v.addError(res);

        
            try{
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/nba.png"));
                ViewLoader.showStage(v, "/view/error.fxml", "All Games Played!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    @FXML
    public void result(ActionEvent event) {
      try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void close(ActionEvent event) {
        stage.close();
    }
}
