import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"Rock", "Paper", "Scissors"};
        String playAgain;

        do {
            // Siddhi here: this is where we take players choice
            System.out.println("Enter your choice (Rock, Paper, Scissors): ");
            String playerChoice = scanner.nextLine();

            // sid here: this is where we get computer's choice
            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];

            System.out.println("Computer chose: " + computerChoice);

            // sid here: Now this will determine the winner
            if (playerChoice.equalsIgnoreCase(computerChoice)) {
                System.out.println("It's a tie!");
            } else if ((playerChoice.equalsIgnoreCase("Rock") && computerChoice.equals("Scissors")) ||
                       (playerChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Rock")) ||
                       (playerChoice.equalsIgnoreCase("Scissors") && computerChoice.equals("Paper"))) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }

            // sid here: Here you can ask if the player wants to play again
            System.out.println("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine();
        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
