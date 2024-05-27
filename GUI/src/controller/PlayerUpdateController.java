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
import javafx.scene.text.Text;
import model.InputException;
import model.Player;
import model.Players;
import model.Team;

public class PlayerUpdateController extends Controller<Team> {

    @FXML
    private TextField nameTf;

    @FXML
    private TextField creditTf;
    
    @FXML
    private TextField ageTf;
     
    @FXML
    private TextField noTf;

    @FXML
    private Button update;
    
    @FXML
    private Button add;
    
    @FXML
    private Button close;  
     
    
    public Team getTeam(){
        return this.model;
    }
    
    private final String getName() { return nameTf.getText();}
    private final Double getCredit() { return Double.parseDouble(creditTf.getText());}
        private final String getCreditString() { return creditTf.getText();}
        
         //  private final String getAgeString() { return ageTf.getText();}
  //  private final String getNoString() { return noTf.getText();}

    private final int getAge() { 
        if (ageTf.getText().equals("")) {
            return -1;
        }
        else {
            return Integer.parseInt(ageTf.getText());
        }
        
    }
    private final int getNo() { 
        if (ageTf.getText().equals("")) {
            return -1;
        }
        else {
             return Integer.parseInt(noTf.getText());
        }
        
        
       
    }
  
    
    
    private  void setName(String value) { nameTf.setText("" + value);}
    private  void setCredit(double value) { creditTf.setText("" + value);}
    private  void setAge(int value) { ageTf.setText("" + value);}
    private  void setNo(int value) { noTf.setText("" + value);}

    private Player player;
    
    @FXML private void initialize() {
        
        player = (Player) stage.getUserData();
        //player = (Player) stage.getUserData();
        add.setDisable(player != null);
        update.setDisable(player == null);
        //stuff should be affected u ntil you press add
        if (player != null) {
            setName(player.getName());
            setCredit(player.getCredit());
            setAge(player.getAge());
            setNo(player.getNo());
        } else {
            setName("");
            setCredit(-1.0);
            setAge(-1);
            setNo(-1);
            player = new Player(getName(), getCredit(), getAge(), getNo());
        }
    }
    
    @FXML
    public void update(ActionEvent event){
        Validator v = new Validator();
        v.clear();
        if (v.isValid(getName(), ""+getCreditString(), ""+getAge(), ""+getNo())) {
            player.update(getName(), getCredit(), getAge(), getNo());
            
             stage.close();
        }
        else {
            v.generateErrors(getName(), ""+getCreditString(), ""+getAge(), ""+getNo());
            
            try {
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/nba.png"));
                ViewLoader.showStage(v, "/view/error.fxml", "Input Errors", stage);
                
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        Player p = new Player("",0.0,0,0);
            p.setTeam(getTeam());
            getTeam().getPlayers().addPlayer(p);
            getTeam().getPlayers().removePlayer(p);
       
    }
    @FXML
    public void add(ActionEvent event) {
        Validator v = new Validator();
        v.clear();
        if (v.isValid(getName(), ""+getCreditString(), ""+getAge(), ""+getNo())) {
            //Players players = (Players) stage.getUserData();
            Player p = new Player(getName(), getCredit(), getAge(), getNo());
            p.setTeam(getTeam());
            getTeam().getPlayers().addPlayer(p);
            //getTeam().getPlayers().getPlayersList.sort();
            stage.close();
        }
        else {
            v.generateErrors(getName(), ""+getCreditString(), ""+getAge(), ""+getNo());
            
            try {
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/view/nba.png"));
                ViewLoader.showStage(v, "/view/error.fxml", "Input Errors", stage);
                
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        Player p = new Player("",0.0,0,0);
            p.setTeam(getTeam());
            getTeam().getPlayers().addPlayer(p);
            getTeam().getPlayers().removePlayer(p);
    }

    @FXML
    public void close(ActionEvent event) {
        stage.close();
    }   
}

