package View;

import java.io.IOException;

import Controller.Controller;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ViewAddQuestion {
	
	private Stage stage;
	private Controller controller;
	
	
	private Label labelStatus;
	
	//Text Fields Open
	private TextField OpenText;
	private TextField OpenAnswer;
	
	//TextFields American
	private TextField americanText=new TextField();
	

	private TextField americanAnswer1=new TextField();
	private TextField americanAnswer2=new TextField();
	private TextField americanAnswer3=new TextField();
	private TextField americanAnswer4=new TextField();
	private TextField americanAnswer5=new TextField();
	private TextField americanAnswer6=new TextField();
	private TextField americanAnswer7=new TextField();
	private TextField americanAnswer8=new TextField();
	private TextField americanAnswer9=new TextField();
	private TextField americanAnswer10=new TextField();
	private TextField[] textFields = {americanAnswer1,americanAnswer2,americanAnswer3,americanAnswer4,americanAnswer5,
			americanAnswer6,americanAnswer7,americanAnswer8,americanAnswer9,americanAnswer10};
	
	
	

	//Labels
	private Label AmericanQuestionText=new Label("Question Text:   ");
	private Label AmericanAnswerText1=new Label("Answer 1:");
	private Label AmericanAnswerText2=new Label("Answer 2:");
	private Label AmericanAnswerText3=new Label("Answer 3:");
	private Label AmericanAnswerText4=new Label("Answer 4:");
	private Label AmericanAnswerText5=new Label("Answer 5:");
	private Label AmericanAnswerText6=new Label("Answer 6:");
	private Label AmericanAnswerText7=new Label("Answer 7:");
	private Label AmericanAnswerText8=new Label("Answer 8:");
	private Label AmericanAnswerText9=new Label("Answer 9:");
	private Label AmericanAnswerText10=new Label("Answer 10:");
	private Label AmericanStatus=new Label("Status:");
	
	
	
	//radio Buttons
	private RadioButton rbTrue1=new RadioButton("True");
	private RadioButton rbFalse1=new RadioButton("False");
	private RadioButton rbTrue2=new RadioButton("True");
	private RadioButton rbFalse2=new RadioButton("False");
	private RadioButton rbTrue3=new RadioButton("True");
	private RadioButton rbFalse3=new RadioButton("False");
	private RadioButton rbTrue4=new RadioButton("True");
	private RadioButton rbFalse4=new RadioButton("False");
	private RadioButton rbTrue5=new RadioButton("True");
	private RadioButton rbFalse5=new RadioButton("False");
	
	private RadioButton rbTrue6=new RadioButton("True");
	
	private RadioButton rbFalse6=new RadioButton("False");
	private RadioButton rbTrue7=new RadioButton("True");
	private RadioButton rbFalse7=new RadioButton("False");
	private RadioButton rbTrue8=new RadioButton("True");
	private RadioButton rbFalse8=new RadioButton("False");
	private RadioButton rbTrue9=new RadioButton("True");
	private RadioButton rbFalse9=new RadioButton("False");
	private RadioButton rbTrue10=new RadioButton("True");
	private RadioButton rbFalse10=new RadioButton("False");
	
	
	//Groups
	private ToggleGroup group1=new ToggleGroup();
	private ToggleGroup group2=new ToggleGroup();
	private ToggleGroup group3=new ToggleGroup();
	private ToggleGroup group4=new ToggleGroup();
	private ToggleGroup group5=new ToggleGroup();
	private ToggleGroup group6=new ToggleGroup();
	private ToggleGroup group7=new ToggleGroup();
	private ToggleGroup group8=new ToggleGroup();
	private ToggleGroup group9=new ToggleGroup();
	private ToggleGroup group10=new ToggleGroup();
	private ToggleGroup[] groups= {group1,group2,group3,group4,group5,group6,group7,group8,group9,group10};
	
	
	//Buttons
	
	public ToggleGroup[] getGroups() {
		return groups;
	}

	private Button addAmericanAnswer=new Button("Add another answer");
	private Button addAmericanQuestion=new Button("Add american question to repository");
	
	public TextField[] getTextFields() {
		return textFields;
	}
	public ViewAddQuestion(Stage stage) {
		this.stage=stage;
		init();
	}
	public String getAmericanText() {
		return americanText.getText();
	}
	public void init() {
		// create labels
			
					stage.setTitle("Add Question");
					stage.getIcons().add(new Image("Books.jpg"));
					Label labelTop = new Label("Add Open Question");
					labelTop.setMinHeight(0);
					labelTop.setMinWidth(100);
					labelTop.setFont(new Font("Arial",30));
					labelTop.setStyle("-fx-font-weight: bold");
					labelTop.setTextAlignment(TextAlignment.CENTER);
					Label labelOpenTest = new Label("Question:");
					Label labelOpenAnswer = new Label("Answer:");
					this.labelStatus=new Label("Status:");
					Button buttonEnterOpen = new Button("Add to repository");
					
					buttonEnterOpen.setOnAction(e -> {
						
						try {
							controller.AddOpenQuestion();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
				});
					Label labelBottom= new Label("Add American Question");
					
					labelBottom.setMinHeight(0);
					labelBottom.setMinWidth(100);
					labelBottom.setFont(new Font("Arial",30));
					labelBottom.setStyle("-fx-font-weight: bold");
					labelBottom.setTextAlignment(TextAlignment.CENTER);
					
					
					
					// create textfields
					this.OpenText = new TextField();
					this.OpenAnswer = new TextField();

					// create gridpane
					GridPane root = new GridPane();
					root.setHgap(10);
					root.setVgap(10);
					root.setPadding(new Insets(10));
					
					root.setAlignment(Pos.CENTER);
					root.add(labelTop, 0, 0, 3, 1);
					root.add(labelOpenTest, 0, 1);
					root.add(labelOpenAnswer, 0, 2);
					
					root.add(buttonEnterOpen, 0, 4);
					root.add(labelStatus, 1, 4);
					root.add(OpenText, 1, 1);
					root.add(OpenAnswer, 1, 2);
					
					//AmericanQuestion
					root.add(labelBottom, 0, 5);
					root.add(AmericanQuestionText,0,6);
					root.add(this.americanText, 1, 6);
					
				
					
					
					
					
					
					
					//groups
					
					rbTrue1.setToggleGroup(group1);
					rbFalse1.setToggleGroup(group1);
					rbTrue2.setToggleGroup(group2);
					rbFalse2.setToggleGroup(group2);
					rbTrue3.setToggleGroup(group3);
					rbFalse3.setToggleGroup(group3);
					rbTrue4.setToggleGroup(group4);
					rbFalse4.setToggleGroup(group4);
					rbTrue5.setToggleGroup(group5);
					rbFalse5.setToggleGroup(group5);
					rbTrue6.setToggleGroup(group6);
					rbFalse6.setToggleGroup(group6);
					rbTrue7.setToggleGroup(group7);
					rbFalse7.setToggleGroup(group7);
					rbTrue8.setToggleGroup(group8);
					rbFalse8.setToggleGroup(group8);
					rbTrue9.setToggleGroup(group9);
					rbFalse9.setToggleGroup(group9);
					rbTrue10.setToggleGroup(group10);
					rbFalse10.setToggleGroup(group10);
					
					//select 1 radio button
					rbTrue1.setSelected(true);
					rbTrue2.setSelected(true);
					rbTrue3.setSelected(true);
					rbTrue4.setSelected(true);
					rbTrue5.setSelected(true);
					rbTrue6.setSelected(true);
					rbTrue7.setSelected(true);
					rbTrue8.setSelected(true);
					rbTrue9.setSelected(true);
					rbTrue10.setSelected(true);
					
					
					
					root.add(AmericanAnswerText1, 0, 9);
					root.add(rbTrue1, 1, 9);
					root.add(rbFalse1, 2, 9);
					root.add(americanAnswer1, 4, 9);
					root.add(AmericanAnswerText2, 0, 10);
					root.add(rbTrue2, 1, 10);
					root.add(rbFalse2, 2, 10);
					root.add(americanAnswer2, 4, 10);
					root.add(AmericanAnswerText3, 0, 11);
					root.add(rbTrue3, 1, 11);
					root.add(rbFalse3, 2, 11);
					root.add(americanAnswer3, 4, 11);
					root.add(AmericanAnswerText4, 0, 12);
					root.add(rbTrue4, 1, 12);
					root.add(rbFalse4, 2, 12);
					root.add(americanAnswer4, 4, 12);
					root.add(AmericanAnswerText5, 0, 13);
					root.add(rbTrue5, 1, 13);
					root.add(rbFalse5, 2, 13);
					root.add(americanAnswer5, 4, 13);
					root.add(AmericanAnswerText6, 0, 14);
					root.add(rbTrue6, 1, 14);
					root.add(rbFalse6, 2, 14);
					root.add(americanAnswer6, 4, 14);
					root.add(AmericanAnswerText7, 0, 15);
					root.add(rbTrue7, 1, 15);
					root.add(rbFalse7, 2, 15);
					root.add(americanAnswer7, 4, 15);
					root.add(AmericanAnswerText8, 0, 16);
					root.add(rbTrue8, 1, 16);
					root.add(rbFalse8, 2, 16);
					root.add(americanAnswer8, 4, 16);
					root.add(AmericanAnswerText9, 0, 17);
					root.add(rbTrue9, 1, 17);
					root.add(rbFalse9, 2, 17);
					root.add(americanAnswer9, 4, 17);
					root.add(AmericanAnswerText10, 0, 18);
					root.add(rbTrue10, 1, 18);
					root.add(rbFalse10, 2, 18);
					root.add(americanAnswer10, 4, 18);
					
					root.add(addAmericanQuestion, 0, 19);
					root.add(AmericanStatus, 1, 19);
					
					this.addAmericanQuestion.setOnAction(e->{
						this.controller.addAmericanQuestion();
					});

					Scene scene = new Scene(root, 900, 800);
					stage.setScene(scene);
					

	}

	public void setAmericanStatus(String americanStatus) {
		AmericanStatus.setText(americanStatus);
	}
	public void show() {
		stage.show();
		
	}
	public void setController(Controller c) {
		this.controller = c;
	}

	public String getOpenText() {
		return OpenText.getText();
	}

	public String getOpenAnswer() {
		return OpenAnswer.getText();
	}

	public void setLabelStatus(String string) {
		this.labelStatus.setText(string); 
	}

	
	

}
