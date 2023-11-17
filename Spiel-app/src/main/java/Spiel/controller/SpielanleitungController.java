package Spiel.controller;



import Spiel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class SpielanleitungController {

	private MainApp mainApp;

	@FXML
	private Text spielanleitung;
	
	@FXML
	private void initialize() {

		this.spielanleitung.setText(
		"Ziel des Spieles ist es den Endboss zu töten, ohne vorher selbst von Gegnern getötet zu werden. Dabei helfen dir Items, die aus Truhen und von getöteten Gegern erhalten werden können. Wenn du gewinnen willst, solltest du klug kämpfen und deine Items sinnvoll einsetzen!\n\n"		
		+ "Im Steuerungsblock rechts unten sind Buttons, mit denen du deine Bewegungsrichtung steuern kannst. Klicke den entsprechenden Button, um dich in eine bestimmte Himmelsrichtung zu bewegen.\n"
		+ "Rechts oben im Tab \"Map\" findest du eine Map, damit du weist, in welchem Raum du dich gerade befindest. Außerdem findest du im rechten oberen Block im Tab \"Inventar\" dein Inventar. Um ein Item aus deinem Inventar auszuwählen, musst du es anklicken.\n"
		+ "Links unten werden dir Informationen zu dem Raum angezeigt, in dem du dich gerade befindest. Darunter befindet sich die Anzeige deiner Lebenspunkte.\n"
		+ "Ganz unten links, befindet sich ein Block mit Buttons um bestimmte Aktionen auszuführen. Du kannst Hebel umlegen, Truhen öffnen bzw. leeren. Um zum Endboss zu kommen, musst du alle Hebel umgelegt haben! Des weiteren kannst du hier Items aufheben, die im Raum herumliegen. Außerdem kannst du im Inventar ausgewählte Items benutzen oder fallen lassen.\n"
		+ "Oben im Menü kannst du den Schwierigkeitsgrad des Spieles festelgen, indem du auf \"Schwierigkeitsgrad\" klickst."
			
		);
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}	
	
	@FXML
	private void handleZurueckButton() {

		mainApp.getPrimaryStage().setScene(mainApp.getScene());
	
	}
	
	
}