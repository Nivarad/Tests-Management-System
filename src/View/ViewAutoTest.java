package View;

import java.io.IOException;
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


public class ViewAutoTest{
	private Controller controller;
	private Stage stage;
	
	//Label
	private Label numQuestionsText;
	private Label questionsString;
	
	//TextFields
	private TextField numQuestions;
	
	//Buttons
	private Button enter;
	
	
	
	
	
	public ViewAutoTest(Stage stage) {
		this.stage=stage;
		stage.setTitle("Automatic Test Creator");
		stage.getIcons().add(new Image("Books.jpg"));
		init1();
	}
	public void init1(){
		this.numQuestionsText=new Label("How many questions do you wish to have on your exam?");
		
		this.numQuestions=new TextField();
		
		this.enter=new Button("Create Test");
		
		this.enter.setOnAction(e ->{
			if(!numQuestionsText.getText().trim().equals("")) {
				try {
					this.controller.CreateAutoTest(Integer.parseInt(numQuestions.getText()));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridPane root = new GridPane();
		
		root.add(numQuestionsText, 1, 1);
		root.add(numQuestions, 1, 2);
		root.add(enter, 1, 3);
		
		Scene scene = new Scene(root,400, 400);
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
	}
	public void init2(){
		Label title=new Label("The Exam:");
		this.questionsString=new Label();
		GridPane root = new GridPane();
		
		root.add(title, 1, 1);
		root.add(questionsString, 1, 2);
		
		Scene scene = new Scene(root,800, 800);
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
	}
	public void setController(Controller c) {
		this.controller=c;
	}
	public void updateData(String exam) {
		this.questionsString.setText(exam);
		stage.show();
		
	}
	public void show() {
		stage.show();
	}

}
