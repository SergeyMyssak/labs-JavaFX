package start;

import javafx.beans.property.SimpleStringProperty;

public class RobotResult {
    private SimpleStringProperty question;
    private SimpleStringProperty answer;

    public RobotResult(String question, String answer) {
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }
}
