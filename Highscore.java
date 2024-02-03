import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Highscore {
    private String name;
    private int score;

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }

    public void WriteToFile(String filename) {
        try {
            FileWriter file = new FileWriter(filename, true);
            PrintWriter writer = new PrintWriter(file);
            writer.println(name + " " + score);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    // add a method to determine if the players score is a top 5 score
    public static boolean isTop5(int score, String filename) {

        try {
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);
            ArrayList<Highscore> highscores = new ArrayList<>();
            while (fileReader.hasNext()) {
                String name = fileReader.next();
                int scoreFromFile = fileReader.nextInt();
                Highscore player = new Highscore(name, scoreFromFile);
                highscores.add(player);
                highscores.sort(Comparator.comparing(Highscore::getScore).reversed());
            }
            if (highscores.size() < 5 && score > 100) {
                return true;
            } else if (highscores.size() >= 4 && score > highscores.get(4).getScore()) {
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return false;
    }
}