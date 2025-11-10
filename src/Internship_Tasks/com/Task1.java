package Internship_Tasks.com;


import java.util.Scanner;
import java.util.Random;

    /**
     * A simple "Guess the Number" game where the player tries to guess
     * a randomly generated number between 1 and 100.
     */
    public class Task1 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            Random random = new Random();

            // Generate a random number between 1 and 100 (inclusive)
            int targetNumber = random.nextInt(100) + 1;

            int userGuess = 0;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("========================================");
            System.out.println("   Welcome to Guess the Number Game!   ");
            System.out.println("========================================");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("Can you guess what it is?");
            System.out.println("----------------------------------------\n");

            // Main game loop - continues until user guesses correctly
            while (!hasGuessedCorrectly) {
                // Prompt user for input
                System.out.print("Enter your guess: ");

                // Check if input is a valid integer
                if (scanner.hasNextInt()) {
                    userGuess = scanner.nextInt();
                    attempts++; // Increment attempt counter

                    // Check if guess is within valid range
                    if (userGuess < 1 || userGuess > 100) {
                        System.out.println("Please enter a number between 1 and 100!\n");
                        continue; // Skip to next iteration
                    }

                    // Compare user's guess with target number
                    if (userGuess < targetNumber) {
                        System.out.println("Too low! Try a higher number.\n");
                    } else if (userGuess > targetNumber) {
                        System.out.println("Too high! Try a lower number.\n");
                    } else {
                        // User guessed correctly!
                        hasGuessedCorrectly = true;
                        System.out.println("\n========================================");
                        System.out.println(" Congratulations! You guessed it! ");
                        System.out.println("========================================");
                        System.out.println("The number was: " + targetNumber);
                        System.out.println("Total attempts: " + attempts);

                        // Provide performance feedback based on attempts
                        if (attempts <= 5) {
                            System.out.println("Rating: Excellent! You're a master guesser!");
                        } else if (attempts <= 10) {
                            System.out.println("Rating: Good job! Well done!");
                        } else {
                            System.out.println("Rating: Nice work! Keep practicing!");
                        }
                        System.out.println("========================================");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid number.\n");
                    scanner.next();
                }
            }

            // Ask if user wants to play again
            System.out.print("\nWould you like to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (playAgain.equals("yes") || playAgain.equals("y")) {
                System.out.println("\n");
                main(args); // Restart the game by calling main method recursively
            } else {
                System.out.println("Thanks for playing! Goodbye!");
            }

            scanner.close();
        }
    }

