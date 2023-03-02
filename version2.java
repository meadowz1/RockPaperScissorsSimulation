
// Imports
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class mainnew {

    // Constants for the different moves
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    public static void main(String[] args) {
        // TODO
        // Importing Scanner
        Scanner keyboard = new Scanner(System.in);

        // Changable variables.
        int simulationAmount = 0;
        int simulationType1;
        int simulationType2;
        int bot1Wins = 0;
        int bot2Wins = 0;
        int ties = 0;
        int winningStreak = 0;
        int longestStreak = 0;
        int lastMove1 = randomizer();
        int lastMove2 = randomizer();
        int bot1Answer = 0;
        int bot2Answer = 0;

        // Getting user inputs and making use of them
        System.out.println("Please enter how many rounds you would like to play: ");
        simulationAmount = keyboard.nextInt();

        System.out.println("Please enter 1 for random and 2 for counterattack method for bot #1: ");
        simulationType1 = keyboard.nextInt();

        System.out.println("Please enter 1 for random and 2 for counterattack method for bot #2: ");
        simulationType2 = keyboard.nextInt();

        // Finding out the winner
        for (int i = 0; i < simulationAmount; i++) {
            // Finding the bot rolls
            if (simulationType1 == 2 && simulationType2 == 2) {
                bot1Answer = counterMethod(lastMove1);
                bot2Answer = counterMethod(lastMove2);
            } else if (simulationType1 == 2 && simulationType2 == 1) {
                bot1Answer = counterMethod(lastMove1);
                bot2Answer = randomizer();
            } else if (simulationType1 == 1 && simulationType2 == 2) {
                bot1Answer = randomizer();
                bot2Answer = counterMethod(lastMove2);
            } else {
                bot1Answer = randomizer();
                bot2Answer = randomizer();
            }

            // Saving the last moves
            lastMove1 = bot1Answer;
            lastMove2 = bot2Answer;

            // Checking the winner
            if (bot1Answer == ROCK && bot2Answer == SCISSORS) {
                bot1Wins++;
            } else if (bot1Answer == PAPER && bot2Answer == ROCK) {
                bot1Wins++;
            } else if (bot1Answer == SCISSORS && bot2Answer == PAPER) {
                bot1Wins++;
            } else if (bot2Answer == ROCK && bot1Answer == SCISSORS) {
                bot2Wins++;
            } else if (bot2Answer == PAPER && bot1Answer == ROCK) {
                bot2Wins++;
            } else if (bot2Answer == SCISSORS && bot1Answer == PAPER) {
                bot2Wins++;
            } else {
                ties++;
            }
        }

        // Printing Final Statistics
        System.out.println("Bot 1 has won " + bot1Wins + " times.");
        System.out.println("Bot 2 has won " + bot2Wins + " times.");
        System.out.println("The bots have tied " + ties + " times.");
    }

    public static int randomizer() {
        Random randomizer = new Random();
        int randomNum = randomizer.nextInt(1, 4);
        return randomNum;
    }

    public static int counterMethod(int lastMove) {
        int nextMove = ROCK;

        switch (lastMove) {
            case ROCK:
                nextMove = PAPER;
                break;
            case PAPER:
                nextMove = SCISSORS;
                break;
            case SCISSORS:
                nextMove = ROCK;
                break;
            default:
                nextMove = randomizer();
        }

        return nextMove;
    }

}
