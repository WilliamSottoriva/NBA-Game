public class Record
{
    private String WinTeam;
    private String LoseTeam;
    private Integer GameNo;
    private Integer Round;
    public Record(String WinTeam, String LoseTeam, int GameNo, int Round)
    {
        this.WinTeam = WinTeam;
        this.LoseTeam = LoseTeam;
        this.GameNo = GameNo;
        this.Round = Round;

    }

    public void displayRecord(){
        System.out.format(Utils.RecordFormat, Round, GameNo, WinTeam, LoseTeam);
    }
}

