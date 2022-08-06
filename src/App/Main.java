package App;


import java.io.FileNotFoundException;
import java.io.IOException;

import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.ModelMenu;
import Model.Model;
import View.ViewAddQuestion;
import View.ViewAutoTest;
import View.ViewCopyTest;
import View.ViewEditAnswer;
import View.ViewEditQuestion;
import View.ViewManualTest;
import View.ViewMenu;
import View.ViewQuestions;
import View.ViewRemoveAnswer;
import javafx.scene.Scene;
public class Main extends Application {

	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		Model model = new Model();
		ViewMenu menuView = new ViewMenu(stage);
		ViewQuestions viewQuestions = new ViewQuestions(new Stage());
		ViewAddQuestion viewAddQuestion=new ViewAddQuestion(new Stage());
		ViewEditQuestion viewEditQuestion=new ViewEditQuestion(new Stage());
		ViewEditAnswer viewEditAnswer=new ViewEditAnswer(new Stage());
		ViewRemoveAnswer viewRemoveQuestion=new ViewRemoveAnswer(new Stage());
		ViewAutoTest viewAutoTest=new ViewAutoTest(new Stage());
		ViewManualTest viewManualTest=new ViewManualTest(new Stage());
		ViewCopyTest viewCopyTest=new ViewCopyTest(new Stage());
		Controller controller = new Controller(model,menuView,viewQuestions,viewAddQuestion,viewEditQuestion,viewEditAnswer
				,viewRemoveQuestion,viewAutoTest,viewManualTest,viewCopyTest);
		

		Scene scene = new Scene(menuView, 400, 400);
		stage.setScene(scene);
		stage.show();
		

	}

	public static void main(String[] args) {
		launch(args);
	}

}
