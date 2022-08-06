package View;

import java.util.ArrayList;
import java.util.Comparator;

import Model.AmericanQuestion;
import Model.OpenQuestion;
import Model.Question;
import Model.Tuple;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ViewQuestions{
	private Text title;
	ListView<AmericanQuestion> listAmerican;
	ListView<OpenQuestion> listOpen;
	private Stage stage;
//	public String[] str= {"hey","blue","hello"};
	ArrayList<AmericanQuestion> americans;
	ArrayList<OpenQuestion> opens;
	String questions;
	private Label labelTitle;
	ListView<String> QuestionsList;
	
	
	
	public ViewQuestions(Stage stage) {
		this.stage=stage;
		init();
	}
	public ViewQuestions(Stage stage,ArrayList<AmericanQuestion> american,ArrayList<OpenQuestion> open,String questions) {
		this.stage=stage;
		stage.getIcons().add(new Image("Books.jpg"));
		stage.setTitle("Questions Repository");
		americans=american;
		opens=open;
		this.questions=questions;
		
		QuestionsList=new ListView<String>();
		init();			
//		createTableAmerican();
		HBox hbox = new HBox(QuestionsList);
		createTableAmerican();
		QuestionsList.setPrefWidth(500);
		QuestionsList.setPrefHeight(300);
		Scene scene = new Scene(hbox,500, 600);
		
		stage.setScene(scene);
		
		
		stage.setAlwaysOnTop(true);
		stage.show();
		
		


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
	}


	private void createTableAmerican() {
//		QuestionsList.getItems().add(questions);
		for(int i=0;i<americans.size();i++) {
			QuestionsList.getItems().add(americans.get(i).toString());
		}
		for(int i=0;i<opens.size();i++) {
			QuestionsList.getItems().add(opens.get(i).toString());
		}
//		
	}

	public String getTitle() {
		return title.getText();
	}



	

//	public boolean updateHamburger(String name, Number price, DegreeOfDoneness dod, String ingredients) {
//		Hamburger h = menu.getSelectionModel().getSelectedItem();
//		if ( (!name.equals(h.name)) || (price.doubleValue() != h.price.doubleValue()) || (dod != h.getDegreeOfDoneness())
//				|| (!ingredients.equals(h.getIngredients())) ) {
//			menu.getSelectionModel().getSelectedItem().setName(name);
//			try {
//				menu.getSelectionModel().getSelectedItem().setPrice(price);
//			} catch (PriceException e) {}
//			menu.getSelectionModel().getSelectedItem().setIngredients(ingredients);
//			menu.getSelectionModel().getSelectedItem().setDegreeOfDoneness(dod);
//			menu.refresh();
//			return true;
//		}
//		menu.refresh();
//		return false;
//	}

//	public void createContact(Contact c) {
//		contacts.getItems().add(c);
//		
//	}

}
