package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Management implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<OpenQuestion> openQuestions= null;
	public ArrayList<AmericanQuestion> getAmericanQuestions() {
		return americanQuestions;
	}
	public int getNumAmericanQuestions() {
		return numAmericanQuestions;
	}



	private ArrayList<AmericanQuestion> americanQuestions=null;
	private int numOpenQuestions;
	private int numQuestions;
	private int numAmericanQuestions;
	
	public Management() {
		//exam=new Exam();
		this.openQuestions=new ArrayList<OpenQuestion>();	
		this.americanQuestions=new ArrayList<AmericanQuestion>();
		this.numOpenQuestions=0;
		this.numAmericanQuestions=0;
		this.numQuestions=0;
		addQuestionsByAdmin();
		
	}
	public void addQuestionsByAdmin() {
		Set<String,Boolean> americanAnswers= new Set<String,Boolean>();
		americanAnswers.add("Dog",true);
		americanAnswers.add("Cat",false);
		americanAnswers.add("Cow",false);
		americanAnswers.add("Crow",false);
		AmericanQuestion american=new AmericanQuestion("What animal barks?",americanAnswers);
		addAmericanQuestion(american);
		
		Set<String,Boolean> americanAnswers2= new Set<String,Boolean>();
		americanAnswers2.add("Square",true);
		americanAnswers2.add("Rectangle",true);
		americanAnswers2.add("Circle",false);
		americanAnswers2.add("Quibicle",false);
		AmericanQuestion american2 =new AmericanQuestion("What shape has 360 degrees in it?",americanAnswers2);
		addAmericanQuestion(american2);
		
		Set<String,Boolean> americanAnswers3= new Set<String,Boolean>();
		americanAnswers3.add("Niv Arad",true);
		americanAnswers3.add("Shir Melayev",true);
		americanAnswers3.add("Adi Meirovech",false);
		americanAnswers3.add("noam",false);
		AmericanQuestion american3 =new AmericanQuestion("who are the creators of this work?",americanAnswers3);
		addAmericanQuestion(american3);
		
	
		OpenQuestion open1 = new OpenQuestion ("What is the jew's country?", "Israel");
		addOpenQuestion(open1);
		OpenQuestion open2 = new OpenQuestion ("What is the name of this Course?", "Object Oriented Programming");
		addOpenQuestion(open2);
		OpenQuestion open3 = new OpenQuestion ("what are we making here?", "Test");
		addOpenQuestion(open3);
	}	
	
	public Boolean addOpenQuestion(OpenQuestion openQuestion) {

		for (int i = 0; i < numOpenQuestions; i++) { // ׳×׳¢׳‘׳•׳¨ ׳¢׳� ׳”׳�׳¢׳¨׳� ׳•׳×׳‘׳“׳•׳§ ׳”׳�׳� ׳§׳™׳™׳�׳× ׳‘׳•
														// ׳›׳‘׳¨ ׳©׳�׳�׳” ׳“׳•׳�׳”
			if (openQuestions.get(i).equals(openQuestion))
				return false;
		}
		openQuestions.add(openQuestion);
		numOpenQuestions++;
		openQuestion.id = ++numQuestions;
		return true;

	}

	public Boolean addAmericanQuestion(AmericanQuestion americanQuestion) {
		// String answer=americanQuestion.questionText;
		for (int i = 0; i < numAmericanQuestions; i++) { // ׳×׳¢׳‘׳•׳¨ ׳¢׳� ׳”׳�׳¢׳¨׳� ׳•׳×׳‘׳“׳•׳§ ׳”׳�׳� ׳§׳™׳™׳�׳×
															// ׳‘׳• ׳›׳‘׳¨ ׳©׳�׳�׳” ׳“׳•׳�׳”
			if (americanQuestions.get(i).equals(americanQuestion))
				return false;
		}

		americanQuestions.add(americanQuestion);
		numAmericanQuestions++;
		americanQuestion.id = ++numQuestions;
		return true;
	}
	public int getNumQuestions() {
		return numQuestions;
	}
	public Question getQuestion(int id) {
		if(id>this.numQuestions)  //׳�׳� ׳” id ׳’׳‘׳•׳” ׳�׳“׳™ ׳�׳– ׳�׳� ׳§׳™׳™׳�׳× ׳”׳©׳�׳�׳” ׳•׳×׳—׳–׳™׳¨ ׳©׳§׳¨
			return null;
		for(int i=0;i<numOpenQuestions;i++) {
			if(openQuestions.get(i).getId()==id)
				return openQuestions.get(i);
		}
		for(int i=0;i<numAmericanQuestions;i++) {
			if(americanQuestions.get(i).getId()==id)
				return americanQuestions.get(i);
		}
		return null;
		
	}
	public AmericanQuestion getAmericanQuestionByString(String americanQuestion) {
		
		for(int i=0;i<numAmericanQuestions;i++) {
			if(americanQuestions.get(i).getQuestionText().equals(americanQuestion))
				return americanQuestions.get(i);
		}
		
		return null;
		
	}
	public ArrayList<OpenQuestion> getOpenQuestions() {
		return openQuestions;
	}
	public OpenQuestion getOpenQuestionByString(String openQuestion) {
		
		for(int i=0;i<numOpenQuestions;i++) {
			if(openQuestions.get(i).getQuestionText().equals(openQuestion))
				return openQuestions.get(i);
		}
			return null;	
	}
	public String toString() {
		StringBuffer str=new StringBuffer();
		str.append("The open questions in the Repository are:"+'\n');
		for(int i=0;i<numOpenQuestions;i++) {
			str.append("(ID:"+openQuestions.get(i).getId()+") "+openQuestions.get(i).toString()+"\n");
		}
		str.append("\n");
		str.append("The american questions in the Repository are:"+'\n');
		for(int i=0;i<numAmericanQuestions;i++) {
			str.append("(ID:"+americanQuestions.get(i).getId()+") "+americanQuestions.get(i).toString()+"\n");
		}
		return str.toString();
	}
	public boolean changeOpenAnswer(int id,String answer) {
		boolean check=false;
		Question question=getQuestion(id);
		if(question!=null) {
			OpenQuestion openQuestion=(OpenQuestion)question;
			check=openQuestion.setAnswer(answer);
		}
		return check;
	}
	public boolean changeAmericanAnswer(int id,int index,String answer,boolean bool) {
		boolean check=false;
		Question question=getQuestion(id);
		if(question!=null) {
			AmericanQuestion americanQuestion=(AmericanQuestion)question;
			check=americanQuestion.editAnswer(index-1, answer, bool);
			
		}
		return check;
	}
	public boolean changeQuestionText(int id, String questionText) {
		boolean check=false;
		Question question=getQuestion(id);
		if(question!=null) 
			check=question.setQuestionText(questionText);
		return check;
	}
	public boolean removeAmericanAnswer(int id,int index) {
		boolean check=false;
		Question question=getQuestion(id);
		if(question!=null &&question instanceof AmericanQuestion) {
			AmericanQuestion americanQuestion=(AmericanQuestion)question;
			check=americanQuestion.removeAnswer(index-1);
		}
		return check;
	}
	
	

	class Exam implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ArrayList <Question> questionForTest = new ArrayList <Question>();
		private int numOfQuestions;
		private int currentQuestions;
		
		public Exam() {
			
		}
		public void manuExam(int numberOfQuestions) {
			this.numOfQuestions = numberOfQuestions;
			this.questionForTest = new ArrayList <Question>();
			currentQuestions=0;
		}
		
		public void autoExam(int numberOfQuestion) {
			this.numOfQuestions= numberOfQuestion;
			this.questionForTest = new ArrayList <Question>();
			currentQuestions=0;
			for(int i=0;i<numOfQuestions;i++) {
				randomQuestion();
			}
		}
		public String examToStringWithoutAnswers() {
			
			StringBuffer buffer=new StringBuffer();
			Collections.sort(questionForTest, new QuestionLengthComparator());
			//exam.sortQuestions();
			ArrayList <Question> questions= getQuestionForTest();
			for(int i=0;i<questions.size();i++) {
				buffer.append("\n");
				if(questions.get(i) instanceof OpenQuestion) {
					buffer.append(((OpenQuestion)questions.get(i)).toStringWithoutAnswers());
					//System.out.println(((OpenQuestion)questions[i]).toString());
				}
				if(questions.get(i) instanceof AmericanQuestion) {
					buffer.append(((AmericanQuestion)questions.get(i)).toStringWithoutAnswers());
					
					//System.out.println(((AmericanQuestion)questions[i]).toString());
				}
			}
			return buffer.toString();
		}
		public String examToStringWithAnswers() {
			StringBuffer buffer=new StringBuffer();
			sortQuestions();
			ArrayList <Question> questions=getQuestionForTest();
			for(int i=0;i<questions.size();i++) {
				buffer.append("\n");
				if(questions.get(i) instanceof OpenQuestion) {
					buffer.append(((OpenQuestion)questions.get(i)).toString());
					//System.out.println(((OpenQuestion)questions[i]).toString());
				}
				if(questions.get(i) instanceof AmericanQuestion) {
					buffer.append(((AmericanQuestion)questions.get(i)).toString());
					
					//System.out.println(((AmericanQuestion)questions[i]).toString());
				}
			}
			return buffer.toString();
		}
		private void randomQuestion() {
			int rangeRand=getNumQuestions();
			int rand=0;
			Question randQuestion=null;
			boolean isAdded=false;
			while(!isAdded) {
				rand=(int) (Math.random()*rangeRand)+1;
				randQuestion=getQuestion(rand);
				if(randQuestion!=null) {
					if(randQuestion instanceof OpenQuestion)
						isAdded=addOpenQuestion((OpenQuestion)randQuestion);
					if(randQuestion instanceof AmericanQuestion) {
						Set<String,Boolean> answers=new Set<String,Boolean>();
						answers =chooseRandomAnswers((AmericanQuestion)randQuestion);
						AmericanQuestion americanQuestion=new AmericanQuestion(randQuestion.questionText, answers);
						isAdded=addRandomAmericanQuestion((AmericanQuestion)americanQuestion);
					}
						
				}
			}
		}
		
		public int getNumOfQuestions() {
			return numOfQuestions;
		}
		
