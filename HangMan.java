/* Hangman
In this assignment you will create a hangman game while also keeping track of the user’s high scores.  I will provide a text file full of roughly 120,000 words that you can randomly pick from, which contains the full contents of the Official Scrabble Player's Dictionary, Second Edition.  Your program will display the number of characters in the hidden word, and if the user has correctly guessed any letters, display that letter in the correct spot.  The user will have 7 guesses to figure out the word, which resets on each correctly solved word.  A correct guess does not reduce the number of guesses.  On the 7th wrong guess, the game ends, and the user is shown their score, and if they make the top 5 all-time high scores, they are asked to enter their name.  Then the high score list is displayed and the program is done.  You must also handle the input correctly, asking the user to re-enter any guesses that aren’t valid.

Assume that the randomly chosen word was house.  If the user has made 3 guesses, L, B, S the output would look like this:
Hidden Word: _ _ _ S _
Incorrect Guesses: B, L
Guesses Left: 5
Score: 10
Enter next guess: D
Sorry, there were no D’s

Hidden Word: _ _ _ S _
Incorrect Guesses: B, D, L
Guesses Left: 4
Score: 10
Enter next guess:

The scoring works as follows:
•	Each correct letter guess is worth 10 points
•	Each correct word is worth 100 points + 30 points for each remaining guess left.

*/

import java.util.*;
import java.io.*;

public class HangMan {
    /* TODO: Create a class called Highscore
     * TODO: seperate the word into guessable spaces
     * TODO: Create a class called Guesses
     * TODO: Create a Score class
     * */
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!\nHint... Try not to get hung up on the wrong guesses!");
        Scanner input = new Scanner(System.in);
        readFile("TestList.txt");
        String word = randomWord("TestList.txt");
        System.out.println(word);


    }

    public static void readFile(String filename) {
        try {
            Scanner file = new Scanner(new File(filename));
            while (file.hasNext()) {
                String word = file.next();
//                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String randomWord(String filename) {
        try {
            Scanner file = new Scanner(new File(filename));
            ArrayList<String> words = new ArrayList<String>();
            while (file.hasNext()) {
                String word = file.next();
                words.add(word);
            }

            Random rand = new Random();
            int index = (int) (Math.random() * words.size());
            return words.get(index);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return "";
    }
}
