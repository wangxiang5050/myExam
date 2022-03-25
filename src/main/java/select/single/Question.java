package select.single;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private String answer;
    private List<String> options = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "select.single.Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + options +
                '}';
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
