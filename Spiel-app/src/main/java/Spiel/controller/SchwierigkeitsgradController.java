package Spiel.controller;

import Spiel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class SchwierigkeitsgradController {

	private MainApp mainApp;

	@FXML
	private ToggleGroup schwierigkeitsgradToggleGroup = new ToggleGroup();
	
	@FXML
	private RadioButton leichtRadioButton;

	@FXML
	private RadioButton normalRadioButton;
	
	@FXML
	private RadioButton schwerRadioButton;
	
	@FXML
	private RadioButton extremRadioButton;
		
	private String leicht="Leicht";
	private String normal="Normal";
	private String schwer="Schwer";
	private String extrem="Extrem";
	
	@FXML
	private void initialize() {

		leichtRadioButton.setUserData(this.leicht);
		normalRadioButton.setUserData(this.normal);
		schwerRadioButton.setUserData(this.schwer);
		extremRadioButton.setUserData(this.extrem);
		
		leichtRadioButton.setToggleGroup(this.schwierigkeitsgradToggleGroup);
		normalRadioButton.setToggleGroup(this.schwierigkeitsgradToggleGroup);
		schwerRadioButton.setToggleGroup(this.schwierigkeitsgradToggleGroup);
		extremRadioButton.setToggleGroup(this.schwierigkeitsgradToggleGroup);
	
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
			
	}	
	
	public void schwierigkeitsgradSetzen() {
		
		switch (mainApp.getSchwierigkeitsgrad()) {
		  case "Leicht", "":
			  this.leichtRadioButton.setSelected(true);
			  break;
		  case "Normal":
			  this.normalRadioButton.setSelected(true);
			  break;
		  case "Schwer": 
			  this.schwerRadioButton.setSelected(true);
			  break;
		  case "Extrem": 
			  this.extremRadioButton.setSelected(true);
			  break;	  
		  default:
			  MainApp.unbekanntenFehlerAnzeigen(mainApp.getSchwierigkeitsgrad());
			  break;
		}
		
	}
	
	@FXML
	private void handleZurueckButton() {

		mainApp.getPrimaryStage().setScene(mainApp.getScene());
	
	}
	
	
	@FXML
	private void handleSpielenButton() {

		mainApp.setSchwierigkeitsgrad(schwierigkeitsgradToggleGroup.getSelectedToggle().getUserData().toString());
		mainApp.initRootLayout();
		
	}
	
}