public class Player {
    private String name;
    private Double credit;
    private String level;
    private Integer age;
    private String team;
    private Integer No;

    public Player(String name, Double credit, int age, int No)
    {
        this.name = name;
        this.credit = credit;
        this.age = age;
        this.No = No;
        team = new String();
        setLevel(credit);
    }

    private void setLevel(Double credit) {
        if (credit < 1000) {level = "Edge";}
        else if (1000 <= credit && credit < 1500) {level = "Common";}
        else if (1500 <= credit && credit < 2000) {level = "Core";}
        else if (credit >= 2000) {level = "All Star";}
    } 

    public void setTeam(String team){
        this.team = team;
    }

    public void updateCredits(double diff){
        credit = credit + diff;
        setLevel(credit);
    }

    public double credit(){
        return credit;
    }

    public int age(){
        return age;
    }

    public String name(){
        return name;
    }

    public void displayPlayer1(){
        System.out.format(Utils.DisplayPlayerFromAllTeamsFormat, name, credit, level, age, No, team);
    }

    public void displayPlayer2(){
        System.out.format(Utils.PlayerFormat, name, credit, level, No, age);
    }

    public boolean hasNo(int No) {
        return this.No == No;
    }

    public boolean hasLevel(String level) {
        return this.level.equals(level);
    }

    public boolean hasName(String name) {
        return this.name.toLowerCase().equals(name.toLowerCase());
    }
}

