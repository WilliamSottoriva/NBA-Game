

package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Game;
import model.Record;
import model.Season;
import model.Team;

import model.Teams;

public class RecordController extends Controller<Season> {

    @FXML
    private Button close;
    
    @FXML
    private TableView<Record> recordTv;
    
    @FXML
    private Text roundNo;
    
    
    @FXML
    private TableColumn<Record,String> round;
    
    @FXML
    private TableColumn<Record,String> game;
    
    @FXML
    private TableColumn<Record,String> winTeam;
    
    @FXML
    private TableColumn<Record,String> loseTeam;

   
    public Season getSeason(){
        return this.model;
    }
    
    
    @FXML private void initialize() {
        recordTv.setItems(getSeason().record());
        
        recordTv.setColumnResizePolicy(recordTv.CONSTRAINED_RESIZE_POLICY);

        
        round.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asString());
        game.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asString());
        winTeam.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        loseTeam.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());


        
    }
       
    @FXML
    public void close(ActionEvent event){
        stage.close();
    }

}
