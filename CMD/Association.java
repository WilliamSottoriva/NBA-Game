public class Association
{

    private Teams teams;
    private Season season; 

    public Association()
    {
        teams = new Teams();
        season = new Season();
    }

    public static void main(String[] args) {
        Association a = new Association();
        a.use();
    } 

    private void use() {
        welcomeMenu();
    }   

    private void welcomeMenu() {
        System.out.print("Welcome to the Association! Please make a selection from the menu:\n1. Explore the teams.\n2. Arrange a new season.\nX. Exit the system.\n");
        char c;
        while ((c = choice()) != 'X') {
            switch(c) {
                case '1': teams.teamsPage(); break;
                case '2': seasonPage(); break;
                default: System.out.println("Please enter a number 1 or 2, or press X to exit."); break;
            }
            System.out.print("Welcome to the Association! Please make a selection from the menu:\n1. Explore the teams.\n2. Arrange a new season.\nX. Exit the system.\n");
        }
        System.out.println("Done");
    }

    private void seasonPage() {
        season = new Season();
        season.initTeamList(teams); 
        season.seasonPage();
    }

    private char choice() {
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
}


