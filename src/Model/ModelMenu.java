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
import java.util.Scanner;


public class ModelMenu {
	static Scanner input;
	
	public static void listFilesForSRC(final File folder,ArrayList<File> fileList) { 	//תטען את כל הקבצי מבחן המתאימים להעתקה 
		
		
	    for (final File fileEntry : folder.listFiles()) {
	    	String pathName=fileEntry.getPath();
	    	fileList.add(fileEntry);
	    }
	        	  
	}
	public static boolean addOpenQuestion(Scanner input,Management manage) {	//הוספת שאלה פתוחה עבור מבחן מסוג יידני
		System.out.println("What is the question you want to add?");
		String question=input.nextLine();
		question=input.nextLine();
		System.out.println("What is the answer for the question?");
		String answer=input.nextLine();
		OpenQuestion openQuestion=new OpenQuestion(question, answer);
		boolean check=manage.addOpenQuestion(openQuestion);
		return check;
	}
	public static boolean addAmericanQuestion(Scanner input,Management manage) {	//הוספת שאלה אמריקאית עבור מבחן מסוג יידני
		System.out.println("What is the question you want to add?");
		String question=input.nextLine();
		question=input.nextLine();
		
		System.out.println("How many answers do you want to add to the question?");
		int numAnswers=0;
		while(numAnswers<=0) {
			numAnswers=input.nextInt();
			
		}
		Set<String,Boolean> answers=new Set<String,Boolean>();
		for(int i=0;i<numAnswers;i++) {
			System.out.println("please add a possible answer :");
			input.nextLine();
			String answer=input.nextLine();
			
			System.out.println("is the answer true or false?");
			Boolean bool=input.nextBoolean();
			answers.add(answer, bool);
			
		}
		AmericanQuestion americanQuestion=new AmericanQuestion(question,answers);
		boolean check=manage.addAmericanQuestion(americanQuestion);
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
	
	public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
		Management management=null;
		FileInputStream fileIn=new FileInputStream("src//BinaryFiles//BinaryQuestions.txt");
		try {
			
			ObjectInputStream in=new ObjectInputStream(fileIn);
			Management managementTry=(Management)in.readObject();			
			in.close();
			fileIn.close();
			management=managementTry;
		}
		catch(Exception e) {
			if(fileIn!=null)
				System.out.println("Couldn't load saved repository because the program version updated since \n");
			management=new Management();
		}
		
		
		boolean menuBool=true;
		input=new Scanner(System.in);
		//prints menu of programs
		showMenu();
		int choose=input.nextInt();
		String chooseStr=" ";
		while(menuBool){
			switch(choose) {
				case 1:{
					//show user the questions in repository
					System.out.println(management.toString());
					break;
				}
				case 2:{
					//add question to the repository
					while(chooseStr!="Y"||chooseStr!="N"||chooseStr!="M") {
						System.out.println("Do you want to add an open question? if yes Press Y \n if you will press N you will be prompted to add american question \n If you want to go back to menu press M");
						chooseStr=input.next();
						//add open question 
						if(chooseStr.toUpperCase().equals("Y")) {
							boolean check=addOpenQuestion(input, management);
							if(check) 
								System.out.println("Question added to repository seccussfully");
							else
								System.out.println("Question already Exist in the repository -therefore it's not added again");
							break;
						}
						else if(chooseStr.toUpperCase().equals("N")) {
							//add american question 
							boolean check=addAmericanQuestion(input, management);
							if(check) 
								System.out.println("Question added to repository seccussfully");
							else
								System.out.println("Question already Exist in the repository -therefore it's not added again");
							break;
						}
						else if(chooseStr.toUpperCase().equals("M")) {
							break;
						}
						else
							System.out.println("Invalid input");
						
						}
					break;
					}
				case 3:{
					//edit question text in the repository by id 
					System.out.println("In order to edit an existed question please enter the question id number\n  if you are not sure what the id please enter -1\n If you want to to back to menu press -2 ");
					
					int choseInt=-5;
					while(choseInt<=0 &&choseInt!=-2) {
						choseInt=input.nextInt();
						input.nextLine();
						if(choseInt==-1) {
							System.out.println(management.toString());
							System.out.println("Please enter the question id you want to change ");
						}
					}
					if(choseInt==-2)
						break;
					Question question=management.getQuestion(choseInt);
					if(question==null) {
						System.out.println("There is no question with that Id , you will be returned to main menu");
						break;
					}
					else {
						System.out.println("Enter the new text for the question you chose to edit");
						
						String questionText=input.nextLine();
						management.changeQuestionText(choseInt, questionText);
						break;
						
					}
						
				}
				case 4:{
					//edit question answer in the repository
					System.out.println("In order to edit an answer for an existing question please enter the question id number\n  if you are not sure what the id please enter -1\n If you want to to back to menu press -2 ");
				
					int choseInt=-5;
					while(choseInt<=0 &&choseInt!=-2) {
						choseInt=input.nextInt();
						input.nextLine();
						if(choseInt==-1) {
							System.out.println(management.toString());
							System.out.println("Please enter the question id you want to change ");
						}
					}
					if(choseInt==-2)
						break;
					Question question=management.getQuestion(choseInt);
					if(question==null) {
						System.out.println("There is no question with that Id , you will be returned to main menu");
						break;
					}
					else {
						int i=0;
						System.out.println(question.toString());
						if(question instanceof AmericanQuestion) {
							System.out.println("choose the number of answer you want to edit \nIf you want to go back to main menu press -1");
							
							while(i<1 &&i!=-1) {
								i=input.nextInt();
								if(i<1 &&i!=-1)
									System.out.println("You pressed invalid number, please try again");
							}
							if(i==-1)
								break;
							else {
								System.out.println("Please enter a new text for the answer");
								String answer=input.next();
								System.out.println("is the answer for the question true or false?");
								boolean bool=input.nextBoolean();
								management.changeAmericanAnswer(choseInt, i, answer, bool);
							}
							
						}
						else {
							System.out.println("Please enter a new text for the answer");
							String answer=input.nextLine();
							management.changeOpenAnswer(choseInt, answer);
						}
					
					}
					break;
					
				}
				case 5:{
					//remove a question from the repository
					System.out.println("In order to remove an answer for an existing question please enter the question id, it can only be done for american quesstion number\n  if you are not sure what the id please enter -1\n If you want to to back to menu press -2 ");
					
					int choseInt=-5;
					while(choseInt<=0 &&choseInt!=-2) {
						choseInt=input.nextInt();
						if(choseInt==-1) {
							System.out.println(management.toString());
							System.out.println("Please enter the question id you want to change ");
						}
					}
					if(choseInt==-2)
						break;
					Question question=management.getQuestion(choseInt);
					if(question==null) {
						System.out.println("There is no question with that Id , you will be returned to main menu");
						break;
					}
					else {
						System.out.println(question.toString());
						if(question instanceof AmericanQuestion) {
							System.out.println("choose the number of answer you want to remove \nIf you want to go back to main menu press -1");
							int i=0;
							while(i<1 &&i!=-1) {
								i=input.nextInt();
								if(i<1 &&i!=-1)
									System.out.println("You pressed invalid number, please try again");
							}
							if(i==-1)
								break;	
							boolean check=management.removeAmericanAnswer(choseInt, i);
							if(check)
								System.out.println("answer removed");
							else
								System.out.println("answer wasn't removed for some reason -please contact admin");	
						}
					}
					
				break;
				}
				case 6:{
					//create a manual test 
					System.out.println("How many questions you wish to be on the exam?");
					int numQuestions=input.nextInt();
					System.out.println(management.toString());
					
					Management.Exam exam= new Management().new Exam();
					exam.manuExam(numQuestions);
					int counter=0;
					while(counter!=numQuestions) {
						System.out.println("please enter the ID of question you want to add");
						int id=input.nextInt();
						Question question=management.getQuestion(id);
						if(question instanceof AmericanQuestion) {
							System.out.println(question.toString());
							System.out.println("how many answers do you want to be included?");
							int numAnswers=input.nextInt();
							System.out.println("Please choose the answers you want to be included");
							Set<String,Boolean> answers=new Set<String,Boolean>();
							AmericanQuestion american=(AmericanQuestion)question;
							for(int i=0;i<numAnswers;i++) {
								Set<String,Boolean> answer=new Set<String,Boolean>();		
								int answerId=input.nextInt();
								answer=american.getPossibleAnswers(answerId-1);
								answers.add(answer.getKey(0), answer.getValue(0));
								//answers[i]=((AmericanQuestion)question).getPossibleAnswers(input.nextInt()-1);
							}
							AmericanQuestion americanQuestion=new AmericanQuestion(question.questionText,answers);
							boolean check=exam.addAmericanQuestion(americanQuestion);
							if(check) {
								counter++;
								check=false;
							}
						}
						if(question instanceof OpenQuestion) {
							boolean check=exam.addOpenQuestion((OpenQuestion)question);
							if(check) {
								counter++;
								check=false;
							}
						}
						
						
					
					
					}
					//print exam and save exam file and solution file
					System.out.println(exam.examToStringWithAnswers());
					LocalDate date = LocalDate.now(); // Create a date object
					
					//create a file for the exam questions
					saveToFile("exam",date,exam);
					
					//create a file for the exam solutions 
					saveToFile("solution", date, exam);
//					
//					
					// save the exam to binary file
					FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryExam_"+date.getYear()+"_"+date.getMonth()+"_"+date.getDayOfMonth()+".txt");    
					  ObjectOutputStream out=new ObjectOutputStream(fout);    
					  out.writeObject(exam);   
					  out.flush();
					  out.close();
					break;
				}
				case 7:{
					//create an automatic exam
					Management.Exam exam=new Management().new Exam();
					System.out.println("How many question you wish to be on the exam?");
					int numQuestions=input.nextInt();
					exam.autoExam(numQuestions);
					
					//print exam and save exam file and solution file
					System.out.println(exam.examToStringWithAnswers());
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
					
					break;
				}
				case 8:{
					Management.Exam exam=null;
					try {
						ArrayList<File> fileList=new ArrayList<File>();
						File examsFolder=new File("src//Tests");
						listFilesForSRC(examsFolder, fileList);
						int counter=0;
						for(File file : fileList) {
							System.out.println((counter+1)+". Exam Name: "+file.getName());
							counter++;
						}
						
						System.out.println("Which Exam do you want to copy?");
						int examIndex=input.nextInt();
						//open a new file to copy the exam into it
						String pathExam=fileList.get(examIndex-1).getName();
						
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
	//					in.close();
	//					fileIn.close();
					}
					catch(Exception e) {
						System.out.println("could'nt load the exam that was selected -probably because the program exam class was updated since");
						break;
					}
					
					choose=0;
					while(choose!=-1) {
						System.out.println("if you want to add a question please press 1  \n "+
								"in order to save the test please enter -1");
						choose=input.nextInt();
						if(choose==1) {
							//add questions to the test
							System.out.println("please enter a question id you wish to add to the exam");
							System.out.println("if you wish to present all the questions in the repository please enter -1");
							choose=input.nextInt();
							if(choose==-1) {
								System.out.println(management.toString());
								System.out.println("please enter the id of the question you want to add ");
								choose=input.nextInt();
							}
								
							
							
							Question question=management.getQuestion(choose);
							if(question==null)
								System.out.println("you entered invalid question id");
							if(question instanceof OpenQuestion) 
								exam.addOpenQuestion((OpenQuestion)question);
							if(question instanceof AmericanQuestion) 
								exam.addAmericanQuestion((AmericanQuestion)question);
						}
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
					break;
				}
		
				case 9:{
					// save the repository to binary file and quit 
					FileOutputStream fout=new FileOutputStream("src//BinaryFiles//BinaryQuestions.txt");    
					  ObjectOutputStream out=new ObjectOutputStream(fout);    
					  out.writeObject(management);   
					  out.flush();
					  out.close();
					  System.out.println("Exiting...");
					  menuBool=false;
					break;
				}
				default :{
					System.out.println("Invalid input - try again");
					Thread.sleep(1000);
				}
			
			}
			if(choose!=9) {
				System.out.println();
				showMenu();
				choose=input.nextInt();
			}
			
			
			
		}

	}	
}
