
// IMPORTS
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  // Constants for the different moves
  // This is to make it simple to identify each variable 
  // and use them inside the code. 
  private static final int ROCK = 1;
  private static final int PAPER = 2;
  private static final int SCISSORS = 3;

  // Main Method
  public static void main(String[] args) {

      // This is a placeholder for the true and false statements to loop the program
      boolean trueFalse = true;

      // While loop to make the program run "infinitely"
      while (trueFalse) {
          // Variables
          int bot1Wins = 0;
          int bot2Wins = 0;
          int ties = 0;
          int longestStreak = 0;
          int winningStreak1 = 0;
          int winningStreak2 = 0;

          // making 2 array lists for the answers
          ArrayList<Integer> bot1List = new ArrayList<>();
          ArrayList<Integer> bot2List = new ArrayList<>();

          // to make sure the other two methods have a number to choose
          bot1List.add(randomizer());
          bot2List.add(randomizer());

          // getting the users inputs
          System.out.println("RPS Simulation \n");
          int simulationAmount = userInput();

          // bot choices 1 and 2
          System.out.println("Please enter what method you would like to use for bot #1: ");
          int botChoice1 = botChoice();

          System.out.println("Please enter what method you would like to use for bot #2: ");
          int botChoice2 = botChoice();

          // comparing choices
          for (int i = 0; i < simulationAmount; i++) {
            // Adding the choice to the ArrayList
            int bot1LastChoice = bot1List.get(i);
            // Putting the choice and last choice into the choiceMaker() method
            int bot1Answer = choiceMaker(botChoice1, bot1LastChoice);
            // Adding the new answer to the list
            bot1List.add(bot1Answer);

            // Same idea here  from the first choice
            int bot2LastChoice = bot2List.get(i);
            int bot2Answer = choiceMaker(botChoice2, bot2LastChoice);
            bot2List.add(bot2Answer);

            // This is the tie situation, which adds up a tie.
            if (results(bot1Answer, bot2Answer) == 4) {
                ties++;
            }

            // This is the Bot #2 winning situation
            else if (results(bot1Answer, bot2Answer) == 5) {
                bot2Wins++;
                // Adds to the winning streak for Bot #2
                winningStreak1++;
                // Sets Bot #1's winning streak to 0
                winningStreak2 = 0;

                // Checks if the current streak is greater than the longest streak
                if (winningStreak1 > longestStreak) {
                    // Sets the winningStreak to the longest streak
                    longestStreak = winningStreak1;
                }
            }

            else if (results(bot1Answer, bot2Answer) == 6) {
                bot1Wins++;
                // Adds to the winning streak for Bot #1
                winningStreak2++;
                // Sets Bot #2's winning streak to 0
                winningStreak1 = 0;
                // Checks if the current streak is greater than the longest streak
                if (winningStreak2 > longestStreak) {
                  // Sets the winningStreak to the longest streak
                    longestStreak = winningStreak2;
                }
            }
        }

    // This calls on the finalStatement() method, which puts everything all together and summarizes the 
    // details discretely.
    finalStatement(simulationAmount, botChoice1, botChoice2, bot1Wins, ties, longestStreak, bot2Wins);

            // This if-else statement checks if the user wants to retry or end the program.
            if (tryAgain() == 1) {
                trueFalse = true;
            } else {
                System.out.println("Thanks for using my program :)");
                trueFalse = false;
            }
        }
    }
  // END OF MAIN method

  // choiceMaker() method
  // returns a number that results in ROCK, PAPER, or SCISSORS
  // takes in bot's current and previous choice
  // integer receives, integer returns
  public static int choiceMaker(int botChoice, int lastChoice) {
      int answer = 0;

      if (botChoice == 1) {
          answer = randomizer();
      } else if (botChoice == 2) {
          answer = counterMethod(lastChoice);
      } else if (botChoice == 3) {
          answer = triangleMethod(lastChoice);
      } else {
          return 0;
      }

      return answer;
  }
  // END OF choiceMaker() method

  // randomizer() method
  // returns a number that can be used in the choiceMaker() method
  // simply uses the Random class in java utility imports
  // returns an integer
  public static int randomizer() {
      Random randomizer = new Random();
      int randomNum = randomizer.nextInt(1, 4);
      return randomNum;
    }
  // END OF randomizer() method
  
  // counterMethod() method
  // uses the last move to create the next move in a pattern
  // of ROCK -> PAPER -> SCISSORS -> ROCK
  // returns an integer
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
  // END OF counterMethod() method

  // triangleMethod() method
  // uses the last move to generate the next move in the pattern
  // ROCK -> SCISSORS -> PAPER -> ROCK
  public static int triangleMethod(int lastMove) {
      int nextMove = ROCK;

      switch (lastMove) {
          case ROCK:
              nextMove = SCISSORS;
              break;
          case SCISSORS:
              nextMove = PAPER;
              break;
          case PAPER:
              nextMove = ROCK;
              break;
          default:
              nextMove = randomizer();
      }
      return nextMove;
  }
  // END OF triangleMethod() method

  // This is the result selection method, results()
  // takes in the two resluts of the bots
  // returns a winner in the form of an integer
  // compares the results of both, checks for winners, ties, and issues
  public static int results(int bot1Result, int bot2Result) {
      // Tie Condition
      int finalResult = 0;
      if (bot1Result == bot2Result) {
          finalResult = 4;
      }

      // Losing conditions for Bot #1
      else if (bot1Result == ROCK && bot2Result == PAPER) {
          finalResult = 5;
      } else if (bot1Result == PAPER && bot2Result == SCISSORS) {
          finalResult = 5;
      } else if (bot1Result == SCISSORS && bot2Result == ROCK) {
          finalResult = 5;
      }

      // Winning conditions for Bot #1
      else if (bot1Result == ROCK && bot2Result == SCISSORS) {
          finalResult = 6;
      } else if (bot1Result == PAPER && bot2Result == ROCK) {
          finalResult = 6;
      } else if (bot1Result == SCISSORS && bot2Result == PAPER) {
          finalResult = 6;
      }

      // Broken Statement
      else {
          System.out.println("Fatal error! Please restart!");
          finalResult = 7;
      }
      return finalResult;
  }
  // END OF results() method

  // userInput() method
  // Asks user for the amount of rounds they want to simulate
  // returns the user's input as an integer
  public static int userInput() {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("How many rounds would you like to simulate? : ");
      int userInputted = keyboard.nextInt();
      return userInputted;
  }
  // END OF userInput() method

  // botChoice() method
  // Asks user for what bot method they would like to use
  // returns an integer which can be used to determine what method they want to use
  public static int botChoice() {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("1. Randomized");
      System.out.println("2. Counterattacking");
      System.out.println("3. Triangle Movement");
      int userInputted = keyboard.nextInt();

      return userInputted;
  }
  // END OF botChoice() method

  // finalStatement() method
  // Prints out statements that summarize all the details
  // takes integers for each detail
  // only prints lines
  public static void finalStatement(int roundsPlayed, int bot1Choice, int bot2Choice, int bot1MatchesWon, int ties,
          int longestStreak, int bot2MatchesWon) {

      System.out.println("There has been a total of " + roundsPlayed + " rounds played.");
      System.out.println("Bot #1 won " + bot1MatchesWon + "  times out of " + roundsPlayed + ".");
      System.out.println("Bot #2 won " + bot2MatchesWon + "  times out of " + roundsPlayed + ".");
      System.out.println("The bots tied a total of " + ties + " times out of " + roundsPlayed + ".");
      System.out.println("The longest win streak held was " + longestStreak + " times.");
      if (bot1MatchesWon < bot2MatchesWon) {
          if (bot2Choice == 1) {
              System.out.println("The randomizer method is the method Bot #2 used to win!");
          } else if (bot2Choice == 2) {
              System.out.println("The counterattacking method is the method Bot #2 used to win!");
          } else {
              System.out.println("The triangle method is the method Bot #2 used to win!");
          }
      } else {
          if (bot1Choice == 1) {
              System.out.println("The randomizer method is the method Bot #1 used to win!");
          } else if (bot1Choice == 2) {
              System.out.println("The counterattacking method is the method Bot #1 used to win!");
          } else {
              System.out.println("The triangle method is the method Bot #1 used to win!");
          }
      }

  }
  // END OF finalStatement() method

  // tryAgain() method
  // Asks user if they want to try again
  // 1 is retry
  // 2 is end program
  // returns integer
  public static int tryAgain() {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Would you like to try again? (Enter 1 for yes and 2 for no.) : ");
      int userInputted = keyboard.nextInt();
      if (userInputted == 1 || userInputted == 2) {
          return userInputted;
      } else {
          System.out.println("Invalid entry! Ending program...");
          return 2;
      }
  }
  // END OF tryAgain() method
}
// END OF PROGRAM
