import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// class to store highscore data by writing to a File
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

    public String toString() {
        return name + " " + score;
    }

    public void writeToFile(String filename) {
        try {
            PrintWriter file = new PrintWriter(new FileWriter(filename, true));
            file.println(name + " " + score);
            file.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
