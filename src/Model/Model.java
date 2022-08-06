package Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.security.DrbgParameters.NextBytes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Model.Management.Exam;


public class Model {
	static Scanner input;
	private Management management;
	public Model() throws FileNotFoundException {
		management=null;
		
		try {
			FileInputStream fileIn=new FileInputStream("src//BinaryFiles//BinaryQuestions.txt");
			ObjectInputStream in=new ObjectInputStream(fileIn);
			Management managementTry=(Management)in.readObject();			
			in.close();
			fileIn.close();
			management=managementTry;
		}
		catch(Exception e) {				
			management=new Management();
		}
		
		
		boolean menuBool=true;
		input=new Scanner(System.in);
		//prints menu of programs
		
		//int choose=input.nextInt();
		String chooseStr=" ";
	}
	public ArrayList<AmericanQuestion> getAmericanQuestions(){
		return management.getAmericanQuestions();
	}
	public ArrayList<OpenQuestion> getOpenQuestions(){
		return management.getOpenQuestions();
	}
	public String getQuestionsString() {
		return management.toString();
	}
	public static void listFilesForSRC(final File folder,ArrayList<File> fileList) { 	//תטען את כל הקבצי מבחן המתאימים להעתקה 
		
		
	    for (final File fileEntry : folder.listFiles()) {
	    	String pathName=fileEntry.getPath();
	    	fileList.add(fileEntry);
	    }
	        	  
	}
	public boolean addOpenQuestion(String text,String answer) {	//הוספת שאלה פתוחה עבור מבחן מסוג יידני
		OpenQuestion openQuestion=new OpenQuestion(text, answer);
		boolean check=management.addOpenQuestion(openQuestion);
		return check;
	}
	public boolean addAmericanQuestion(String text,Set<String,Boolean> answers) {	//הוספת שאלה אמריקאית עבור מבחן מסוג יידני
		
		AmericanQuestion americanQuestion=new AmericanQuestion(text,answers);
		boolean check=this.management.addAmericanQuestion(americanQuestion);
		return check;
	}
	public static void showMenu() {		//הצגת התפריט
		System.out.println("Please select an option: \n1 - Show all the questions in the repository"
				+ "\n2 - Add question to the repository \n3 - Edit an existed question in the repository"
				+ "\n4 - Edit an existed answer for a question \n5 - Delete an existed answer for a question"
				+"\n6 - Create a manual test \n7 -Create an automatic test\n8 – Copy an existing test and add questions on top of it \n9 - Exit the program");
	}
	public static boolean saveToFile(String name,LocalDate date,Management.Exam exam) throws FileNotFoundException {	//שמירת מבחן לקובץ טקסט
		
		String str=null;
		File file=null;
		str= name.contains("exam") ?  exam.examToStringWithoutAnswers() : exam.examToStringWithAnswers();
		
		try {
			if(name.contains("exam"))
				file=new File("src/Tests/"+name+"_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");
			else
				file=new File("src/Solutions/"+name+"_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");
			if(file.createNewFile()) 
				System.out.println("exam file created");
			else
				System.out.println("File already exist");
			
			
		}
		catch(Exception error) {
			System.out.println("an error occured ");
			return false;
		}
		//save exam in the file
		PrintWriter writer=new PrintWriter(file);
		writer.print(str);
		writer.close();
		return true;
	}
	public void save() throws IOException {
		FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryQuestions.txt");    
		ObjectOutputStream out=new ObjectOutputStream(fout);    
		out.writeObject(management);   
		out.flush();
		out.close();
	}
	public boolean EditQuestion(int id,String text) {
		if(management.getQuestion(id)==null)
			return false;
		management.getQuestion(id).setQuestionText(text);
		return true;
	}
	public Boolean editAnswer(int id,int numAnswer,String text,boolean check) {
		if(management.getQuestion(id)==null)
			return false;
		if(management.getQuestion(id) instanceof OpenQuestion)
			management.changeOpenAnswer(id, text);
		else
			management.changeAmericanAnswer(id, numAnswer, text, check);
		return true;
	}

