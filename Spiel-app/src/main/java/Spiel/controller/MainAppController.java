package Spiel.controller;


import java.util.List;
import java.util.Optional;

import Spiel.MainApp;
import Spiel.model.Chest;
import Spiel.model.Hebel;
import Spiel.model.Player;
import Spiel.model.Room;
import Spiel.model.items.Item;
import Spiel.model.items.ItemBandage;
import Spiel.model.items.ItemHealthPotion;
import Spiel.model.items.ItemHolzschwert;
import Spiel.model.items.ItemIronSword;
import Spiel.model.items.ItemMedkit;
import Spiel.model.items.ItemMinischildtrank;
import Spiel.model.items.ItemSchildtrank;
import Spiel.model.items.ItemSchneeball;
import Spiel.model.items.ItemStock;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MainAppController {

	private MainApp mainApp;
	private Player player;
	

	@FXML
	private Text raumbeschreibung;

	@FXML
	private Text infos;
	
	@FXML
	private Label hpanzeige;
	
	@FXML
	private ProgressBar hpAnzeige;
	
	@FXML
	private Label schildanzeige;
	
	@FXML
	private ProgressBar schildAnzeige;
	
	@FXML
	private ProgressBar gegnerHpAnzeige;
	
	@FXML
	private ComboBox<String> itemsimraum;
	
	@FXML
	ListView<Item> listView;
	
	@FXML
	private Text inventarLeer;
	
	@FXML
	private ImageView mapImage;
	
	@FXML
	private ImageView roomImage;
	
	@FXML
	private ImageView gegnerGif;
	
	@FXML
	private ImageView truheImage;
	
	@FXML
	private ImageView hebelImage;
	
	@FXML
	private HBox buttonbereich;
	
	@FXML
	private MenuBar menubar;
	
	@FXML
	private AnchorPane steuerungsbereich;
	
	@FXML
	private Tab inventar;
	
	@FXML
	private void initialize() {

	}
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}	
	
	public void setPlayer(Player player) {
		this.player=player;
		
	}	
	
    /**
     * setzt alle Informationen die Angezeigt werden sollen (Raumbeschreibung, Infotext, Spieler-Lebenspunkte und Lebensanzeige, Bild der Mapp für die Räume, Bild des Raums
     */
	public void startwerteSetzen() {
		
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		this.infos.setText("Willkommen in der Welt von Tyranak-Adventure!"+"\n"+"Klicke oben links auf \"Hilfe\" um zur Spielanleitung zu kommen.");
		Integer hp=player.getHp();
		this.hpanzeige.setText(hp.toString());
		lebensAnzeigeAktualisieren();
		itemlisteImRaumAktualisieren();
		inventarAktualisieren();
		gegnerHpAnzeigeAktualisieren();
		this.mapImage.setImage(this.passendesMapBildfinden(player.getCurrentRoom().getRoomId()));
		this.roomImage.setImage(this.passendesRoomBildfinden(player.getCurrentRoom().getRoomId()));
		
	}
	
	
	@FXML
	private void handleNordenButton() {

		walkPlayer("norden");
	
	}
	
	@FXML
	private void handleOstenButton() {

		walkPlayer("osten");
	
	}
	
	@FXML
	private void handleSuedenButton() {

		walkPlayer("süden");
	
	}
	
	@FXML
	private void handleWestenButton() {

		walkPlayer("westen");
	
	}
	
	@FXML
	private void handleNordostenButton() {

		walkPlayer("nordosten");
	
	}
	
	@FXML
	private void handleSuedostenButton() {

		walkPlayer("südosten");
	
	}
	
	@FXML
	private void handleSuedwestenButton() {

		walkPlayer("südwesten");
	
	}
	
	@FXML
	private void handleNordwestenButton() {

		walkPlayer("nordwesten");
	
	}
	
	@FXML
	private void handleZurueckButton() {

	if(player.getRoomhistorySize()==1) {
		
		this.infos.setText("Du befindest dich im Startraum und kannst nicht weiter zurück gehen!");	
		
	}
	else if(mainApp.endgegnerPruefen(player.getCurrentRoom())) {
		this.infos.setText("Du kannst hier nicht mehr zurück!");
	}
	else {	
		player.goBack(1);
		raumbeschreibungAktualisieren();	
		itemlisteImRaumAktualisieren();
		gegnerHpAnzeigeAktualisieren();
		this.mapImage.setImage(this.passendesMapBildfinden(player.getCurrentRoom().getRoomId()));
		this.roomImage.setImage(this.passendesRoomBildfinden(player.getCurrentRoom().getRoomId()));
		this.gegnerGif.setImage(this.passendesGegnerGiffinden(player.getCurrentRoom().getRoomId()));
		this.truheImage.setImage(this.passendesTruhenbildfinden(player.getCurrentRoom().getRoomId()));
		this.hebelImage.setImage(this.passendesHebelbildfinden(player.getCurrentRoom().getRoomId()));
		
	}
		
	}
	
	
	/**
	*  @param Richtung, in die sich bewegt werden soll
	*Methode für die Bewegung des Spielers durch die Räume 
	*/
	private void walkPlayer(String direction) {
		
		Room room=player.getCurrentRoom();
		
		if( (!mainApp.hallePruefen(room)) || (player.getPressedLever()==3) || (mainApp.hallePruefen(room) && !direction.equals("norden"))) {	
			
		player.walk(direction);
		raumbeschreibungAktualisieren();
		itemlisteImRaumAktualisieren();
		gegnerHpAnzeigeAktualisieren();
		this.mapImage.setImage(this.passendesMapBildfinden(player.getCurrentRoom().getRoomId()));
		this.roomImage.setImage(this.passendesRoomBildfinden(player.getCurrentRoom().getRoomId()));
		this.gegnerGif.setImage(this.passendesGegnerGiffinden(player.getCurrentRoom().getRoomId()));
		this.truheImage.setImage(this.passendesTruhenbildfinden(player.getCurrentRoom().getRoomId()));
		this.hebelImage.setImage(this.passendesHebelbildfinden(player.getCurrentRoom().getRoomId()));
		
		if(room.equals(player.getCurrentRoom())) {
			this.infos.setText("Da ist keine Tür!");	
		}
		
		}
		else{
			this.infos.setText("Du musst drei Schalter drücken um hier rein zu kommen!");
		}	
		
	}
	
	@FXML
	private void handleHebelUmlegenButton() {

	    Room room = player.getCurrentRoom();

        if (room.getHebel().isPresent() && !room.leverAvailable()) {
            this.infos.setText("Der Hebel in diesem Raum wurde bereits umgelegt...");
    
        } 
        else if (room.getHebel().isPresent() && room.leverAvailable()) {
            this.infos.setText("Du zögerst leicht.... ziehst aber dennoch an dem verrosteten Hebel... in der Ferne hörst du es krachen...");
            room.getHebel().get().einschalten();
            player.pressLever();
            this.hebelImage.setImage(this.passendesHebelbildfinden(player.getCurrentRoom().getRoomId()));
            
        } 
        else {
        	this.infos.setText("In dem Raum gibt es keinen Hebel...");
        }   

	}
	
	@FXML
	private void handleTruheoefffnenButton() {
		
		Room room=player.getCurrentRoom();  
		
		if(room.getChest().isPresent()) {
      
        if(!room.getChest().get().isOpen()) {
        	
        	this.setChestItems(room.getChest().get());
        	
        	room.getChest().get().setOpen(true);	
        	this.infos.setText("Die Truhe in diesem Raum wurde geöffnet!"+"\n"+room.getChest().get().getItemsInChest());	
        	player.openchest();
        	this.truheImage.setImage(this.passendesTruhenbildfinden(player.getCurrentRoom().getRoomId()));
        	
        }
        else {
        	  this.infos.setText("Die Truhe in diesem Raum wurde bereits geöffnet!"+"\n"+room.getChest().get().getItemsInChest());	
        }
				
		}  
	    else {
	        this.infos.setText("In diesem Raum ist keine Truhe vorhanden!");
	    }
	
	}
	
	
	@FXML
	private void handleTruheLeerenButton() {
		
		Room room=player.getCurrentRoom();  
		
		if(room.getChest().isPresent()) {
      
		Chest chest=room.getChest().get();		
        if(chest.removeItemsFromChest().isPresent() && !chest.isChestEmpty()) {
        	
        	Optional<List<Item>> truheninhalt=chest.removeItemsFromChest();
        	
        	if(player.getItemsFromChest(truheninhalt.get())) {

				this.infos.setText(
						"Du hast dir Items aus der Truhe genommen!" + "\n" + room.getChest().get().getItemsInChest());
				this.truheImage.setImage(this.passendesTruhenbildfinden(player.getCurrentRoom().getRoomId()));

        	}
        	else {
        		this.infos.setText("Dein Inventar ist voll!"+"\n"+room.getChest().get().getItemsInChest()); 	
        	}

        	inventarAktualisieren();
        	
        }
        else if (chest.removeItemsFromChest().isPresent()  && chest.isChestEmpty()) {
        	this.infos.setText("Die Truhe ist leer!"); 
        }
        else {
	        this.infos.setText("Du musst die Truhe zunächst öffnen!");
	    }
			
        
		}  
	    else {
	        this.infos.setText("In diesem Raum ist keine Truhe vorhanden!");
	    }
			
	}
	
	
	@FXML
	private void handleAufhebenButton() {
		
		Room room=player.getCurrentRoom();  

		
		if(!room.getItemNamesinRoom().isEmpty() && itemsimraum.getValue()!=null) {
			
		boolean itemhinzugefügt=player.pickUpItem(itemsimraum.getSelectionModel().getSelectedIndex());
		if(itemhinzugefügt) {
			this.infos.setText("Du hast das Item "+itemsimraum.getValue().split(" ")[1]+" aufgenommen!");
		}
		else {
			this.infos.setText("Dein Inventar ist voll!");
		}
			
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		inventarAktualisieren();
		itemlisteImRaumAktualisieren();


		}
		else if(!room.getItemNamesinRoom().isEmpty() && itemsimraum.getValue()==null) {
			this.infos.setText("Bitte wähle das Item aus, welches du aufheben möchtest!");
		}
		else {
			this.infos.setText("In diesem Raum liegen keine Items herum!");		
		}
				
	}
	
	@FXML
	private void handleAblegenButton() {
	
		if(!player.isInventoryempty() && !listView.getSelectionModel().isEmpty()) {
		
		int selectedIndex=listView.getSelectionModel().getSelectedIndex();	
		int listViewSize=listView.getItems().size();
		String itemname=listView.getSelectionModel().getSelectedItem().getName();
		
		player.dropOneItem(listView.getSelectionModel().getSelectedIndex());
		this.inventarAktualisieren();
	
		if(listViewSize==listView.getItems().size()) {
			listView.getSelectionModel().select(selectedIndex);
		}
		
		this.infos.setText("Du hast das Item "+itemname+" abgelegt!");	
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		itemlisteImRaumAktualisieren();
		
		}
		else if(!player.isInventoryempty() && listView.getSelectionModel().isEmpty()) {
			this.infos.setText("Bitte wähle das Item aus, welches du ablegen möchtest!");
		}
		else {
			this.infos.setText("Dein Inventar ist leer!");		
		}
				
	}
	
	@FXML
	private void handleSlotablegen() {
		
		if(!player.isInventoryempty() && !listView.getSelectionModel().isEmpty()) {
			
			player.dropItem(listView.getSelectionModel().getSelectedIndex());
			this.infos.setText("Du hast das Item "+listView.getSelectionModel().getSelectedItem().getName()+" abgelegt!");	
			this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
			itemlisteImRaumAktualisieren();
			inventarAktualisieren();
			
			}
		
	}
	
	
	@FXML
	private void handleBenutzenButton(){			
				
		player.setUseItemText(null);
					
		if(!player.isInventoryempty() && !listView.getSelectionModel().isEmpty()) {
						
			String itemname=listView.getSelectionModel().getSelectedItem().getName();
			itempruefen(itemname);
			
		}
		else if(!player.isInventoryempty() && listView.getSelectionModel().isEmpty()) {
			this.infos.setText("Bitte wähle das Item aus, welches du benutzen möchtest!");
		}
		else {
			this.infos.setText("Dein Inventar ist leer!");		
		}
						
	}
	
    /**
     * Diese Methode wird nach einer kurzen Verzögerung ausgeführt, nachdem der Spieler ein Item benutzt hat und aktualisiert Spielerinventar, Lebensanzeige und Infotext
     * 
     */
	private void afteranimation() {
	
		int selectedIndex=listView.getSelectionModel().getSelectedIndex();
		int listViewSize=listView.getItems().size();
		
		player.useItem(listView.getSelectionModel().getSelectedIndex());
		this.infos.setText(player.getUseItemText());		
		inventarAktualisieren();
		lebensAnzeigeAktualisieren();
			
			
		if(listViewSize==listView.getItems().size()) {
			listView.getSelectionModel().select(selectedIndex);
		}
			
		this.enableButtons();
		
	}
	
    /**
     * @param Itemname
     * Diese Methode wird ausgeführt, wenn der Spieler ein im Iventar ausgewähltes Item benutzen will
     * 
     */
	private void itempruefen(String itemname) {
	
	    switch (itemname) {
	    
	       case "Verband":
	        	this.infos.setText("");	
	        	  if(player.getHp() >= player.getMaxHp()-25){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(1000, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	         
	        case "Heiltrank":
	        	this.infos.setText("");	
	        	  if(player.getHp() == player.getMaxHp()){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(2000, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	      
	        case "Medkit":
	        	this.infos.setText("");	
	        	  if(player.getHp() == player.getMaxHp()){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(4000, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	         
	        case "Minischildtrank":
	        	this.infos.setText("");	
	        	  if(player.getSchild() >= player.getMaxSchild()-50){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(1500, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	         
	        case "Schildtrank":
	        	this.infos.setText("");	
	        	  if(player.getSchild() == player.getMaxSchild()){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(3000, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	         
	        case "Schlürfsaft":
	        	this.infos.setText("");	
	        	  if(player.getHp() == player.getMaxHp() && player.getSchild() == player.getMaxSchild()){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.disableButtons();
					
	            		  delay(3500, () ->  afteranimation()
	            				  );
	            			
	              }
	         break;	
	         
	        case "Saufkrug":
	        	this.infos.setText("");	
				if (player.getHp() == player.getMaxHp() && player.getSchild() == player.getMaxSchild()) {

					afteranimation();

				} else {
	                
	            		this.disableButtons();
					
	            		  delay(7000, () ->  afteranimation()
	            				  );
	            			
								}
	         break;	
	         
	        case "Schneeball":
	        	this.infos.setText("");	

	            		this.disableButtons();
					
	            		  delay(2000, () ->  afteranimation()
	            				  );

	         break;	
	         
	        case "Stock", "Holzschwert", "Eisenschwert", "Diamantschwert", "Zauberschwert", "Sporteierkuchen":
	        	
	            if(player.getOptionalGegner().isPresent()) {
	            		
	            	this.disableButtons();
					
					int belegteItemSlotsVorher = player.getAnzahlItems();
					int selectedIndex = listView.getSelectionModel().getSelectedIndex();
					player.useItem(listView.getSelectionModel().getSelectedIndex());
					int belegteItemSlotsNachher = player.getAnzahlItems();

	      		  if(player.getOptionalGegner().isPresent()) {
	  			
	      			  delay(2000, () -> {
	      				
	      				  this.infos.setText(player.getUseItemText());
							gegnerHpAnzeigeAktualisieren();
							inventarAktualisieren();
							if (belegteItemSlotsVorher == belegteItemSlotsNachher) {
								listView.getSelectionModel().select(selectedIndex);
							}

							if (player.getGegner().getAussetzen() > 0) {

								delay(1000, () -> {

									this.infos.setText(
											player.getGegner().getOpponentName() + " kann dich nicht angreifen!");

									player.getGegner().decrementAussetzen(1);

									this.enableButtons();

								});

							} else {

								delay(2000, () -> {
	      					  
	      					  int schildvorher=player.getSchild();
	      					  int hpvorher=player.getHp();
	      					  
	      					  player.getDamaged(player.getGegner().calculateDamagefromAttack());
	      					   spielverlorenPruefen();
	      					   
	      					   if(player.getSchild()!= schildvorher && player.getSchild() ==0) {
	      						 this.infos.setText(player.getGegner().getOpponentName()+" hat dich angegriffen und dein Schild ist gebrochen!"+"\n"+"Du hast noch "+player.getHp()+" HP.");
	      					   }
	      					   else if(player.getHp()== hpvorher && player.getSchild() != schildvorher && player.getSchild()>0) {
		      						 this.infos.setText(player.getGegner().getOpponentName()+" hat dich angegriffen!"+"\n"+"Du hast noch "+player.getSchild()+" Schild.");
		      					   }
	      					   else {
	      						 this.infos.setText(player.getGegner().getOpponentName()+" hat dich angegriffen!"+"\n"+"Du hast noch "+player.getHp()+" HP.");    
	      					   }
	      					  
	      					  lebensAnzeigeAktualisieren();
	      					  this.enableButtons();

								});

							}

	      			  } );
	      			
	      		  }
	      		  else {
	  			
	      			  delay(2000, () -> {
	      				this.enableButtons();

							gegnerHpAnzeigeAktualisieren();
							inventarAktualisieren();
							if (belegteItemSlotsVorher == belegteItemSlotsNachher) {
								listView.getSelectionModel().select(selectedIndex);
							}

	      				this.infos.setText(player.getUseItemText());  
	      				player.kill();
	      				spielgewonnenPruefen();
	      				this.itemlisteImRaumAktualisieren();
	      				this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
	      				this.gegnerGif.setImage(this.passendesGegnerGiffinden(player.getCurrentRoom().getRoomId()));
	      				
	      			  } );  
	      			
	      		  	}

	            }
	            else {
	            
	            	this.disableButtons();
					
          		  delay(1500, () -> afteranimation()
          				  );	

						}
	        break;
	        
	        default:
	            System.out.println("Keinen Hunger?");

	            break;
	    }

	}
	
	
    private void spielverlorenPruefen() {
		
	if(!player.isAlive()) {
		
	this.mainApp.initGestorbenDialog();
		
		
	}
    	
    	
	}

    private void spielgewonnenPruefen() {
		
	if(player.isAlive() && mainApp.endgegnerPruefen(player.getCurrentRoom()) && player.getOptionalGegner().isEmpty()) {
		
	this.mainApp.initGewonnenDialog();
		
	}
    	
    	
	}
    

    /**
     * @param millis
     * Zeit in Millisekunden
     * @param continuation
     * Aktionen, die nach der Verzögerung ausgeführt werden sollen
     * Mit dieser Methode kann eine Verzögerung um eine bestimmte Zeit festgelegt werden
     * 
     */
	public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
      }
	
	
	private void raumbeschreibungAktualisieren() {
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		this.infos.setText("");	
		
	}
	
	private void gegnerHpAnzeigeAktualisieren() {

		if (this.player.getOptionalGegner().isPresent() && !player.getGegner().isFullLive()) {

			this.gegnerHpAnzeige.progressProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					 double progress = newValue == null ? 0 : newValue.doubleValue();
				        if (progress < 0.2) {
					gegnerHpAnzeige.setId("red-bar");
				        } else if (progress < 0.4) {
					gegnerHpAnzeige.setId("orange-bar");
				        } else if (progress < 0.6) {
						gegnerHpAnzeige.setId("yellow-bar");
				        }
				        else if (progress < 0.8) {
					gegnerHpAnzeige.setId("lightgreen-bar");
				        }
				        else {   	
					gegnerHpAnzeige.setId("darkgreen-bar");
				        }
				      }

			});
			
			this.gegnerHpAnzeige.setVisible(true);

			double maxHp = player.getGegner().getMaxHp();
			double hp = player.getGegner().getOpponentHp();

			double progress = hp / maxHp;
			System.out.println("Progress :" + progress);

			this.gegnerHpAnzeige.setProgress(progress);

		} else {
			this.gegnerHpAnzeige.setVisible(false);
		}

	}

	private void lebensAnzeigeAktualisieren() {

		this.hpAnzeige.progressProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					 double progress = newValue == null ? 0 : newValue.doubleValue();
				        if (progress < 0.2) {
					hpAnzeige.setId("red-bar");
				        } else if (progress < 0.4) {
					hpAnzeige.setId("orange-bar");
				        } else if (progress < 0.6) {
					hpAnzeige.setId("yellow-bar");
				        }
				        else if (progress < 0.8) {
					hpAnzeige.setId("lightgreen-bar");
				        }
				        else {   	
					hpAnzeige.setId("darkgreen-bar");
				        }
				      }

				    }); 
		
		
		Integer hp=player.getHp();
		this.hpanzeige.setText(hp.toString());
		
		Integer schild=player.getSchild();
		this.schildanzeige.setText(schild.toString());
		
		Double doublehp=(double) player.getHp();
		this.hpAnzeige.setProgress(doublehp / 100);
	
		Double doubleschild= (double) player.getSchild();
		this.schildAnzeige.setProgress(doubleschild/100);
		
	}
	
	private void disableButtons() {
		
		buttonbereich.setDisable(true);
		menubar.setDisable(true);
		steuerungsbereich.setDisable(true);
		inventar.setDisable(true);
		
	}
	
	private void enableButtons() {
		
		buttonbereich.setDisable(false);
		menubar.setDisable(false);
		steuerungsbereich.setDisable(false);
		inventar.setDisable(false);
		
	}
	
	private void itemlisteImRaumAktualisieren() {
		
		this.itemsimraum.getItems().clear();
		Room newroom=player.getCurrentRoom();
		
		List<Item> items= newroom.getItemsintheRoom();
		
		ObservableList<String> itemnameAndQuantity = FXCollections.observableArrayList();
		for(Item item : items) {
			
			
			if (item.getItemzustand() != null) {
				itemnameAndQuantity
						.add(item.getAnzahl() + "x " + item.getName() + "\n     Zustand: " + item.getItemzustand());
			} else {

				itemnameAndQuantity.add(item.getAnzahl() + "x " + item.getName());
			}

		}
	
		this.itemsimraum.setItems(itemnameAndQuantity);
	
	}
	
	
	private void inventarAktualisieren() {
	
		this.listView.getItems().clear();
		List<Item> items = player.getItems();

	this.inventarLeer.setVisible(false);
	if (items.isEmpty()) {
		this.inventarLeer.setVisible(true);
	}
	
	ObservableList<Item> elements = FXCollections.observableArrayList();
	elements.addAll(items);

	this.listView.setItems(elements);

//		System.out.println("-------------------------------------------------------");
//		for (Item i : this.listView.getItems()) {
//
//			System.out.println("Inventar: Item: " + i.getName() + " Zustand: " + i.getItemzustand());
//
//		}
//		System.out.println("-------------------------------------------------------");
		
		this.listView.setCellFactory(param -> new ListCell<Item>() {
			/*view the image class to display the image*/

			private AnchorPane anchorPane = new AnchorPane();
			private ImageView displayImage = new ImageView();

			@Override
			public void updateItem(Item item,boolean empty) {
			super.updateItem(item, empty);
			anchorPane.getChildren().clear();

			if (empty) {
			setText(null);
			setGraphic(null);
			setStyle(null);
			
			} else {
		
					HBox h = new HBox();
					h.setAlignment(Pos.CENTER);

					displayImage.setImage(passendesLogofinden(item.getName()));
					displayImage.setFitWidth(50);
					displayImage.setFitHeight(50);

					anchorPane.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

					AnchorPane.setTopAnchor(displayImage, 0.0);
					AnchorPane.setLeftAnchor(displayImage, 0.0);
					AnchorPane.setRightAnchor(displayImage, 0.0);
					AnchorPane.setBottomAnchor(displayImage, 0.0);

					if (!anchorPane.getChildren().contains(displayImage)) {
						anchorPane.getChildren().add(displayImage);
					}

					if (item.getItemzustand() != null && !item.isItemKomplettNeu()) {

						// System.out.println(item.getName() + " Itemzustand: " +
						// item.getItemzustand());

						ProgressBar itemzustand = new ProgressBar();
						itemzustand.setOpacity(0.7);
						itemzustand.setMaxWidth(48);
						itemzustand.setMaxHeight(10);

						itemzustand.progressProperty().addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,
									Number newValue) {
								double progress = newValue == null ? 0 : newValue.doubleValue();
								if (progress < 0.2) {
									itemzustand.setStyle("-fx-box-border: goldenrod; -fx-accent: red;");
								} else if (progress < 0.4) {
									itemzustand.setStyle("-fx-box-border: goldenrod; -fx-accent: orange;");
								} else if (progress < 0.6) {
									itemzustand.setStyle("-fx-box-border: goldenrod; -fx-accent: yellow;");
								} else if (progress < 0.8) {
									itemzustand.setStyle("-fx-box-border: goldenrod; -fx-accent: #48ff00;");
								} else {
									itemzustand.setStyle("-fx-box-border: goldenrod; -fx-accent: #00d123;");
								}
							}

						});

						itemzustand.setProgress(Double.valueOf(item.getItemzustand()) / 100);

						AnchorPane.setTopAnchor(itemzustand, 39.0);
						AnchorPane.setLeftAnchor(itemzustand, 1.0);
						AnchorPane.setRightAnchor(itemzustand, 1.0);
						AnchorPane.setBottomAnchor(itemzustand, 1.0);

						if (!anchorPane.getChildren().contains(itemzustand)) {
							anchorPane.getChildren().add(itemzustand);
						}

					}

			if(item.isStackbar()) {
				Label l=new Label();
				l.setText(item.getAnzahl() + "x");
				l.setPadding(new Insets(0,8,0,0));	
				h.getChildren().add(l);
			}
		
//					System.out.println("---------------------------------------------------");
//					System.out.println("anchorpane nodes: " + anchorPane.getChildren().size());
//
//					for (Node n : anchorPane.getChildren()) {
//						System.out.println("anchorPane node id: " + n.toString());
//
//					}
//
//					System.out.println("---------------------------------------------------");

					h.getChildren().add(anchorPane);

//					System.out.println("---------------------------------------------------");
//					System.out.println("h nodes: " + h.getChildren().size());
//
//					for (Node n : h.getChildren()) {
//						System.out.println("h node id: " + n.toString());
//
//					}
//				
//					System.out.println("---------------------------------------------------");

			setText(item.getName());
			setGraphic(h);
			setStyle("-fx-font-weight: bold;"+"-fx-font-size: 15px;");		

			}
			}
			});
		
	}
	
	private Image passendesLogofinden(String itemname) {
		
		return mainApp.getLogos().get(itemname.trim().toLowerCase());
		
	}
	
	private Image passendesMapBildfinden(int roomId) {
		
		return mainApp.getMapImages().get(roomId);
		
	}
	
	private Image passendesRoomBildfinden(int roomId) {
		
		return mainApp.getRoomImages().get(roomId);
		
	}

	private Image passendesGegnerGiffinden(int roomId) {
		
		if(player.getOptionalGegner().isPresent()) {
			
			return mainApp.getGegnerGifs().get(roomId);
		}
		else {
			return null;	
			
		}
			
	}
	
	private Image passendesTruhenbildfinden(int roomId) {
		
		if(player.getCurrentRoom().getChest().isPresent()) {
			
			Chest chest=player.getCurrentRoom().getChest().get();

			if (chest.isOpen() && chest.isChestEmpty()) {

				return new Image("roomImages/Truhe_leer.png");

			} else if (chest.isOpen() && !chest.isChestEmpty()) {

				return new Image("roomImages/Truhe_auf.png");
				
			}
			else {
				
				return new Image("roomImages/Truhe_zu.png");

			}
			
		}
		else {
			return null;	
			
		}
			
	}
	
	private Image passendesHebelbildfinden(int roomId) {
		
		if(player.getCurrentRoom().getHebel().isPresent()) {
			
			Hebel hebel=player.getCurrentRoom().getHebel().get();
			if(hebel.getZustand()) {
				
				return new Image("roomImages/Schalter_ein.png");
			}
			else {
				
				return new Image("roomImages/Schalter_aus.png");
			}
			
		
		}
		else {
			return null;	
			
		}
			
	}
	
	
	@FXML
	private void handleItemBeschreibung() {
	
	if(this.listView.getSelectionModel().getSelectedItem()!=null) {
		
		String itemDescription=player.getItemDescription(this.listView.getSelectionModel().getSelectedIndex());
	     this.infos.setText(itemDescription);
		
	}
			
	}
	
	public String getCurrentRoomDescriptionFromPlayer() {
		
	return player.getCurrentRoom().getLongDescription();
		
	}
	
	public String getItemsfromCurrentRoom() {
		
	return player.getCurrentRoom().getItemsinRoom();
		
	}
	
	
	@FXML
	private void handleSpielanleitung() {
	
		mainApp.initSpielanleitung();
			
	}
	
	
	@FXML
	private void handleSchwierigkeitsgradWählen() {
	
		mainApp.initSchwierigkeitsgradWählen();
			
	}
	
	
	private void setChestItems(Chest chest) {
		
		int max = 100;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
		int anzahlitems=0;
		
		if(rand>=1 && rand <25) {
        	
			anzahlitems=0;
        }
        else if(rand>=25 && rand <65) {
        	
        	anzahlitems=1;
        }
        else if(rand>=65 && rand <90) {
        	
        	anzahlitems=2;
        }
        else if(rand>=90 && rand <=100) {
        	
        	anzahlitems=3;
        } 
		
		
	     for(int i=0;i<anzahlitems;i++){
	           
	    	 chest.setItem(getItem());
	     }
			
		}	
	
	
		private Item getItem() {
		
		int max = 1000;
        int min = 1;
        int range = max - min + 1;
		
        int rand = (int)(Math.random() * range) + min;
        
        if(rand>=1 && rand <400) {
        	
        	return this.getwhiteItem();
        }
        else if(rand>=400 && rand <800) {
        	
        	return this.getlightblueItem();
        }
        else if(rand>=800 && rand <=1000) {
        	
        	return this.getblueItem();
        }

		return null;
        
	}	
	
	private Item getwhiteItem() {
		
		int max = 2;
        int min = 1;
        int range = max - min + 1;
		
        int rand = (int)(Math.random() * range) + min;
        
        switch (rand) {
        
	       case 1: return new ItemStock(1); 
	       case 2: return new ItemBandage(5); 
	       
	      
		default:
	            System.out.println("Keinen Hunger?");
	            break;
	    }
		return null;

	}
	
	private Item getlightblueItem() {
		
		int max = 4;
        int min = 1;
        int range = max - min + 1;
		
        int rand = (int)(Math.random() * range) + min;
        
        switch (rand) {
        
	       case 1: return new ItemHolzschwert(1); 
	       case 2: return new ItemHealthPotion(1); 
	       case 3: return new ItemMedkit(1); 
	       case 4: return new ItemMinischildtrank(3);
	       
	       
		default:
	            System.out.println("Keinen Hunger?");
	            break;
	    }
		return null;

	}
	
	private Item getblueItem() {
		
		int max = 3;
        int min = 1;
        int range = max - min + 1;
		
        int rand = (int)(Math.random() * range) + min;
        
        switch (rand) {
        
	       case 1: return new ItemIronSword(1); 
	       case 2: return new ItemSchildtrank(1); 
			case 3:
				return new ItemSchneeball(1);

		default:
	            System.out.println("Keinen Hunger?");
	            break;
	    }
		return null;

	}

}
