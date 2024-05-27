import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;
    public Players()
    {
        Players = new ArrayList<Player>();
    }

    public void updateCredits(double diff) {
        for (Player player : Players) {
            player.updateCredits(diff);
        }
    }

    public void addPlayer(Player player) {
        Players.add(player);
    }

    public double avgCredit() {
        double sum = 0;
        double n = 0;
        for (Player player : Players) {
            n += 1;
            sum += player.credit();
        }
        if (n != 0) {
            return sum/n;
        }
        return 0;
    }

    public int NoofPlayer() {
        return Players.size();
    }

    public double avgAge() {
        double sum = 0;
        double n = 0;
        for (Player player : Players) {
            n += 1;
            sum += player.age();
        }
        if (n != 0) {
            return sum/n;
        }
        return 0; 
    }

    public void displayPlayers1(){
        for (Player player: Players) {
            player.displayPlayer1();
        }
    }

    public void displayPlayers2(){
        Utils.playerHeader();
        for (Player player: Players) {
            player.displayPlayer2();
        }
        Utils.playerTableEnd();
    }

    public void newPlayer(String teamName){
        System.out.print("Please enter the player's name: ");
        String name = In.nextLine();
        System.out.print("Please enter the player's credit: ");
        Double credit = In.nextDouble();
        System.out.print("Please enter the player's age: ");
        int age = In.nextInt();
        System.out.print("Please enter the player's No: ");
        int No = In.nextInt();
        String eName = existingNo(No);
        while (eName != null) {
            System.out.print("This No has been occupied by: " + eName + ". Please re-enter the No: ");
            No = In.nextInt();
            eName = existingNo(No);
        }        
        Player p = new Player(name, credit, age, No);
        p.setTeam(teamName);
        Players.add(p);
        System.out.println("Player " + name + " added!");
    }

    private String existingNo(int No){
        for (Player player: Players) {
            if (player.hasNo(No)) {
                return player.name();
            }
        }  
        return null;
    }

    public void updatePlayer(String teamName){
        System.out.print("Please enter the player's name: ");
        String name = In.nextLine();
        Player p = findPlayer(name);
        if (p == null) {
            System.out.println("Player does not exist.");
        }
        else {
            Players.remove(p);
            System.out.print("Please enter the name: ");
            name = In.nextLine();
            System.out.print("Please enter the credit: ");
            Double credit = In.nextDouble();
            System.out.print("Please enter the age: ");
            int age = In.nextInt();
            System.out.print("Please enter the No: ");
            int No = In.nextInt();
            String eName = existingNo(No);
            while (eName != null) {
                System.out.print("This No has been occupied by: " + eName + ". Please re-enter the No: ");
                No = In.nextInt();
                eName = existingNo(No);
            }        
            p = new Player(name, credit, age, No);
            p.setTeam(teamName);
            Players.add(p);
            System.out.println("Player information updated.");
        }
    }

    private Player findPlayer(String name){
        for (Player player: Players) {
            if (player.hasName(name)) {
                return player;
            }
        }  
        return null;
    }

    public void removePlayer(){
        System.out.print("Please enter the player's name: ");
        String name = In.nextLine();
        Player p = findPlayer(name);
        if (p == null) {
            System.out.println("Player does not exist.");
        }
        else {
            Players.remove(p);
            System.out.println("Player deleted.");
        }
        
    }

    public ArrayList<Player> getPlayersWithLevel(String level){
        ArrayList<Player> playersWithLevel = new ArrayList<Player>();
        for (Player player : Players) {
            if (player.hasLevel(level)) {
                playersWithLevel.add(player);
            }
        }
        return playersWithLevel;
    }
}