	public boolean RemoveAnswer(int questionID, int answerID) {
		boolean check=management.removeAmericanAnswer(questionID,answerID);
		return check;
	}
	public String createAutoTest(int numQuestions) throws IOException {
		Management.Exam exam=new Management().new Exam();
		exam.autoExam(numQuestions);
		LocalDate date = LocalDate.now(); // Create a date object
		
		//create a file for the exam questions
		saveToFile("exam",date,exam);
		
		//create a file for the exam solutions 
		saveToFile("solution", date, exam);
		
		// save the exam to binary file
		FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryExam_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");    
		  ObjectOutputStream out=new ObjectOutputStream(fout);    
		  out.writeObject(exam);   
		  out.flush();
		  out.close();
		  
		  return exam.examToStringWithAnswers();
		
		
	}
//	public static void addQuestionToExam(int questionId) {
//		Management.Exam exam=new Management().new Exam();
//		exam.manuExam(10);
//		if(a)
//		
//	}
	public String createManuTest(java.util.Set<Integer> set) throws IOException {
		ArrayList<Question> questions=new ArrayList<Question>();
		for(int i : set) {
			questions.add(management.getQuestion(i));
		}
		Management.Exam exam=new Management().new Exam();
		exam.manuExam(questions);
		LocalDate date = LocalDate.now(); // Create a date object
		
		//create a file for the exam questions
		saveToFile("exam",date,exam);
		
		//create a file for the exam solutions 
		saveToFile("solution", date, exam);
		
		// save the exam to binary file
		FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryExam_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");    
		  ObjectOutputStream out=new ObjectOutputStream(fout);    
		  out.writeObject(exam);   
		  out.flush();
		  out.close();
		  
		  return exam.examToStringWithAnswers();
	}
	public String examToString() { 	//תטען את כל הקבצי מבחן המתאימים להעתקה 
		String exams="";
		ArrayList<File> fileList=new ArrayList<File>();
		File examsFolder=new File("src//Tests");
	    for (final File fileEntry : examsFolder.listFiles()) {
	    	String pathName=fileEntry.getPath();
	    	fileList.add(fileEntry);
	    }
	    int counter=0;
		for(File file : fileList) {
			exams+=(counter+1)+". Exam Name: "+file.getName()+"\n";
			counter++;
		}
		return exams;
	    
	        	  
	}
	public void copyTest(String id) throws ClassNotFoundException, IOException {
		Management.Exam exam=new Management().new Exam();
		try {
			
			ArrayList<File> fileList=new ArrayList<File>();
			File examsFolder=new File("src//Tests");
			listFilesForSRC(examsFolder, fileList);
		
			
			//open a new file to copy the exam into it
			String pathExam=fileList.get(Integer.parseInt(id)-1).getName();
			
			ArrayList<File> binaryFileList=new ArrayList<File>();
			File BinaryFolder=new File("src//BinaryFiles");
			listFilesForSRC(BinaryFolder, binaryFileList);
			pathExam=pathExam.replace("exam", "BinaryExam");
			
			FileInputStream examIn = null;
			for(File file: binaryFileList) {
				if(file.getName().equals(pathExam))
					examIn=new FileInputStream(file.getPath());
			}
			//FileInputStream examIn=new FileInputStream(binaryFile.getPath());
			ObjectInputStream inExam =new ObjectInputStream(examIn);
			
			exam=(Management.Exam)inExam.readObject();
			
			
		}

		catch(Exception e) {
			
		}
		LocalDate date=LocalDate.now();
		//create a file for the exam questions
		saveToFile("examCopy",date,exam);
		
		//create a file for the exam solutions 
		saveToFile("solutionCopy", date, exam);
		
		// save the exam to binary file
		FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryExamCopy_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");    
		ObjectOutputStream out=new ObjectOutputStream(fout);    
		out.writeObject(exam);   
		out.flush();
		out.close();
	}
	
	
	
	
}
