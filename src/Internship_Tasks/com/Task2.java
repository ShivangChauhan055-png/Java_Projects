package Internship_Tasks.com;

import java.util.Scanner;
import java.util.Random;

public class Task2 {

    // Game choices
    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";

    // Score tracking
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int ties = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("============================ ");
        System.out.println("  ROCK PAPER SCISSORS GAME");
        System.out.println("============================ ");
        System.out.println();

        boolean playAgain = true;

        while (playAgain) {
            //  player choice
            System.out.print("Enter your choice (rock/paper/scissors) or 'quit' to exit: ");
            String playerChoice = sc.nextLine().toLowerCase().trim();

            // Check if player wants to quit
            if (playerChoice.equals("quit")) {
                playAgain = false;
                continue;
            }

            // Validate input
            if (!isValidChoice(playerChoice)) {
                System.out.println("Invalid choice! Please enter rock, paper, or scissors.");
                System.out.println();
                continue;
            }

            // Generate computer choice
            String computerChoice = getComputerChoice(rand);

            // Display choices
            System.out.println("\nYou chose: " + playerChoice);
            System.out.println("Computer chose: " + computerChoice);

            // Determine winner
            String result = determineWinner(playerChoice, computerChoice);
            System.out.println(result);

            // Display score
            displayScore();
            System.out.println();
        }

        // Display final results
        displayFinalResults();
        sc.close();
    }

    // Validate if the choice is valid
    private static boolean isValidChoice(String choice) {
        return choice.equals(ROCK) || choice.equals(PAPER) || choice.equals(SCISSORS);
    }

    // Generate random computer choice
    private static String getComputerChoice(Random rand) {
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0: return ROCK;
            case 1: return PAPER;
            case 2: return SCISSORS;
            default: return ROCK;
        }
    }

    // Determine the winner of the round
    private static String determineWinner(String player, String computer) {
        if (player.equals(computer)) {
            ties++;
            return "It's a tie!";
        }

        if ((player.equals(ROCK) && computer.equals(SCISSORS)) ||
                (player.equals(PAPER) && computer.equals(ROCK)) ||
                (player.equals(SCISSORS) && computer.equals(PAPER))) {
            playerScore++;
            return "You win this round!";
        } else {
            computerScore++;
            return "Computer wins this round!";
        }
    }

    // Display current score
    private static void displayScore() {
        System.out.println("\n--- Current Score ---");
        System.out.println("Player: " + playerScore);
        System.out.println("Computer: " + computerScore);
        System.out.println("Ties: " + ties);
        System.out.println("--------------------");
    }

    // Display final results when game ends
    private static void displayFinalResults() {
        System.out.println("\n=================================");
        System.out.println("      FINAL RESULTS");
        System.out.println("=================================");
        System.out.println("Player Score: " + playerScore);
        System.out.println("Computer Score: " + computerScore);
        System.out.println("Ties: " + ties);
        System.out.println("Total Rounds: " + (playerScore + computerScore + ties));

        if (playerScore > computerScore) {
            System.out.println("\nCongratulations! You won overall!");
        } else if (computerScore > playerScore) {
            System.out.println("\nComputer won overall. Better luck next time!");
        } else {
            System.out.println("\nIt's an overall tie!");
        }

        System.out.println("=================================");
        System.out.println("Thanks for playing!");
    }
}