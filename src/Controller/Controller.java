package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import Model.Model;
import Model.ModelMenu;
import Model.Question;
import Model.Set;
import Model.Tuple;
import View.ViewAddQuestion;
import View.ViewAutoTest;
import View.ViewCopyTest;
import View.ViewEditAnswer;
import View.ViewEditQuestion;
import View.ViewManualTest;
import View.ViewMenu;
import View.ViewQuestions;
import View.ViewRemoveAnswer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Controller {

	private Model model;
	private ViewMenu viewMenu;
	private ViewQuestions viewQuestions;
	private ViewAddQuestion viewAddQuestions;
	private ViewEditQuestion viewEditQuestions;
	private ViewEditAnswer viewEditAnswer;
	private ViewRemoveAnswer viewRemoveAnswer;
	private ViewAutoTest viewAutoTest;
	private ViewManualTest viewManualTest;
	private ViewCopyTest viewCopyTest;
	
	public Controller(Model model,ViewMenu viewMenu,ViewQuestions viewQuestions, ViewAddQuestion viewAddQuestion,ViewEditQuestion viewEditQuestion,
			ViewEditAnswer viewEditAnswer,ViewRemoveAnswer viewRemoveAnswer,ViewAutoTest viewAutoTest, ViewManualTest viewManualTest, ViewCopyTest viewCopyTest) {
		
		this.model=model;
		this.viewMenu=viewMenu;
		this.viewQuestions=viewQuestions;
		this.viewAddQuestions=viewAddQuestion;
		this.viewEditQuestions=viewEditQuestion;
		this.viewEditAnswer=viewEditAnswer;
		this.viewRemoveAnswer=viewRemoveAnswer;
		this.viewAutoTest=viewAutoTest;
		this.viewManualTest=viewManualTest;
		this.viewCopyTest=viewCopyTest;
		
		
		viewMenu.setController(this);
		viewAddQuestion.setController(this);
		viewEditQuestion.setController(this);
		viewEditAnswer.setController(this);
		viewRemoveAnswer.setController(this);
		viewAutoTest.setController(this);
		viewManualTest.setController(this); 
		viewCopyTest.setController(this);
		
		}
	
	public void openNewView() throws IOException {
		RadioButton rb =(RadioButton)viewMenu.getTgr().getSelectedToggle();
		if(rb==viewMenu.getRb1()) {
			viewQuestions=new ViewQuestions(new Stage() ,model.getAmericanQuestions(),model.getOpenQuestions(),model.getQuestionsString());
		}
		else if(rb==viewMenu.getRb2()) {
			viewAddQuestions.show();
			
		}
		else if(rb==viewMenu.getRb3()) {
			viewEditQuestions.updateData(model.getQuestionsString());
			viewEditQuestions.show();
			viewEditQuestions.setController(this);
		}
		else if(rb==viewMenu.getRb4()) {
			viewEditAnswer.updateData(model.getQuestionsString());
			viewEditAnswer.show();
		}
		else if(rb==viewMenu.getRb5()) {
			viewRemoveAnswer.UpdateData(model.getQuestionsString());
			viewRemoveAnswer.show();
		}
		else if(rb==viewMenu.getRb6()) {
			viewManualTest.updateData(model.getQuestionsString());
			viewManualTest.show();
		}
		else if(rb==viewMenu.getRb7()) {
			viewAutoTest.show();
		}
		else if(rb==viewMenu.getRb8()) {
			viewCopyTest.setExamString(model.examToString());
			viewCopyTest.show();
			
		}
		else if(rb==viewMenu.getRb9()) {
			model.save();
			javafx.application.Platform.exit();
		}
		
	}

	public void AddOpenQuestion() throws InterruptedException {
		String text=viewAddQuestions.getOpenText().trim();
		String answer=viewAddQuestions.getOpenAnswer().trim();
		if(!text.equals("") &&!answer.equals("")) {
			Boolean check=model.addOpenQuestion(text, answer);
			if(check)
				viewAddQuestions.setLabelStatus("question added to repository");
			else
				viewAddQuestions.setLabelStatus("a problem occured, can't add the question");
		}
		else
			viewAddQuestions.setLabelStatus("enter data first");
		
		
		
		
	}

	public void editQuestion(int id, String newText) {
		boolean check=model.EditQuestion(id, newText);
		if(check)
			viewEditQuestions.setLabelStatus("status: question changed");
		else
			viewEditQuestions.setLabelStatus("status: question didn't change");
	}

	public void editAnswer(int questionId, int answerId, String newText) {
		RadioButton rb =(RadioButton)viewEditAnswer.getTgr().getSelectedToggle();
		Boolean check=model.editAnswer(questionId, answerId, newText, Boolean.parseBoolean(rb.getText()));
		if(check)
			viewEditAnswer.updateData(model.getQuestionsString());
		
	}

	public void removeAnswer(int questionID,int answerID) {
		boolean check=model.RemoveAnswer(questionID,answerID);
		if(check)
			viewRemoveAnswer.setLabelStatus("answer removed!");
		else
			viewRemoveAnswer.setLabelStatus("an error occured");
		viewRemoveAnswer.UpdateData(model.getQuestionsString());
		
	}

	public void CreateAutoTest(int numQuestions) throws IOException {
		String exam=model.createAutoTest(numQuestions);
		viewAutoTest.init2();
		viewAutoTest.updateData(exam);
		
	}

	public void addAmericanQuestion() {
		String text=viewAddQuestions.getAmericanText();
		Set<String,Boolean> answers=new Set<String,Boolean>();
		
		ToggleGroup[] tg=viewAddQuestions.getGroups();
		TextField[] fields=viewAddQuestions.getTextFields();
		for(int i=0;i<fields.length;i++) {
			if(!fields[i].getText().equals("")) {
				RadioButton rb=(RadioButton)tg[i].getSelectedToggle();
				answers.add(fields[i].getText(), Boolean.parseBoolean(rb.getText()));
			}
		}
		boolean check=model.addAmericanQuestion(text, answers);
		if(check)
			viewAddQuestions.setAmericanStatus("American question added to repository");
		else
			viewAddQuestions.setAmericanStatus("an error occured");
		
		
	}

	public void createManuTest(java.util.Set<Integer> set) throws IOException {
		String textString="";
		textString=model.createManuTest(set);
		if(!textString.equals("")) {
			viewManualTest.setStatusLabel("Test file created");
			viewManualTest.setExamQuestions(textString);
		}
		
	}

	public void copyTest(String id) throws ClassNotFoundException, IOException {
		model.copyTest(id);
	
		
	}

}