//		public boolean addQuestion (Question question) {
//			if(currentQuestions==numOfQuestions)
//				return false;
	//
//			for(int i = 0 ; i < numOfQuestions ; i++) {
//				if(questionForTest[i].getQuestionText().equals(question))
//					return false;
//			}
//			if(question instanceof OpenQuestion) {
//				questionForTest[currentQuestions++] = question;
//			}
//			if(question instanceof AmericanQuestion) {
//				AmericanQuestion americanQuestion=(AmericanQuestion)question;
//				((AmericanQuestion) question).possibleAnswers
//				
//				questionForTest[currentQuestions++] = question;
//				
//			}
//			return true;
//				
//		}
	//	
		public Set<String,Boolean> chooseRandomAnswers(AmericanQuestion americanQuestion) {
			int numAnswers=4;
			int counter=0;
			boolean hasChosenTrue=false;
			Set<String,Boolean> chosenAnswers=new Set<String,Boolean>();
			for(int i=0;i<americanQuestion.getNumAnswers();i++) {
				if(counter==numAnswers)
					break;
				Set<String,Boolean> set = americanQuestion.getPossibleAnswers(i);
				if(set.getValue(0)==true &&!hasChosenTrue) {
					chosenAnswers.add(set.getKey(0),set.getValue(0));
					counter++;
					hasChosenTrue=true;
				}
				if(set.getValue(0)==false) {
					chosenAnswers.add(set.getKey(0),set.getValue(0));
					counter++;
				}
			}
			chosenAnswers.add("None of the answers is true",!hasChosenTrue);
			counter++;
			return chosenAnswers;
//			chosenAnswers.get(counter) =new Set("None of the answers is true",!hasChosenTrue);
//			counter++;
//			return chosenAnswers;
				
			
		}
		public boolean addOpenQuestion(OpenQuestion question) {
			if(currentQuestions==numOfQuestions)
				return false;

			for(int i = 0 ; i < currentQuestions ; i++) {
				if(questionForTest.get(i) instanceof OpenQuestion) {
					if(questionForTest.get(i).getQuestionText().equals(question.questionText))
						return false;
				}
			}
			questionForTest.add(question);
			currentQuestions++;
			
			return true;
		}
		private boolean addRandomAmericanQuestion(AmericanQuestion question) {
			if(currentQuestions==numOfQuestions)
				return false;

			for(int i = 0 ; i < currentQuestions ; i++) {
				if(questionForTest.get(i) instanceof AmericanQuestion) {
					if(questionForTest.get(i).getQuestionText().equals(question.questionText))
						return false;
				}
			}
			questionForTest.add(question);
			currentQuestions++;
			return true;
		}
		public boolean addAmericanQuestion(AmericanQuestion question) {
			for(int i = 0 ; i < currentQuestions ; i++) {
				if(questionForTest.get(i) instanceof AmericanQuestion) {
					if(questionForTest.get(i).getQuestionText().equals(question.questionText))
						return false;
				}
			}
			
			questionForTest.add(question);
			currentQuestions++;
			int counterTruth=0;
			for(int i=0;i<question.getNumAnswers();i++) {
				if(question.getPossibleAnswers(i).getValue(0))
					counterTruth++;
			}
			if(counterTruth==0) {
				question.addAnswer("None of the answers is true",true);
				question.addAnswer("More than 1 answer is true", false);
			}
			else if(counterTruth==1) {
				question.addAnswer("None of the answers is true",false);
				question.addAnswer("More than 1 answer is true", false);
			}
			else {
				question.addAnswer("None of the answers is true",false);
				question.addAnswer("More than 1 answer is true", true);
			}
			
			return true;
		}
		
		public ArrayList<Question> getQuestionForTest() {
			ArrayList<Question> questionsList = new ArrayList<Question>(this.questionForTest);
			return questionsList;
			//return Arrays.copyOf(this.questionForTest, this.questionForTest.size());
		}
		class QuestionLengthComparator implements Comparator<Question>{ //Custom Comparator class according to your need

		    @Override
		        public int compare(Question a, Question b) {
		    	int aLength=0;
				int bLength=0;
				if(a instanceof AmericanQuestion) {
					AmericanQuestion A=(AmericanQuestion)a;
					
					for(int i=0;i<A.getNumAnswers();i++) {
						aLength+=A.getPossibleAnswers(i).getKey(0).length();
					}
					
				}
				else
					aLength=((OpenQuestion)a).getAnswer().length();
				
				if(b instanceof AmericanQuestion) {
					AmericanQuestion B=(AmericanQuestion)b;
					
					for(int i=0;i<B.getNumAnswers();i++) {
						bLength+=B.getPossibleAnswers(i).getKey(0).length();
					}
					
				}
				else
					bLength=((OpenQuestion)b).getQuestionText().length();
				return (aLength-bLength);
		        }
		 }
		public void sortQuestions() {
			//Arrays.sort(this.questionForTest, (a,b) -> a.questionText.compareTo(b.questionText));
//			Arrays.sort(this.questionForTest, (a,b) ->{
//			int aLength=0;
//			int bLength=0;
//			if(a instanceof AmericanQuestion) {
//				AmericanQuestion A=(AmericanQuestion)a;
//				aLength=A.getQuestionText().length();
//				for(int i=0;i<A.getNumAnswers();i++) {
//					aLength+=A.getPossibleAnswers(i).getAnswer().length();
//				}
//				
//			}
//			else
//				aLength=((OpenQuestion)a).getQuestionText().length();
//			
//			if(b instanceof AmericanQuestion) {
//				AmericanQuestion B=(AmericanQuestion)b;
//				bLength=B.getQuestionText().length();
//				for(int i=0;i<B.getNumAnswers();i++) {
//					bLength+=B.getPossibleAnswers(i).getAnswer().length();
//				}
//				
//			}
//			else
//				bLength=((OpenQuestion)b).getQuestionText().length();
//			return -1*(aLength-bLength);
//		});
//		}
	}
		public void manuExam(ArrayList<Question> questions) {
			
			this.questionForTest=questions;
		}
	}
	
	
	
}
