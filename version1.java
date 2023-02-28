
// Imports
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> rockPaperScissorArray = new ArrayList<Integer>(); // Making ArrayList
        int bot1Wins = 0;
        int bot2Wins = 0;
        int ties = 0;

        Scanner keyboard = new Scanner(System.in); // Creating the Scanner

        System.out.println("Please input how many games you would like to simulate: "); // Prompts user to enter an
                                                                                        // integer to
        int gameSimulation = keyboard.nextInt(); // find out how many games they would simulate.

        for (int i = 0; i < gameSimulation; i++) {
            int bot1 = randomizeNumberMachine();
            int bot2 = randomizeNumberMachine();
            System.out.println("Bot 1: " + rockPaperScissors(bot1));
            System.out.println("Bot 2: " + rockPaperScissors(bot2));
            System.out.println(" ");
            String winnerCheckString = winnerCheck(bot1, bot2);
            if (winnerLoserCount(winnerCheckString) == 1) {
                ties++;
            } else if (winnerLoserCount(winnerCheckString) == 2) {
                bot1Wins++;
            } else {
                bot2Wins++;
            }
        }
        System.out.println("Bot 1 has won: " + bot1Wins + " times.");
        System.out.println("Bot 2 has won: " + bot2Wins + " times.");
        System.out.println("Bots have tied " + ties + " times.");
    }

    public static int randomizeNumberMachine() {
        // This method here generates a random number between
        // 1 and 3, to be used in another method to check for
        // Rock, Paper, and Scissors, and to determine a winner.

        Random randomizer = new Random();
        int randomNum = randomizer.nextInt(1, 4);
        return randomNum;
    }

    public static String winnerCheck(int bot1Answer, int bot2Answer) {
        // This method will check and compare for each answer
        // made by the bot and find out the winner from the two
        String winner = " ";

        // Tie Case
        if (bot1Answer == bot2Answer) {
            winner = "Tie";
        }
        // P1 Rock Win
        else if (bot1Answer == 1 && bot2Answer == 3) {
            winner = "Player 1 Win";
        }
        // P1 Rock Lose
        else if (bot1Answer == 1 && bot2Answer == 2) {
            winner = "Player 2 Win";
        }
        // P1 Paper Win
        else if (bot1Answer == 2 && bot2Answer == 1) {
            winner = "Player 1 Win";
        }
        // P1 Paper Lose
        else if (bot1Answer == 2 && bot2Answer == 3) {
            winner = "Player 2 Win";
        }
        // P1 Scissors Win
        else if (bot1Answer == 3 && bot2Answer == 2) {
            winner = "Player 1 Win";
        }
        // P1 Scissors Lose
        else {
            winner = "Player 2 Win";
        }

        // CHANGE
        return winner;
    }

    public static int winnerLoserCount(String winnerCheck) {
        // This method will check for who won from the
        // winnerCheck() method. It will see if it is
        // either a tie, win, or loss.
        int finalAnswer;

        if (winnerCheck.equals("Tie")) {
            finalAnswer = 1;
        } else if (winnerCheck.equals("Player 1 Win")) {
            finalAnswer = 2;
        } else {
            finalAnswer = 3;
        }

        return finalAnswer;
    }

    public static String rockPaperScissors(int botAnswer) {
        // This method checks for the integers between 1 and 3, then
        // assesses 1 as Rock, 2 and Paper, and 3 as Scissors

        String rPS;

        if (botAnswer == 1) {
            rPS = "Rock";
        } else if (botAnswer == 2) {
            rPS = "Paper";
        } else {
            rPS = "Scissors";
        }

        return rPS;
    }
}
