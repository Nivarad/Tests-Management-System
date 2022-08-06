package Model;
import java.io.Serializable;

public abstract class Question implements Serializable{
	
	protected String questionText;
	protected int id;
	
	public Question(String questionText) {
		this.questionText=questionText;
		id=0;
	}
	public String getQuestionText() {
		return questionText;
	}
	public Boolean setQuestionText(String questionText) {
		this.questionText = questionText;
		return true;
	}
	public int getId() {
		return id;
	}
	abstract Boolean setAnswer(Object answer);
	abstract Object getAnswer();
	abstract Boolean equals(Question question);
	public abstract String toString();
	public abstract String toStringWithoutAnswers();
	

}
