package View;

import java.util.ArrayList;
import java.util.Comparator;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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


public class ViewEditAnswer{
	private Text title;
	private Controller controller;
	private TextField QuestionText;
	private ArrayList<AmericanQuestion> americanQuestions;
	private ArrayList<OpenQuestion> openQuestiona;
	ListView<AmericanQuestion> listAmerican;
	private Button buttonEnterOpen;
	ListView<OpenQuestion> listOpen;
	private Stage stage;
//	public String[] str= {"hey","blue","hello"};
	ArrayList<AmericanQuestion> americans;
	ArrayList<OpenQuestion> opens;
	
	private Label labelTitle;
	private Label labelQuestions;
	private Label labelStatus;
	
	
	private ToggleGroup group;
	private RadioButton rb1;
	private RadioButton rb2;
	
	public ViewEditAnswer(Stage stage) {
		this.stage=stage;
		stage.setTitle("Edit Answer");
		stage.getIcons().add(new Image("Books.jpg"));
		init();
		
		
	}

	public void setTitle(String text) {

		this.title.setText(text);
	}
	private void init() {
		this.labelTitle = new Label("Questions");
		this.labelTitle.setMinHeight(0);
		this.labelTitle.setMinWidth(100);
		this.labelTitle.setFont(new Font("Arial",30));
		this.labelTitle.setStyle("-fx-font-weight: bold");
		
		Label labelTop = new Label("Edit Answer");
		labelTop.setMinHeight(0);
		labelTop.setMinWidth(100);
		labelTop.setFont(new Font("Arial",30));
		labelTop.setStyle("-fx-font-weight: bold");
		Label explain = new Label("Click on the question you want to edit");
		this.buttonEnterOpen = new Button("Edit answer");
		Label questionId = new Label("Question ID you want to change:");
		Label labelOpenTest = new Label("new answer Text:");
		this.QuestionText=new TextField();
		this.labelStatus=new Label("Status:");
		TextField questionNum=new TextField();
		Label AnswerID = new Label("Answer number to edit(fill only for american):");
		TextField answerNum=new TextField();
		
		this.labelQuestions=new Label("");
		
		this.group = new ToggleGroup();
		this.rb1=new RadioButton("True");
		this.rb1.setToggleGroup(group);
		this.rb1.setSelected(true);
		
		this.rb2=new RadioButton("False");
		this.rb2.setToggleGroup(group);
		
		
		
		
				

		
		GridPane root = new GridPane();
		
		root.add(labelTop, 1, 0);
		root.add(explain, 1, 2);
		
		root.add(labelQuestions, 1, 3);
		root.add(labelOpenTest, 0, 4);
		root.add(this.QuestionText, 1, 4);
		root.add(this.rb1, 1, 6);
		root.add(this.rb2, 2, 6);
		root.add(questionId, 0, 8);
		root.add(questionNum, 1, 8);
		root.add(AnswerID, 0, 10);
		root.add(answerNum, 1, 10);
		root.add(buttonEnterOpen, 1, 12);
		root.add(labelStatus, 2, 12);
		Scene scene = new Scene(root,800, 800);
		
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
		
		buttonEnterOpen.setOnAction(e -> {
			int questionID=Integer.parseInt(questionNum.getText());
			String newText=QuestionText.getText();
			int answerId=0;
			if(!answerNum.getText().equals(""))
				answerId=Integer.parseInt(answerNum.getText());			
			
			controller.editAnswer(questionID, answerId, newText);
				
			
			
			
	});
	}

	public void updateData(String questions) {
		this.labelQuestions.setText(questions);
		this.labelStatus.setText("Status: Answer changed");
	}


	public void show() {
		stage.show();
		
	}

	public String getTitle() {
		return title.getText();
	}

	public void setController(Controller c) {
		this.controller = c;
	}
	
	public ToggleGroup getTgr() {
		return group;
	}

	

}
