package msk.lab5;

import javafx.beans.property.SimpleStringProperty;

public class TextRobot {
    private SimpleStringProperty word;

    public TextRobot(String word) {
        this.word = new SimpleStringProperty(word);
    }
    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }
}
