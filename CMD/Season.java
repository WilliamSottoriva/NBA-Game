import java.util.ArrayList;
public class Season {
    private ArrayList<Game> schedule;
    private ArrayList<Team> currentTeamList;
    private Integer round;
    private ArrayList<Record> records;

    public Season()
    {
        schedule = new ArrayList<Game>();
        currentTeamList  = new ArrayList<Team>();
        round = 1;
        records = new ArrayList<Record>();
    }

    public void seasonPage(){
        System.out.print("Welcome to the season page! Please make a selection from the menu:\n1. Add a team to the round.\n2. Display the current round.\n3. Play games.\n4. Display the game result records.\nR. Return to previous menu.\n");
        char c;
        while ((c = choice()) != 'R') {
            switch(c) {
                case '1': addToRound(); break;
                case '2': displayRound(); break;
                case '3': playGames(); break;
                case '4': results(); break;
                default: System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu."); break;
            }
            System.out.print("Welcome to the season page! Please make a selection from the menu:\n1. Add a team to the round.\n2. Display the current round.\n3. Play games.\n4. Display the game result records.\nR. Return to previous menu.\n");
        }
    }

    private void addToRound() {
        int numTeams = currentTeamList.size();
        for (int i = 0; i < numTeams; i++) { 
            displayTeams();
            String teamName = In.nextLine();
            while (findTeamFromName(teamName) == null) {
                System.out.println("No such team! Please try again");
                displayTeams();
                teamName = In.nextLine();
            }
            Game gameToAddTo = getGame();
            Team t = findTeamFromName(teamName);
            gameToAddTo.addTeam(t, teamName);
            currentTeamList.remove(t);
        }
    }

    private void displayRound() {
        Utils.GameHeader();
        for (Game game : schedule) {
            game.displayRound();
        }
        Utils.GameEnd();
    }

    private void playGames() {
        if (schedule.size() == 0) {
            System.out.println("No game in the current round, please add teams to the round first!");
        }
        else {
            ArrayList<Team> newCurrentTeamList = new ArrayList<Team>();
            Team winner = null;
            ArrayList<Team> results = new ArrayList<Team>();
            for (Game game : schedule) {
                results = game.playGame();
                newCurrentTeamList.add(results.get(0));
                records.add(new Record(results.get(0).name(), results.get(1).name(), game.term, round));   
            }
            currentTeamList = newCurrentTeamList;
            System.out.println("All games finished! You can use 4 to check the results.");
            if (schedule.size() == 1) {
                System.out.println("This season ends!");
                System.out.println(results.get(0).name() + " is the Champion!!");
                currentTeamList = new ArrayList<Team>();
            }
            schedule = new ArrayList<Game>();   
            round++;
        }
        
    }

    private void results() {
        Utils.RecordHeader();
        for (Record r : records) {
            r.displayRecord();
        }
        Utils.RecordEnd();
    } 

    private void displayTeams() {
        System.out.println("The existing teams are as follows: ");
        String teams = "";
        for (Team team : currentTeamList) {
            teams += team.name() + " ";
        }
        System.out.println((teams).substring(0, teams.length() - 1));
        System.out.println("Please enter the team's name that you want to schedule: ");
    }


    private Team findTeamFromName(String name) {
        for (Team team : currentTeamList) {
            if (team.hasName(name)){
                return team;
            }
        }
        return null;
    }

    private Game getGame() {
        Game lastGame;
        if (schedule.size() == 0) {
            addGame();
        }
        else if (!(lastGame = schedule.get(schedule.size()-1)).full()) {
            return lastGame;
        }
        else {
            addGame();
        }      
        return schedule.get(schedule.size()-1);
    }

    private void addGame() {
        int maxTerm = 0; 
        for (Game game : schedule) {
            if (game.term > maxTerm) {
                maxTerm = game.term;
            }
        }
        schedule.add(new Game(maxTerm + 1));
    }

    public void initTeamList(Teams teams) {
        for (Team team : teams.teams()) {
            currentTeamList.add(team);
        }
    }

    private char choice() {
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
}

