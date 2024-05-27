

package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Team;

import model.Teams;

public class TeamsController extends Controller<Teams> {
    
    
    @FXML
    private Button add;

    @FXML
    private Button manage;

    @FXML
    private Button delete;

    @FXML
    private GridPane close;
    
    @FXML 
    private TableView<Team> allTeamsTv;
    
    @FXML
    private TableColumn<Team, String> avgCredit;
    
    @FXML
    private TableColumn<Team, String> No;
    
    @FXML
    private TableColumn<Team, String> avgAge;
    
    private ObservableList<Team> teamsList = FXCollections.observableArrayList();
    private Property<ObservableList<Team>> teamsListProperty;
   
    public Teams getTeams(){
        return this.model;
    }
    
    private Team getSelectedTeam() {
        return allTeamsTv.getSelectionModel().getSelectedItem();
    }
    
    public ObservableList<Team> getCurrentTeams() {
        return getTeams().currentTeams();
    }
    
    @FXML private void initialize() {
    
       
        allTeamsTv.setItems(this.model.currentTeams());
        avgAge.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asString("%.2f"));
        avgCredit.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asString("%.2f"));
        No.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asString());
        allTeamsTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                add.setDisable(newTeam != null);
                manage.setDisable(newTeam == null);
                delete.setDisable(newTeam == null);
            }
        );
        
       allTeamsTv.getSelectionModel().clearSelection();
        allTeamsTv.setColumnResizePolicy(allTeamsTv.CONSTRAINED_RESIZE_POLICY);

    }
       
    @FXML
    public void add(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 allTeamsTv.getSelectionModel().clearSelection();

    }
    @FXML
    public void manage(ActionEvent event) {
      try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams().getTeam(getSelectedTeam().getName()), "/view/ManageTeamView.fxml", "Managing Team: " + getSelectedTeam().getName(), stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
               allTeamsTv.getSelectionModel().clearSelection();

    }
    @FXML
    public void delete(ActionEvent event) {
        getTeams().remove(getSelectedTeam());
                 allTeamsTv.getSelectionModel().clearSelection();

    }
    @FXML
    public void close(ActionEvent event) {
        stage.close();
    }
}
