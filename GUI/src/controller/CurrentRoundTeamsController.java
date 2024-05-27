

package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Game;
import model.Season;
import model.Team;

import model.Teams;

public class CurrentRoundTeamsController extends Controller<Season> {
    @FXML
    private Button close;
    
    @FXML
    private TableView<Game> scheduledTv;
    
    @FXML
    private TableColumn<Game, String> vs;
    
     @FXML
    private TableColumn<Game, String> team1;
     
     @FXML
    private TableColumn<Game, String> team2;
    
    private StringProperty s = new SimpleStringProperty();


    
    public Season getSeason(){
        return this.model;
    }
    
    
    
    public int getRound() {
        Season s;
        s = (Season) getSeason();
        return s.round() +1;
    }   
    
    @FXML private void initialize() {
            
        
        team1.setCellValueFactory(cellData -> cellData.getValue().team1());
                team2.setCellValueFactory(cellData -> cellData.getValue().team2());


        
        s.set("vs");
        vs.setCellValueFactory(cellData -> s);

               
        
        scheduledTv.setColumnResizePolicy(scheduledTv.CONSTRAINED_RESIZE_POLICY);
    }
       
    @FXML
    public void close(ActionEvent event){
        stage.close();
    }

}
