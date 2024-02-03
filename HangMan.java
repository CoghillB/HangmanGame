
import java.util.*;
import java.io.*;

public class HangMan {
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!\nHint... Try not to get hung up on the wrong guesses!");
        Scanner input = new Scanner(System.in);
        ArrayList<String> incorrectGuesses = new ArrayList<>();
        int guessesLeft = 7;
        int score = 0;
        boolean play = true;

        readFile("dictionary.txt");
        String word = randomWord("dictionary.txt").toUpperCase();

        while (play) {
            StringBuilder hiddenWord = new StringBuilder();
            hiddenWord.append("_ ".repeat(word.length()));

            while (guessesLeft > 0 && hiddenWord.toString().contains("_")) {
                System.out.println("\nHidden Word: " + hiddenWord);
                System.out.println("Incorrect Guesses: " + incorrectGuesses.toString().replace("[", "")
                        .replace("]", ""));
                System.out.println("Guesses Left: " + guessesLeft);
                System.out.println("Score: " + score);
                System.out.print("Enter next guess: ");
                String guess = input.next().toUpperCase();

                try {
                    if (guess.equals(" ") || guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                        System.out.println("\nInvalid input, enter a single letter");
                        continue;
                    } else if (hiddenWord.toString().contains(guess) || incorrectGuesses.toString().contains(guess)) {
                        System.out.println("\nYou have already guessed that letter, try again!");
                    } else if (word.contains(guess)) {
                        correctGuess(word, hiddenWord.toString(), guess);
                        score += 10;

                    } else {
                        // if the guess is incorrect
                        incorrectGuesses.add(guess);
                        System.out.println("\nSorry, there were no " + guess + "'s");
                        guessesLeft--;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, try again");
                    input.next();
                }
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0)) {
                        hiddenWord = new StringBuilder(hiddenWord.substring(0, i * 2) + guess + hiddenWord.substring(i * 2 + 1));
                    }
                }

                if (!hiddenWord.toString().contains("_")) {
                    System.out.println("Congratulations! You guessed the word correctly!");
                    score += 100 + (30 * guessesLeft);
                    System.out.println("You get 100 points plus " + (30 * guessesLeft) + " points for the remaining guess!");
                    System.out.println("You had " + guessesLeft + " guesses left!");
                    System.out.println("Your score is: " + score);
                    System.out.print("Would you like to play again? (yes/no): ");
                    String playAgain = input.next();
                    while (!playAgain.equalsIgnoreCase("yes") && !playAgain.equalsIgnoreCase("no")) {
                        System.out.println("Invalid input, please enter yes or no");
                        playAgain = input.next();
                    }
                    if (playAgain.equalsIgnoreCase("no")) {
//                        System.out.println("Thanks for playing!");
                        break;
                    } else if (playAgain.equalsIgnoreCase("yes")) {
                        word = randomWord("dictionary.txt").toUpperCase();
                        hiddenWord = new StringBuilder();
                        hiddenWord.append("_ ".repeat(word.length()));
                        incorrectGuesses.clear();
                        guessesLeft = 7;
                    } else {
                        System.out.println("Invalid input, please enter yes or no");
                        return;
                    }
                } else if (guessesLeft == 0) {
                    System.out.println("\nYou ran out of guesses dummy!");
                    System.out.println("The word was: " + word);
                    System.out.print("Would you like to play again? (yes/no): ");
                    String playAgain = input.next();
                    if (playAgain.equalsIgnoreCase("no")) {
                        System.out.println("\nThanks for playing!\nYou have yourself the most fabulous of days!");
                        break;
                    } else if (playAgain.equalsIgnoreCase("yes")) {
                        word = randomWord("dictionary.txt").toUpperCase();
                        hiddenWord = new StringBuilder();
                        hiddenWord.append("_ ".repeat(word.length()));
                        incorrectGuesses.clear();
                        score = 0;
                        guessesLeft = 7;
                    } else {
                        System.out.println("Invalid input, please enter yes or no");
                    }
                }
            }

            if (Highscore.isTop5(score, "Highscores.txt")) {
                System.out.println("\nCongratulations! You have a top 5 score!");
                System.out.print("Enter your name: ");
                String name = input.next();
                Highscore player = new Highscore(name, score);
                player.WriteToFile("Highscores.txt");
            } else {
                System.out.println("You did not get a top 5 score");
            }
            System.out.println("Highscores");
            System.out.println("-----------------");
            displayFile("Highscores.txt");
            play = false;
        }
    }

    private static void correctGuess(String word, String hiddenWord, String guess) {
        //only print message once even if the letter shows up multiple times
        if (hiddenWord.contains(guess)) {
            System.out.println("\nYou have already guessed that letter, try again!");
        } else {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    hiddenWord = hiddenWord.substring(0, i * 2) + guess + hiddenWord.substring(i * 2 + 1);

                }
            }
            System.out.println("\nCorrect!");
        }
    }

    public static void readFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String word = fileReader.next();
//                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String randomWord(String filename) {
        try {
            Scanner file = new Scanner(new File(filename));
            ArrayList<String> words = new ArrayList<>();
            while (file.hasNext()) {
                String word = file.next();
                words.add(word);
            }

//            Random rand = new Random();
            int index = (int) (Math.random() * words.size());
            return words.get(index);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return "";
    }

    public static void displayFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);
            ArrayList<Highscore> highscores = new ArrayList<>();
            while (fileReader.hasNext()) {
                String name = fileReader.next();
                int score = fileReader.nextInt();
                Highscore player = new Highscore(name, score);
                highscores.add(player);
                highscores.sort(Comparator.comparing(Highscore::getScore).reversed());
            }
            int count = 1;
            for (int i = 0; i < 5; i++) {
                if (i < highscores.size()) {
                    //display in column format
                    System.out.printf("%d. %-10s %d\n", count, highscores.get(i).getName(), highscores.get(i).getScore());
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}