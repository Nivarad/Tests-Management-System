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



public class ViewCopyTest{
	private Controller controller;
	private Stage stage;
	
	//Label
	private Label labelTitle;
	private Label examString;
	
	public void setExamString(String string) {
		this.examString.setText(string);
	}
	//TextFields
	private TextField examIdTF;
	
	//Buttons
	private Button enter;
	
	
	
	
	
	public ViewCopyTest(Stage stage) throws IOException {
		this.stage=stage;
		stage.setTitle("Copy an existing test");
		stage.getIcons().add(new Image("Books.jpg"));
		init1();
	}
	public void init1() throws IOException{
		this.labelTitle = new Label("Exams");
		this.labelTitle.setMinHeight(0);
		this.labelTitle.setMinWidth(100);
		this.labelTitle.setFont(new Font("Arial",30));
		this.labelTitle.setStyle("-fx-font-weight: bold");

		this.examString=new Label();
		this.examIdTF=new TextField();
		
		this.enter=new Button("Copy Test");
		
		this.enter.setOnAction(e ->{
			if(!examIdTF.getText().equals(""))
				try {
					try {
						controller.copyTest(examIdTF.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
		GridPane root = new GridPane();
		
		root.add(labelTitle, 1, 1);
		root.add(examString, 1, 2);
		root.add(examIdTF, 1, 3);
		root.add(enter, 2, 3);
		
		Scene scene = new Scene(root,400, 400);
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
	}
	public void init2(){
		Label title=new Label("Exam Copy");
		this.examString=new Label();
		
		this.examIdTF=new TextField();
		GridPane root = new GridPane();
		
		
		
		root.add(title, 1, 1);
		root.add(examString, 1, 2);
		
		Scene scene = new Scene(root,800, 800);
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
	}
	public void setController(Controller c) {
		this.controller=c;
	}
	public void updateData(String exam) {
		this.examString.setText(exam);
		stage.show();
		
	}
	public void show() {
		stage.show();
	}

}
