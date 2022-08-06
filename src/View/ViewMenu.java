package View;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.text.StyledEditorKit.BoldAction;

import Controller.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

//LoginView
public class ViewMenu extends GridPane {
	// create labels
	private ToggleGroup group;
	private Label labelTitle;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private RadioButton rb5;
	private RadioButton rb6;
	private RadioButton rb7;
	private RadioButton rb8;
	private RadioButton rb9;

	// create submit button
	private Button buttonEnter;	

	// controller
	private Controller controller;
	

	public ViewMenu(Stage stage) {
		//action();
		stage.setTitle("Menu");
		
		stage.getIcons().add(new Image("Books.jpg"));
		init();
		addComponents();
		setHgap(0);
		setVgap(10);
		setPadding(new Insets(10));
		
	}
	

	private void init() {
		
		this.labelTitle = new Label("Application Menu");
		this.labelTitle.setMinHeight(0);
		this.labelTitle.setMinWidth(100);
		this.labelTitle.setFont(new Font("Arial",30));
		this.labelTitle.setStyle("-fx-font-weight: bold");
		
//		this.textFieldUser = new TextField();
//		this.textFieldUser.setOnMouseClicked(e -> updateStatus(" ", "black"));
//		this.textFieldPass = new PasswordField();
//		this.textFieldPass.setOnMouseClicked(e -> updateStatus(" ", "black"));
		this.group = new ToggleGroup();

		this.rb1 = new RadioButton("Show Questions In Repository");
		this.rb1.setToggleGroup(group);
		this.rb1.setSelected(true);

		this.rb2 = new RadioButton("Add Question");
		this.rb2.setToggleGroup(group);
		 
		this.rb3 = new RadioButton("Edit Question");
		this.rb3.setToggleGroup(group);
		
		this.rb4 = new RadioButton("Edit Answer");
		this.rb4.setToggleGroup(group);
		 
		this.rb5 = new RadioButton("Remove Question");
		this.rb5.setToggleGroup(group);
		
		this.rb6 = new RadioButton("Create Manu Exam");
		this.rb6.setToggleGroup(group);
		 
		this.rb7 = new RadioButton("Create Auto Exam");
		this.rb7.setToggleGroup(group);
		
		this.rb8 = new RadioButton("Create a new exam based on an existing one");
		this.rb8.setToggleGroup(group);
		
		this.rb9 = new RadioButton("Exit");
		this.rb9.setToggleGroup(group);
		
		this.buttonEnter=new Button("submit");
		this.buttonEnter.setOnAction(e -> {
			
				try {
					controller.openNewView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
	}

	private void addComponents() {
		setAlignment(Pos.CENTER);
		
		add(labelTitle, 0, 0, 1, 1);
		
		labelTitle.setTextAlignment(TextAlignment.CENTER);
		
		add(rb1,0,1);
		add(rb2,0,2);
		add(rb3,0,3);
		add(rb4,0,4);
		add(rb5,0,5);
		add(rb6,0,6);
		add(rb7,0,7);
		add(rb8,0,8);
		add(rb9,0,9);
		
		add(buttonEnter,0,10);
//		add(labelPass, 0, 2);
//		add(labelStatus, 0, 3, 2, 1);
//		add(buttonEnter, 2, 3);
//		add(textFieldUser, 1, 1);
//		add(textFieldPass, 1, 2);
	}

//	private void updateStatus(String statusText, String statusColor) {
//		if (!statusText.equals("")) {
//			labelStatus.setText(statusText);
//			labelStatus.setStyle("-fx-text-fill: " + statusColor + ";-fx-font-weight: bold");
//		}
//
//	}
	
	
	public RadioButton getRb8() {
		return rb8;
	}


	public RadioButton getRb9() {
		return rb9;
	}


	public ToggleGroup getTgr() {
		return group;
	}
	
	public ToggleGroup getGroup() {
		return group;
	}
	public Label getLabelTitle() {
		return labelTitle;
	}
	public RadioButton getRb1() {
		return rb1;
	}
	public RadioButton getRb2() {
		return rb2;
	}
	public RadioButton getRb3() {
		return rb3;
	}
	public RadioButton getRb4() {
		return rb4;
	}
	public RadioButton getRb5() {
		return rb5;
	}
	public RadioButton getRb6() {
		return rb6;
	}
	public RadioButton getRb7() {
		return rb7;
	}
	public Button getButtonEnter() {
		return buttonEnter;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller c) {
		this.controller = c;
	}
}
