import java.util.Scanner;
import java.util.Random;

class RockPaperScissors {

    static String playRound(String player, String computer) {

        if (player.equals(computer))
            return "Draw";

        if (player.equals("Rock") && computer.equals("Scissors"))
            return "Player Wins";

        if (player.equals("Paper") && computer.equals("Rock"))
            return "Player Wins";

        if (player.equals("Scissors") && computer.equals("Paper"))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        String moves[] = {"Rock", "Paper", "Scissors"};

        int win = 0, loss = 0, draw = 0;

        System.out.println("Rock Paper Scissors Game");

        for (int i = 1; i <= 5; i++) {

            System.out.println("\nRound " + i);
            System.out.print("Enter your move: ");
            String player = sc.next();

            String computer = moves[r.nextInt(3)];

            String result = playRound(player, computer);

            System.out.println("Computer Move: " + computer);
            System.out.println(result);

            if (result.equals("Player Wins"))
                win++;
            else if (result.equals("Computer Wins"))
                loss++;
            else
                draw++;
        }

        System.out.println("\nFinal Summary");
        System.out.println("Wins: " + win);
        System.out.println("Losses: " + loss);
        System.out.println("Draws: " + draw);

        double percent = (win * 100.0) / 5;
        System.out.println("Win Percentage: " + percent + "%");

        sc.close();
    }
}