package Model;
import java.io.Serializable;

public class OpenQuestion extends Question implements Serializable{
	
	private String answer;
	private static int num;
	
	
	public OpenQuestion(String question, String answer) {
		super(question);
		this.answer=answer;
		this.id=++num;
	}


	@Override
	Boolean setAnswer(Object answer) {
		if(answer instanceof String) {
			this.answer = answer.toString();
			return true;
		}
		return false;
	}

	@Override
	String getAnswer() {
		return answer;
	}

	@Override
	Boolean equals(Question question) {
		if(question instanceof OpenQuestion) {
			if(this.questionText.equals(question.questionText))
				return true;
			return false;
		}
		return false;
	}


	@Override
	public String toString() {
		String str="Question : "+this.questionText+"\t"+this.answer;
		return str;
	}
	@Override
	public String toStringWithoutAnswers() {
		String str="Question : "+this.questionText;
		return str;
	}
	

}

