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


public class ViewEditQuestion{
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
	private Label questions;
	private Label labelTitle;

	private Label labelStatus;
	
	
	
	public void setLabelStatus(Label labelStatus) {
		this.labelStatus = labelStatus;
	}
	
	public ViewEditQuestion(Stage stage) {
		this.stage=stage;
		stage.setTitle("Edit Question");
		stage.getIcons().add(new Image("Books.jpg"));
		
		
		Label labelTop = new Label("Edit Question");
		labelTop.setMinHeight(0);
		labelTop.setMinWidth(100);
		labelTop.setFont(new Font("Arial",30));
		labelTop.setStyle("-fx-font-weight: bold");
		Label explain = new Label("Click on the question you want to edit");
		this.buttonEnterOpen = new Button("Edit Question");
		Label questionId = new Label("Question ID you want to change:");
		Label labelOpenTest = new Label("new Question Text:");
		this.QuestionText=new TextField();
		this.labelStatus=new Label("Status:");
		TextField questionNum=new TextField();
		
		
		
		init();			
//		createTableAmerican();
		
		GridPane root = new GridPane();
		
		root.add(labelTop, 1, 0);
		root.add(explain, 1, 2);
		questions=new Label();
		root.add(questions, 1, 3);
		root.add(labelOpenTest, 0, 4);
		root.add(this.QuestionText, 1, 4);
		
		
		root.add(questionId, 0, 5);
		root.add(questionNum, 1, 5);
		
		root.add(buttonEnterOpen, 1, 7);
		root.add(labelStatus, 2, 7);
		Scene scene = new Scene(root,800, 800);
		
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
		
		buttonEnterOpen.setOnAction(e -> {
			int id=Integer.parseInt(questionNum.getText());
			String newText=QuestionText.getText();
			System.out.println(newText);
			System.out.println(id);
			controller.editQuestion(id, newText);
				
			
			
			
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
	public void updateData(String questionsString) {
		this.questions.setText(questionsString);
	}

}
