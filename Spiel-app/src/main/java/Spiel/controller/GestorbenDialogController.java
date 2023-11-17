package Spiel.controller;


import Spiel.MainApp;
import Spiel.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class GestorbenDialogController {

	private MainApp mainApp;
	private Player player;

	@FXML
	private Text infos;
	
	@FXML
	private Label geoeffneteTruhen;

	@FXML
	private Label umgelegteSchalter;
	
	@FXML
	private Label getoeteteGegner;
	
	@FXML
	private void initialize() {

	
		
	}
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}	
	
	public void setPlayer(Player player) {
		this.player=player;
		
	}	
	
	public void spielStatistikenAnzeigenVerloren() {
		
		infos.setText("Du wurdest von "+player.getGegner().getOpponentName()+" getötet." +"\n"+player.getGegner().getOpponentName()+" hatte noch "+player.getGegner().getOpponentHp()+" HP.");
		Integer umgelegteSchalter=player.getPressedLever();
		Integer getoeteteGegner=player.getKills();
		Integer geoeffneteTruhen=player.getOpenedChests();
		this.umgelegteSchalter.setText(umgelegteSchalter.toString());
		this.getoeteteGegner.setText(getoeteteGegner.toString());
		this.geoeffneteTruhen.setText(geoeffneteTruhen.toString());
		
	}
	
	@FXML
	private void handleNochmalSpielenButton() {

		this.mainApp.initRootLayout();
	
	}
	
	@FXML
	private void handleSchließenButton() {

	this.mainApp.getPrimaryStage().close();
	
	}
	
	
}