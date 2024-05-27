import java.util.ArrayList;
public class Team {
    private String name;
    private Players players;
    public Team(String name)
    {
        this.name = name;
        players = new Players();
    }

    public void manageTeam() {
        System.out.print("Welcome to the " + name + " 's Page! Please make a selection from the menu:\n1. Display team's players.\n2. Add a new player.\n3. Update an existing player.\n4. Delete an existing player.\nR. Return to previous menu.\n");
        char c;
        while ((c = choice()) != 'R') {
            switch(c) {
                case '1': 
                    players.displayPlayers2();
                    break;
                case '2': 
                    players.newPlayer(name);
                    break;
                case '3':
                    players.updatePlayer(name); 
                    break;
                case '4': 
                    players.removePlayer();
                    break;
            }
            System.out.print("Welcome to the " + name + " 's Page! Please make a selection from the menu:\n1. Display team's players.\n2. Add a new player.\n3. Update an existing player.\n4. Delete an existing player.\nR. Return to previous menu.\n");
        }
    }

    public void format() {
        System.out.format(Utils.teamsFormat, name, NoofPlayer(), AvgCredit(), AvgAge());
    }

    public void won(double diff){
        players.updateCredits(diff/5);
       
    }

    public void lost(double diff){
        players.updateCredits(0-diff/5);
    }                

    public void displayPlayers(){
        players.displayPlayers1();
    }

    public ArrayList<Player> getPlayersWithLevel(String level){
        return players.getPlayersWithLevel(level);
    }

    private char choice() {
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }

    private int NoofPlayer() {
        return players.NoofPlayer();
    }

    public double AvgCredit(){
        return players.avgCredit();
    }

    private double AvgAge() {
        return players.avgAge();
    }

    public String name(){
        return name;
    }

    public void addPlayers(Players players){
        this.players = players;
    }

    public boolean hasName(String name) {
        return this.name().equals(name);
    }




}

