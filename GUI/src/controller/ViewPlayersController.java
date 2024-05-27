

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
import model.Player;
import model.Players;
import model.Teams;



public class ViewPlayersController extends Controller<Teams> {

    @FXML
    private Button close;
    
    @FXML
    private TextField levelTf;
    
    @FXML
    private TextField nameTf;
    
    @FXML
    private TextField fromTf;
    
    @FXML
    private TextField toTf;
   
    @FXML 
    private TableView<Player> allPlayersTv;
    
    @FXML
    private TableColumn<Player, String> credit;
    
    @FXML
    private TableColumn<Player, String> team;
    
    private final String getLevel() { return levelTf.getText();}
    private final String getName() { return nameTf.getText();}
    private final int getFrom() { 
        if (!fromTf.getText().equals("")) {
            return Integer.parseInt(fromTf.getText());
        }
        return 0;
    }
    
    private final int getTo() { 
        if (!toTf.getText().equals("")) {
            return Integer.parseInt(toTf.getText());
        }
        return 0;
    }    
    
   
    
    public Teams getTeams(){
        return this.model;
    }
    
    @FXML private void initialize() {
        Players p = new Players();
        p.getPlayersList().setAll(this.model.allPlayersList());
        
      
        p.filterList(null, null, 0, 0);
        
        allPlayersTv.setItems(p.getFilteredPlayers());
                credit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString("%.1f"));

        
      
        levelTf.textProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                allPlayersTv.getItems().clear();
                p.getPlayersList().setAll(this.model.allPlayersList());

                if (getName() != null) {
                    p.filterList(getName(), getLevel(), getFrom(), getTo());
                }
                else {
                    p.filterList(getName(), null, getFrom(), getTo());

                }
                
                allPlayersTv.setItems(p.getFilteredPlayers());

            }
        );
        nameTf.textProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                allPlayersTv.getItems().clear();
                p.getPlayersList().setAll(this.model.allPlayersList());

                    
                if (getName() != null) {
                    p.filterList(getName(), getLevel(), getFrom(), getTo());
                }
                else {
                    p.filterList(null, getLevel(), getFrom(), getTo());

                }
                
                allPlayersTv.setItems(p.getFilteredPlayers());

            }
        );
        fromTf.textProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                                allPlayersTv.getItems().clear();
                                                p.getPlayersList().setAll(this.model.allPlayersList());


                if (!fromTf.getText().equals("")) {
                    p.filterList(getName(), getLevel(), getFrom(), getTo());
                }
                else {
                    p.filterList(getName(), getLevel(), 0, getTo());

                }

                allPlayersTv.setItems(p.getFilteredPlayers());

            }
        );
        toTf.textProperty().addListener(
            (observable, oldTeam, newTeam) -> {
                allPlayersTv.getItems().clear();
                                p.getPlayersList().setAll(this.model.allPlayersList());

                if (!toTf.getText().equals("")) {
                    p.filterList(getName(), getLevel(), getFrom(), getTo());
                }
                else {
                    p.filterList(getName(), getLevel(), getFrom(), 0);

                }
                allPlayersTv.setItems(p.getFilteredPlayers());


            }
        );
                        allPlayersTv.getSortOrder().add(team);

        allPlayersTv.setColumnResizePolicy(allPlayersTv.CONSTRAINED_RESIZE_POLICY);

    }
    
     
   

    @FXML
    public void close(ActionEvent event) {
        stage.close();
    }
}
