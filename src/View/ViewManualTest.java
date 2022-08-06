package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import Controller.Controller;
import Model.AmericanQuestion;
import Model.OpenQuestion;
import Model.Question;
import Model.Tuple;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ViewManualTest{
	private Controller controller;
	private Stage stage;
	Set<Integer> set=new HashSet<Integer>();
	
	//Label
	private Label RepositoryQuestions;
	private Label AddQuestionID;
	private Label RepositoryQuestionsString;
	private Label ExamTitle;
	private Label ExamQuestions;
	public void setExamQuestions(String label) {
		ExamQuestions.setText(label);
	}
	private Label statusLabel;
	
	
	
	public void setStatusLabel(String label) {
		this.statusLabel.setText(label);
	}
	//TextFields
	private TextField QuestionId;
	
	//Buttons
	private Button enter;
	private Button addQuestion;
	
	
	
	
	
	public ViewManualTest(Stage stage) {
		this.stage=stage;
		stage.setTitle("Manual Test Creation");
		stage.getIcons().add(new Image("Books.jpg"));
		init1();
	}
	public void init1(){
		this.RepositoryQuestions=new Label("All Questions");
		this.RepositoryQuestionsString=new Label();
		this.AddQuestionID=new Label("enter a question id to add to the test");
		this.statusLabel=new Label("Status:");
		
		this.QuestionId=new TextField();
		this.QuestionId.setMaxWidth(70);
		
		this.enter=new Button("Create Test");
		this.addQuestion=new Button("Add Questions");
		this.ExamQuestions=new Label();
		this.ExamTitle=new Label("Exam Questions");
		this.enter.setOnAction(e ->{
			if(!this.set.isEmpty()) {
				try {
					controller.createManuTest(set);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.addQuestion.setOnAction(e ->{
			set.add(Integer.parseInt(this.QuestionId.getText()));
			this.statusLabel.setText("Question Added");
		});
		GridPane root = new GridPane();
		
		root.add(RepositoryQuestions, 1, 1);
		root.add(RepositoryQuestionsString, 1, 2);
		root.add(AddQuestionID, 0, 3);
		root.add(QuestionId, 1, 3);
		root.add(addQuestion, 1, 4);
		root.add(enter, 2, 4);
		root.add(ExamTitle, 1, 5);
		root.add(ExamQuestions, 1, 6);
		
		Scene scene = new Scene(root,800, 800);
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
	}
	
	public void setController(Controller c) {
		this.controller=c;
	}
	public void updateDataRepository(String questions) {
		this.RepositoryQuestionsString.setText(questions);
		stage.show();
		
	}
	public void updateExamData(String exam) {
		this.ExamQuestions.setText(exam);
	}
	public void show() {
		stage.show();
	}
	public void updateData(String questionsString) {
		this.RepositoryQuestionsString.setText(questionsString);
	}

}
