import java.util.ArrayList;
public class Game
{
    private ArrayList<Team> teams;
    private ArrayList<Team> results;
    public Integer term;

    public Game(int term)
    {
        teams = new ArrayList<Team>();
        results = new ArrayList<Team>();
        this.term = term;
    }

    public ArrayList<Team> playGame() {
        Team team1 = teams.get(0);
        Team team2 = teams.get(1);
        Double team1AvgCredit = team1.AvgCredit();
        Double team2AvgCredit = team2.AvgCredit();
        if (team1AvgCredit < team2AvgCredit) {
            results.add(team2);
            results.add(team1);
        }
        else if (team1AvgCredit > team2AvgCredit) {
            results.add(team1);
            results.add(team2);
        }
        else {
            if (team1.name().toLowerCase().compareTo(team2.name().toLowerCase()) < 0) {
                results.add(team1);
                results.add(team2);
            }
            else {
                results.add(team2);
                results.add(team1);
            }
        }
        double diff = team1AvgCredit - team2AvgCredit;
        if (diff < 0) {
            diff = diff * (-1);
        }
        results.get(0).won(diff);
        results.get(1).lost(diff);
        return results;
    }

    public void addTeam(Team t, String teamName) {
        teams.add(t);
        System.out.println("Team " + teamName + " has been added at the Game " + term + " position " + teams.size());
    }

    public boolean full() {
        return teams.size()==2;
    }

    public void displayRound(){
        System.out.format(Utils.GamesFormat, teams.get(0).name(), " vs", teams.get(1).name());
    }

}





