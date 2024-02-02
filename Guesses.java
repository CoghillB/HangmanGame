public class Guesses {

    private String letter;
    private boolean correct;

    public Guesses(String letter, boolean correct) {
        this.letter = letter;
        this.correct = correct;
    }

    public String getLetter() {
        return letter;
    }

    public boolean getCorrect() {

        return correct;
    }

    public void setCorrect(boolean correct) {

        this.correct = correct;
    }

    public String toString() {

        return letter;
    }
}
