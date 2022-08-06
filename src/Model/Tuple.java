package Model;
import java.io.Serializable;

public class Tuple implements Serializable {

	private String answer;
	private boolean answerBool;
	
	public Tuple(String answer,Boolean answerBool) {
		this.answer=answer;
		this.answerBool=answerBool;
	}
	public String getAnswer() {
		return answer;
	}
	public Boolean getAnswerBool() {
		return answerBool;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAnswerBool(Boolean answerBool) {
		this.answerBool = answerBool;
	}
}
