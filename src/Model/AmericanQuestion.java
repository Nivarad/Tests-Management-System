package Model;

import java.io.Serializable;
import java.util.Arrays;

public class AmericanQuestion extends Question implements Serializable {
	
	private final int MAX_ANSWERS=10;
	//private Tuple[] possibleAnswers;
	Set<String,Boolean> possibleAnswers= new Set<String,Boolean>();
	private int numAnswers;
	public AmericanQuestion(String questionText) {
		// TODO Auto-generated constructor stub
		super(questionText);
		
		this.possibleAnswers=new Set<String,Boolean>();
		numAnswers=0;
	}
	public AmericanQuestion(String questionText , Set<String,Boolean> possibleAnswers) {
		super(questionText);
		this.possibleAnswers= possibleAnswers;
		numAnswers= possibleAnswers.getLength();
	}
	
	public Boolean addAnswer(String answer, Boolean bool) {
		if(numAnswers==MAX_ANSWERS)
			return false;
		Set<String,Boolean> set = new Set<String,Boolean>();
		possibleAnswers.add(answer, bool);
		numAnswers++;
		return true;
		
	}
	public Boolean removeAnswer(int index) {
		possibleAnswers.remove(possibleAnswers.getKey(index));
		//possibleAnswers[index]=possibleAnswers[numAnswers-1];
		numAnswers=possibleAnswers.getLength();
		return true;
	}
	
	public int getNumAnswers() {
		return numAnswers;
	}
	public Set<String,Boolean> getPossibleAnswers(int i) {
		Set<String,Boolean> set=new Set<String,Boolean>();
		set.add(possibleAnswers.getKey(i), possibleAnswers.getValue(i));
		return set;
	}
	
	
	public Boolean editAnswer(int index,String answer,boolean bool) {
		possibleAnswers.setKey(index, answer);
		possibleAnswers.setValue(index, bool);
		return true;
	}

	@Override
	Boolean setAnswer(Object answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Object getAnswer() {
		Set<String,Boolean> copyAnswers= possibleAnswers;
		return copyAnswers;
	}

	@Override
	Boolean equals(Question question) {
		if(question instanceof AmericanQuestion) {
			if(this.questionText.equals(question.questionText))
				return true;
			return false;
		}
		return false;
	
	}

	@Override
	public String toString() {
		StringBuffer str=new StringBuffer();
		str.append("Question : "+this.questionText);
		for(int i=0;i<numAnswers;i++) {
			str.append("\n\t "+(i+1)+". "+possibleAnswers.getKey(i)+"    "+possibleAnswers.getValue(i));
		}
		return str.toString();
		
		
	}
	@Override
	public String toStringWithoutAnswers() {
		StringBuffer str=new StringBuffer();
		str.append("Question : "+this.questionText);
		for(int i=0;i<numAnswers;i++) {
			str.append("\n\t "+(i+1)+". "+possibleAnswers.getKey(i));
		}
		return str.toString();
	}
	
}


