package select.multiple;

import java.util.ArrayList;
import java.util.List;

public class MultipleSelectQuestion {

    private String question;
    private List<Character> answer = new ArrayList<>();
    private List<String> options = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Character> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Character> answer) {
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
