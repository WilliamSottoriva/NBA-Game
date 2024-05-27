import java.util.ArrayList;
public class Teams {
    public ArrayList<Team> teams;
    public Teams()
    {
        teams = new ArrayList<Team>();

        Players sunsPlayers = new Players();
        Player p = new Player("Devin Booker", 2500.0, 26, 1);
        p.setTeam("Suns");
        sunsPlayers.addPlayer(p);
        p = new Player("Chris Paul", 1500.0, 37, 3);
        p.setTeam("Suns");
        sunsPlayers.addPlayer(p);
        p = new Player("Deandre Ayton", 2000.0, 24, 22);
        p.setTeam("Suns");
        sunsPlayers.addPlayer(p);
        p = new Player("Kevin Durant", 3000.0, 34, 35);
        p.setTeam("Suns");
        sunsPlayers.addPlayer(p);
        p = new Player("Terrence Ross", 1000.0, 32, 8);
        p.setTeam("Suns");
        sunsPlayers.addPlayer(p);

        Players bullsPlayers = new Players();
        p = new Player("Andre Drummond", 1500.0, 29, 3);
        p.setTeam("Bulls");
        bullsPlayers.addPlayer(p);
        p = new Player("Zach LaVine", 3000.0, 28, 8);
        p.setTeam("Bulls");
        bullsPlayers.addPlayer(p);
        p = new Player("Dalen Terry", 900.0, 20, 25);
        p.setTeam("Bulls");
        bullsPlayers.addPlayer(p);
        p = new Player("Terry Taylor", 1000.0, 23, 32);
        p.setTeam("Bulls");
        bullsPlayers.addPlayer(p);
        p = new Player("Carlik Jones", 800.0, 25, 22);
        p.setTeam("Bulls");
        bullsPlayers.addPlayer(p);

        Players hawksPlayers = new Players();
        p = new Player("Trae Young", 2200.0, 24, 11);
        p.setTeam("Hawks");
        hawksPlayers.addPlayer(p);
        p = new Player("John Collins", 2000.0, 25, 20);
        p.setTeam("Hawks");
        hawksPlayers.addPlayer(p);
        p = new Player("Aaron Holiday", 800.0, 26, 3);
        p.setTeam("Hawks");
        hawksPlayers.addPlayer(p);
        p = new Player("Jalen Johnson", 1000.0, 21, 1);
        p.setTeam("Hawks");
        hawksPlayers.addPlayer(p);
        p = new Player("Trent Forrest", 1200.0, 24, 2);
        p.setTeam("Hawks");
        hawksPlayers.addPlayer(p);

        Players netsPlayers = new Players();
        p = new Player("Mikal Bridges", 2400.0, 26, 1);
        p.setTeam("Nets");
        netsPlayers.addPlayer(p);
        p = new Player("Ben Simmons", 2000.0, 26, 10);
        p.setTeam("Nets");
        netsPlayers.addPlayer(p);
        p = new Player("Patty Mills", 900.0, 34, 8);
        p.setTeam("Nets");
        netsPlayers.addPlayer(p);
        p = new Player("Joe Harris", 1200.0, 31, 12);
        p.setTeam("Nets");
        netsPlayers.addPlayer(p);
        p = new Player("Seth Curry", 1900.0, 32, 30);
        p.setTeam("Nets");
        netsPlayers.addPlayer(p);

        Team suns = new Team("Suns");
        Team bulls = new Team("Bulls");
        Team hawks = new Team("Hawks");
        Team nets = new Team("Nets");

        suns.addPlayers(sunsPlayers);
        bulls.addPlayers(bullsPlayers);
        hawks.addPlayers(hawksPlayers);
        nets.addPlayers(netsPlayers);

        teams.add(suns);
        teams.add(bulls);
        teams.add(hawks);
        teams.add(nets);
    }

    public void teamsPage() {
        System.out.print("Welcome to the Teams Page! Please make a selection from the menu:\n1. Display all teams.\n2. Display all players.\n3. Add a new team.\n4. Manage an existing team.\n5. Delete an existing team.\n6. Display all players by an level.\nR. Return to previous menu.\n");
        char c;
        while ((c = choice()) != 'R') {
            switch(c) {
                case '1': 
                    displayTeams(); break;
                case '2': 
                    displayPlayers1(); break;
                case '3': addTeam(); break;
                case '4': manageTeam(); break;
                case '5': removeTeam(); break;
                case '6': displayByLevel(); break;
            }
        System.out.print("Welcome to the Teams Page! Please make a selection from the menu:\n1. Display all teams.\n2. Display all players.\n3. Add a new team.\n4. Manage an existing team.\n5. Delete an existing team.\n6. Display all players by an level.\nR. Return to previous menu.\n");
        }
    } 

    private void displayTeams(){
        Utils.teamsHeader();
        for (Team team : teams) {
            team.format();
        }
        Utils.teamTableEnd();
    }

    private void displayPlayers1(){
        Utils.DisplayPlayerFromAllTeamsHeader();
        for (Team team : teams) {
            team.displayPlayers();
            Utils.DisplayPlayerFromAllTeamsEnd();
        }
    }

    private void addTeam(){
        System.out.print("Please enter the name of the team: ");
        String name = In.nextLine();
        while (findTeamFromName(name) != null) {
            System.out.print("Team " + name + " already exist! Please enter a new name: ");
            name = In.nextLine();
        }
        Players p = new Players();
        Team t = new Team(name);
        t.addPlayers(p);
        teams.add(t);
        System.out.println("Team " + name + " added!");  
    }

    private void manageTeam() {
        String teamName;
        System.out.print("Please enter the team's name that you want to manage: ");
        teamName = In.nextLine();
        Team t = findTeamFromName(teamName);
        if (t == null) {
            System.out.println("Team does not exist!");
        }
        else {
            t.manageTeam();
        } 
    }

    private void removeTeam(){
        System.out.print("Please enter the team's name that you want to delete: ");
        String teamName = In.nextLine();
        Team t = findTeamFromName(teamName);
        if (t == null) {
            System.out.println("The team you want to delete does not exist!");
        }
        else {
            teams.remove(t);
            System.out.println("The team " + teamName + " has been deleted.");
        }
    }

    private void displayByLevel() {
        System.out.print("Please enter the player's level that you want to view: ");
        String level = In.nextLine();
        ArrayList<Player> playersWithLevel = playersWithLevel(level);
        while (playersWithLevel.size() == 0) {
            System.out.print("No such level! Please re-enter the level: ");
            level = In.nextLine();
            playersWithLevel = playersWithLevel(level);
        }
        Utils.DisplayPlayerFromAllTeamsHeader();
        for (Player player : playersWithLevel) {
            player.displayPlayer1();
        }
        Utils.DisplayPlayerFromAllTeamsEnd();
    }

    private Team findTeamFromName(String name) {
        for (Team team : teams) {
            if (team.hasName(name)){
                return team;
            }
        }
        return null;
    }

    private ArrayList<Player> playersWithLevel(String level){
        ArrayList<Player> playersWithLevel = new ArrayList<Player>();
        for (Team team : teams) {
            for (Player player : team.getPlayersWithLevel(level)) {
                playersWithLevel.add(player);
            }
        }
        return playersWithLevel;
    }

    public ArrayList<Team> teams() {
        return teams;
    }

    private char choice() {
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }


}


