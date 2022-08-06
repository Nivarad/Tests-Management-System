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


public class ViewRemoveAnswer{
	private Text title;
	private Controller controller;
	private TextField QuestionId;
	private ArrayList<AmericanQuestion> americanQuestions;
	private ArrayList<OpenQuestion> openQuestiona;
	ListView<AmericanQuestion> listAmerican;
	private Button buttonEnterOpen;
	ListView<OpenQuestion> listOpen;
	private Stage stage;
//	public String[] str= {"hey","blue","hello"};
	ArrayList<AmericanQuestion> americans;
	ArrayList<OpenQuestion> opens;
	String questions;
	private Label labelTitle;

	private Label labelStatus;
	private Label questionsString;
	private TextField answerId;
	
	
	
	public void setLabelStatus(Label labelStatus) {
		this.labelStatus = labelStatus;
	}
	public ViewRemoveAnswer(Stage stage) {
		this.stage=stage;
		stage.getIcons().add(new Image("Books.jpg"));
		stage.setTitle("Remove Answer");
		Label labelTop = new Label("Remove Answer from American Question");
		labelTop.setMinHeight(0);
		labelTop.setMinWidth(100);
		labelTop.setFont(new Font("Arial",30));
		labelTop.setStyle("-fx-font-weight: bold");
		
		this.buttonEnterOpen = new Button("Remove");
		Label questionId = new Label("Question ID you want to change:");
		Label answerNum = new Label("answer number you wish to remove:");
		
		this.QuestionId=new TextField();
		this.answerId=new TextField();
		this.labelStatus=new Label("Status:");
		Label choose=new Label("Remove:       ");
		
		
		
		init();			
//		createTableAmerican();
		
		GridPane root = new GridPane();
		
		root.add(labelTop, 1, 0);
		
		this.questionsString = new Label(questions);
		root.add(this.questionsString, 1, 3);
		root.add(questionId, 0, 6);
		root.add(QuestionId, 1, 6);
		root.add(answerNum, 0, 7);
		root.add(answerId, 1, 7);
		
		
		root.add(buttonEnterOpen, 1, 8);
		root.add(labelStatus, 1, 9);
		Scene scene = new Scene(root,800, 800);
		
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
		
		buttonEnterOpen.setOnAction(e -> {
			int idQ=Integer.parseInt(QuestionId.getText());
			int idA=Integer.parseInt(answerId.getText());
			this.controller.removeAnswer(idQ, idA);
	});}
	
	public void setTitle(String text) {

		this.title.setText(text);
	}
	private void init() {
		this.labelTitle = new Label("Questions");
		this.labelTitle.setMinHeight(0);
		this.labelTitle.setMinWidth(100);
		this.labelTitle.setFont(new Font("Arial",30));
		this.labelTitle.setStyle("-fx-font-weight: bold");
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
	public void setLabelStatus(String string) {
		this.labelStatus.setText(string);
		
	}
	public void UpdateData(String questions) {
		this.questionsString.setText(questions);
	}
}
