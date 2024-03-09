import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void main(String[] args) {
        final int MIN_RANGE = 1;
        final int MAX_RANGE = 100;
        final int MAX_ATTEMPTS = 5;
        int score = 0;
        
        System.out.println("Welcome to Guess the Number game!");
        Scanner scanner = new Scanner(System.in);
        
        // Game loop
        while (true) {
            int randomNumber = generateRandomNumber(MIN_RANGE, MAX_RANGE);
            int attempts = 0;
            
            // Round loop
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess (between " + MIN_RANGE + " and " + MAX_RANGE + "): ");
                int userGuess = getUserGuess(scanner, MIN_RANGE, MAX_RANGE);
                attempts++;
                //System.out.println("No More attempts");
                
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    score += MAX_ATTEMPTS - attempts + 1; // Higher score for fewer attempts
                    break; // Exit round loop
                } else if (userGuess < randomNumber) {
                    System.out.println("Try a higher number than " + userGuess);
                } else {
                    System.out.println("Try a lower number than " + userGuess);
                }
            }
            
            // Check if user wants to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break; // Exit game loop
            }
        }
        
        System.out.println("Your final score: " + score);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
    
    // Generates a random number within the given range
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    // Prompt user to enter their guess and correct if outside the range
    public static int getUserGuess(Scanner scanner, int min, int max) {
        int guess;
        while (true) {
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= min && guess <= max) {
                    break;
                } else {
                    System.out.print("Please enter a number within the specified range: ");
                }
            } else {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Consume invalid input
            }
        }
        return guess;
    }
}
