package blank;


public class BlankQuestion {

    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "BlankQuestion{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
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

}
