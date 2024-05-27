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
import javafx.scene.AccessibleAttribute;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.InputException;
import model.Player;
import model.Team;

public class ManageTeamController extends Controller<Team> {

    @FXML
    private Button add;

    @FXML
    private Button update;
    
    @FXML
    private Button delete;
     
    @FXML
    private Button close;
    
    
    
    @FXML TextField teamName;

    @FXML 
    private TableView<Player> manageTeamsTv;
     
    @FXML
    private TableColumn<Player, String> credit;
    
    @FXML
    private TableColumn<Player, String> age;
    
    @FXML
    private TableColumn<Player, String> No;
    
    @FXML
    private TableColumn<Player, String> name;
     
    
    public Team getTeam(){
        return this.model;
    }
    
    private final String getNameTf() { return teamName.getText();}

    
    private Player getSelectedPlayer() {
        return manageTeamsTv.getSelectionModel().getSelectedItem();
    }
    
    private ObservableList<Player> playersList = FXCollections.observableArrayList();
    private Property<ObservableList<Player>> playersListProperty;
    
    
    @FXML private void initialize() {
     

           
                   
           teamName.setText(getTeam().getName());
           
               

        credit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString("%.1f"));
        age.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        No.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());

        manageTeamsTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                add.setDisable(newTeam != null);
                update.setDisable(newTeam == null);
                delete.setDisable(newTeam == null);

            }
        );
        
        
        
       
        
       manageTeamsTv.getSortOrder().add(name);
        
     
     
        
        
        manageTeamsTv.setColumnResizePolicy(manageTeamsTv.CONSTRAINED_RESIZE_POLICY);

    }
    

    
    @FXML public void change() {
        getTeam().setName(teamName.getText());
    }
    
    @FXML
    public void add(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setUserData(getSelectedPlayer());
            ViewLoader.showStage(getTeam(), "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
                   manageTeamsTv.getSortOrder().add(name);

        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         manageTeamsTv.getSelectionModel().clearSelection();
    }
    @FXML
    public void update(ActionEvent event) {
      try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setUserData(getSelectedPlayer());
                   manageTeamsTv.getSortOrder().add(name);

            ViewLoader.showStage(getTeam(), "/view/PlayerUpdateView.fxml", "Updating Player: " + getSelectedPlayer().getName(), stage);
         
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
               manageTeamsTv.getSelectionModel().clearSelection();

    }
    @FXML
    public void delete(ActionEvent event) {
        getTeam().getPlayers().removePlayer(getSelectedPlayer());
                 manageTeamsTv.getSelectionModel().clearSelection();
                        manageTeamsTv.getSortOrder().add(name);


    }
    @FXML
    public void close(ActionEvent event) {
        Validator v = new Validator();
        v.clear();

        
        if (!v.isValid(getNameTf())) {
            v.generateErrors(getNameTf());
            try {
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(v, "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            getTeam().setName(getNameTf());
            stage.close();

        }
        
    }   
}

