

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
import model.Game;
import model.Season;
import model.Team;

import model.Teams;

public class TeamsRoundController extends Controller<Season> {

    @FXML
    private Button add;

    @FXML
    private Button arrange;

    @FXML
    private ListView<Team> teamsLv;
    
    @FXML
    private TableView<Game> scheduledTv;
    
    @FXML
    private TableColumn<Game, String> team1;
    
    @FXML
    private TableColumn<Game, String> team2;
    
    
    public Season getSeason(){
        return this.model;
    }
    
    public int getRound() {
        Season s;
        s = (Season) getSeason();
        return s.round() +1;
    }     
    
    

    
    private ObservableList<Team> getSelectedTeams() {
        return teamsLv.getSelectionModel().getSelectedItems();
    }
    
    
    
    @FXML private void initialize() {
        teamsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        team1.setCellValueFactory(cellData -> cellData.getValue().team1());
         team2.setCellValueFactory(cellData -> cellData.getValue().team2());

        teamsLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldTeams, newTeams) -> {
                add.setDisable(getSelectedTeams().size() != 2 || teamsLv.getItems().size() == 0);
                arrange.setDisable(teamsLv.getItems().size() != 0);

            }
        );
        scheduledTv.setColumnResizePolicy(scheduledTv.CONSTRAINED_RESIZE_POLICY);
    }
       
    @FXML
    public void add(ActionEvent event){
        getSeason().addTeams(getSelectedTeams());
    }
    @FXML
    public void arrange(ActionEvent event) {
        stage.close();
    }

}
