package Spiel.model;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import Spiel.model.items.Item;
import Spiel.model.items.ItemKategorien;

/**
 * Diese Klasse steht für einen Spieler im Spiel.
 * Ein Spieler befindet sich zu jedem Zeitpunkt des Spiels in einem bestimmten Raum.
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */
public class Player
{
	 /**
     * Raum in dem sich der Spieler befindet
     */
    private Room currentRoom;
    
    /**
     * Liste der Räume, in denen der Spieler schon war
     */
    private Stack<Room> roomhistory;
    /**
     * aktuelle Leben des Spielers
     */
    private int hp;
    
    private int schild;
    /**
     * maximale Leben des Spielers
     */
    private int maxHp;
    
    private int maxSchild;
    /**
     * Anzahl der aktivierten Schalter
     */
    private int pressedLever;
    
    private int openedChests;
    
    private int kills;
    
    /**
     * Inventar des Spielers
     */
    private PlayerInventory inventar;

private String useItemText;
    
    /**
     * Konstruktor für die Player-Klasse.
     */
    public Player()
    {
        currentRoom = null;
        roomhistory = new Stack<>();
        hp = 100;
        schild=0;
        maxSchild=100;
        maxHp=100;
        pressedLever=0;
        inventar = new PlayerInventory();
   
     //  inventar.addItem (new Item_IronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
        
    }

    public int getMaxSchild() {
		return maxSchild;
	}

	/**
     * Liefert den aktuellen Raum des Spielers zurück.
     */
    public  Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Setzt den aktuellen Raum des Spielers.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Versuche, in eine bestimmte Richtung zu laufen.
     * Wenn dort eine Tür ist, wird sich dadurch der aktuelle Raum des Spielers ändern.
     */
    public void walk(String direction)
    {
        // Try to leave current room.
    	Optional<Room> whereToGo = currentRoom.getExit(direction);

    	  if (whereToGo.isPresent()) {
          	
    		 Room room=whereToGo.get();
    		  
         	 setCurrentRoom(room);
             roomhistory.push(room);
             System.out.println(room.getLongDescription());
             System.out.println(room.getItemsinRoom()); 
         } 
         else {
   
             System.out.println("Da ist keine Tür!");
             
         }
    
    }



	/**
     * geht die jeweilige Anzahl an Räumen zurück.
     * @param rueckschritte
     * @return
     */
    public String goBack(int rueckschritte){


            if (roomhistory.size() == 1) {
            	System.out.println();
            	System.out.println("Du befindest dich im Startraum und kannst nicht weiter zurück gehen!");
                return String.valueOf(currentRoom);
            }
            else if(roomhistory.size()<=rueckschritte) {
            System.out.println();	
            System.out.println("Du kannst nicht "+rueckschritte+" Schritte zurück gehen!"); 
            int maximaleschritte=roomhistory.size()-1;
            System.out.println("Du kannst höchstens "+maximaleschritte +" Schritte zurück gehen!");
            return String.valueOf(currentRoom);
            
            }
            else if(rueckschritte<0) {
            System.out.println();	
            System.out.println("Bitte gebe eine positive Zahl ein!");	
            return String.valueOf(currentRoom);
            }
            else {
           	
            for(int a=0; a<rueckschritte; a++) {
            	roomhistory.pop();	
            }
            	
                setCurrentRoom(roomhistory.peek());
                System.out.println(currentRoom.getLongDescription());
                return String.valueOf(currentRoom);

            }
         
    }

    /**
     * Methode zum Benutzen von Items des Spielers
     * @param index
     * itemName
     */

    public void useItem(int index){
      
   if(this.inventar.getItem(index).isPresent()) {
	   
	   this.inventar.getItem(index).get().use(this);   
	   
   }
   else {
	 
	   System.out.println("Das Item ist nicht in deinem Inventar vorhanden!");   
	   
   }
    	
    }

    public String getItemDescription(int index){
        
    	return this.inventar.getItemDescription(index);

    }
    
    
    /**
     * Methode um Items aus einer Kiste dem Inventar des Spielers hinzuzufügen
     * @param list
     */
    public boolean getItemsFromChest(List<Item> list){

    	boolean itemsentnommen=false;
    	
    	if(list.isEmpty()) {
    		
    	System.out.println("Die Truhe ist leer!");
    		
    	}
    	else {
    		
    	List<Item> inventaritems=this.inventar.getItems();	
    		
    	for(Item item : list) {
    	   
    		 String itemname=item.getName();
    	        int itemanzahl= item.getAnzahl();
    	        int stackanzahl=item.getStackAnzahl();
    	        
    	        out:
    	    	for(Item inventaritem : inventaritems) {
    	        
    	    		if(itemname.equals(inventaritem.getName()) && inventaritem.getAnzahl()<inventaritem.getStackAnzahl()) {
    	        	
    	    		itemsentnommen=true;	
    	        	int menge=inventaritem.getAnzahl();
    	      
    	        	
    	        	if(menge<=(stackanzahl- itemanzahl)) {

    	        		inventaritem.setAnzahl(menge+itemanzahl);
    	        		item.setAnzahl(0);
    	        		  break out;
    	        		
    	        	}
    	        	else if(menge>(stackanzahl- itemanzahl)) {
    	        		
    	        		int hinzugefügteItems= stackanzahl-menge;
    	        		inventaritem.setAnzahl(stackanzahl);	
    	        		item.setAnzahl(item.getAnzahl()-hinzugefügteItems);
    	        	}
    	        	
    	     
    	        }	
    	    		
    	    		
    	    	}
    
    	        
    	        while(item.getAnzahl()!=0 && !this.inventar.isInventoryfull()) {
    	        
    	        	itemsentnommen=true;
    	        	
    	        if(item.getAnzahl()<=item.getStackAnzahl()) {
    	        	
    	        Item itemcopie=item.getCopie();	
    	        this.inventar.addItem(itemcopie);
    	        item.setAnzahl(0);
    	        
    	        }
    	        else {
    	        
    	        	Item itemcopie=item.getCopie();		
    	        	itemcopie.setAnzahl(item.getStackAnzahl());
    	        	this.inventar.addItem(itemcopie);
    	        	item.setAnzahl(item.getAnzahl()-item.getStackAnzahl());
    	        	
    	        }
    	        	
    	        }
    	        
    	        
    	        
    	        }
    	        	  	
    	   Iterator<Item> it = list.iterator();
    	    while(it.hasNext()) {
    	      Item i = it.next();
    	      if(i.getAnzahl()==0) {
    	        it.remove();
    	      }
    	    }
    	
 		 }	
    	
    	return itemsentnommen;
    	
    }

    /**
     * Methode zum Aufnehmen von Items in einen Raum
     * @param index
     */
    public boolean pickUpItem(int index){

    Optional<Item> optionalItem=this.currentRoom.getItem(index);
    Item item=null;
    if(optionalItem.isPresent()) {
    	
    	item= optionalItem.get();

		System.out.println("Aufheben Item: " + item.getName() + " Zustand: " + item.getItemzustand());
    }
    	
    if(this.currentRoom.noItemsinRoom())	{
    	
    	System.out.println("In diesem Raum liegen keine Items herum!");
    
    }
    else {
    		
    return	this.itemprüfung(item,index);

    }
	return false;
    	
    }

    
    public boolean itemprüfung(Item item, int listindex) {
    	
		// -->Schleife um nachzuschauen ob das Item schon im Inventar ist und die
		// maximale Stackanzahl schon erreicht ist.

		int itemmenge = item.getAnzahl();
		int itemmengenabgleich = item.getAnzahl();
		int index = 0;
		List<Item> items = this.inventar.getItems();

	while (itemmenge > 0 && index < items.size()) {
    		
    String itemnameInventar=items.get(index).getName();
    int itemanzahlInventarItem= items.get(index).getAnzahl();
    int stackanzahlInventarItem=items.get(index).getStackAnzahl();
	Integer itemzustandInventarItem = items.get(index).getItemzustand();
	int itemslot = index + 1;

	if (itemnameInventar.equals(item.getName()) && itemanzahlInventarItem < stackanzahlInventarItem
			&& itemzustandInventarItem == item.getItemzustand() && item.isStackbar()) {
    	
		if (itemmenge <= (stackanzahlInventarItem - itemanzahlInventarItem)) {
    		
			this.inventar.getItem(index).get().setAnzahl(itemanzahlInventarItem + itemmenge);
    		this.currentRoom.removeItem(item);

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Itemslot " + itemslot + " " + itemnameInventar + ": Es wurden " + itemmenge
					+ " Items hinzugefügt.\n Die nicht aufgehobene Itemanzahl beträgt: 0");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

			itemmenge = 0;

    	}
		else if (itemmenge > (stackanzahlInventarItem - itemanzahlInventarItem)) {
    		
    		int hinzugefügteItems= stackanzahlInventarItem-itemanzahlInventarItem;
    		this.inventar.getItem(index).get().setAnzahl(stackanzahlInventarItem);	
    		this.currentRoom.setItemAnzahl(listindex, hinzugefügteItems);
    		
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

			System.out.println("Itemslot " + itemslot + " " + itemnameInventar + ": Es wurden " + hinzugefügteItems
					+ " Items hinzugefügt.");

			itemmenge -= hinzugefügteItems;

			System.out.println("Die nicht aufgehobene Itemanzahl beträgt: " + itemmenge);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

    	}
    	
    }
    	
    index++;	
    }
    	
	// -> Ende Schleife

	// Falls sich das Item noch nicht im Inventar befindet oder es ist schon im
	// Inventar, aber die maximale Stack Anzahl wurde schon erreicht.
	// und im Iventar noch mindestens 1 Slot frei ist
    
	while (itemmenge > 0 && !this.inventar.isInventoryfull()) {
    	
		if (itemmenge <= item.getStackAnzahl()) {

			this.inventar.addItem(item);
			this.currentRoom.removeItem(item);

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println(item.getName() + ": Es wurden " + itemmenge + " Items zu einem freien Slot hinzugefügt.");

			itemmenge = 0;

			System.out.println("Die nicht aufgehobene Itemanzahl beträgt: " + itemmenge);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

		} else if (itemmenge > item.getStackAnzahl()
				&& item.getItemKategorie().equals(ItemKategorien.KAMPFITEM.toString())) {

			Item itemInInventory = item.getCopie();
			itemInInventory.setAnzahl(item.getStackAnzahl());
			this.inventar.addItem(itemInInventory);
			this.currentRoom.setItemAnzahl(listindex, item.getStackAnzahl());

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(item.getName() + "(Kampfitem): Es wurden " + item.getStackAnzahl()
					+ " Items zu einem freien Slot hinzugefügt.");

			itemmenge -= item.getStackAnzahl();

			System.out.println("Die nicht aufgehobene Itemanzahl beträgt: " + itemmenge);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

			return true;

		} else {

			Item itemInInventory = item.getCopie();
			itemInInventory.setAnzahl(item.getStackAnzahl());
			this.inventar.addItem(itemInInventory);
			this.currentRoom.setItemAnzahl(listindex, item.getStackAnzahl());

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(item.getName() + ": Es wurden " + item.getStackAnzahl()
					+ " Items zu einem freien Slot hinzugefügt.");

			itemmenge -= item.getStackAnzahl();

			System.out.println("Die nicht aufgehobene Itemanzahl beträgt: " + itemmenge);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

		}

    }
    
	if (itemmengenabgleich == itemmenge) {
		return false;
    }
	else {
		return true;
    }

    }
    
    public boolean kisteleeren(Item item, int listindex) {
    	
        boolean stop=false;	
        int index=0;
        List<Item> items=this.inventar.getItems();
        while(stop== false && index <items.size())	{
        		
        String itemname=items.get(index).getName();
        int itemanzahl= items.get(index).getAnzahl();
        int stackanzahl=items.get(index).getStackAnzahl();
        
        if(itemname.equals(item.getName()) && itemanzahl<stackanzahl) {
        	
        	int menge=item.getAnzahl();
        	stop=true;
        	
        	if(menge<=(stackanzahl- itemanzahl)) {
        		
        		this.inventar.getItem(index).get().setAnzahl(itemanzahl+item.getAnzahl());	
        		this.currentRoom.removeItem(item);
        	}
        	else if(menge>(stackanzahl- itemanzahl)) {
        		
        		int hinzugefügteItems= stackanzahl-itemanzahl;
        		this.inventar.getItem(index).get().setAnzahl(stackanzahl);	
        		this.currentRoom.setItemAnzahl(listindex, hinzugefügteItems);
        		
        	}
        	
        	return true;
        }
        	
        index++;	
        }
        	
        if(stop==false && !this.inventar.isInventoryfull()) {
        	
        if(item.getAnzahl()<=item.getStackAnzahl()) {
        	
        this.inventar.addItem(item);
        this.currentRoom.removeItem(item);	
        
        }
        else {
        	
        Item itemInInventory=item.getCopie();
        itemInInventory.setAnzahl(item.getStackAnzahl());	
        this.inventar.addItem(itemInInventory);
        this.currentRoom.setItemAnzahl(listindex,item.getStackAnzahl());
        	
        }
        
        return true;
        }
        else {
        	
        	return false;
        	
        }
    	
        	
        }
    
    
    
    /**
     * Methode zum Ablegen eines Items in einen Raum
     * @param index
     */
    public void dropItem(int index){

    	if(inventar.isInventoryempty()) {
	
    	    System.out.println("Dein Inventar ist leer!");		
	
    	}
    	else if(inventar.getItem(index).isPresent()) {
    		
    	Item item=inventar.getItem(index).get();
    	
    	for(Item roomitem: this.currentRoom.getItemsintheRoom()) {
    		
			if (roomitem.getName().equals(item.getName()) && roomitem.getItemzustand() == item.getItemzustand()) {
    			
    			roomitem.setAnzahl(roomitem.getAnzahl()+item.getAnzahl());
    			this.inventar.removeItems(item);
    			System.out.println(item.getName() +" wurde abgelegt!");
    			return;
    		}
    		
    	}
    	this.currentRoom.addItem(item);
    	this.inventar.removeItems(item);
    	
    	}
    	else{
    	
    		 System.out.println("Das Item ist nicht in deinem Inventar vorhanden!");
    		
    	}
    		
    }

    public void dropOneItem(int index){

    	if(inventar.isInventoryempty()) {
	
    	    System.out.println("Dein Inventar ist leer!");		
	
    	}
    	else if(inventar.getItem(index).isPresent()) {
    		

    	Item item=inventar.getItem(index).get();
    	
    	for(Item roomitem: this.currentRoom.getItemsintheRoom()) {
    		
			if (roomitem.getName().equals(item.getName()) && item.getAnzahl() > 1
					&& roomitem.getItemzustand() == item.getItemzustand()) {
    			
    			roomitem.setAnzahl(roomitem.getAnzahl()+1);
    			item.setAnzahl(item.getAnzahl()-1);
    			System.out.println(item.getName() +" wurde ein Mal abgelegt!");
    			return;
    		}
			else if (roomitem.getName().equals(item.getName()) && item.getAnzahl() == 1
					&& roomitem.getItemzustand() == item.getItemzustand()) {
    			
    			roomitem.setAnzahl(roomitem.getAnzahl()+1);
    			this.inventar.removeItems(item);
    			System.out.println(item.getName() +" wurde ein Mal abgelegt!");
    			return;
    		}
    		
    	}
    	
    	
    	if(item.getAnzahl()>1) {
    		
    		Item newroomitem=item.getCopie();
    		newroomitem.setAnzahl(1);
    		this.currentRoom.addItem(newroomitem);
    		item.setAnzahl(item.getAnzahl()-1);
    
    		
    	}
    	else {
    	
    		Item newroomitem=item.getCopie();
    		newroomitem.setAnzahl(1);	
    		this.currentRoom.addItem(newroomitem);
    		this.inventar.removeItems(item);
    	
    	}
    	
    	
    	}
    	else{
    	
    		 System.out.println("Das Item ist nicht in deinem Inventar vorhanden!");
    		
    	}
    		
    }
    
    
    
    
    
    public boolean isInventoryempty() {
    	
    	return this.inventar.isInventoryempty();
     	
    }
    
    public String inventarAusgeben() {
		
    	return this.inventar.inventarAusgeben();
    		
    }
    
    public void removeItems(Item item) {
    	
    this.inventar.removeItems(item);	
    	
    }
    
    public boolean contains(String secondWord) {
		
    	return this.inventar.contains(secondWord);
    		
    }
    
    public void addItem(Item item) {
    
    if(item!=null && !this.inventar.isInventoryfull() && item.getAnzahl()<=item.getStackAnzahl() && item.getAnzahl()>0) {
    	this.inventar.addItem(item);		
    }
    	
    }
    
    public List<String> getItemNames() {
    	
     return this.inventar.getItemNames();	
        	
    }
    
    public List<Item> getItems() {
    	
        return this.inventar.getItems();
           	
       }
    
    
    /**
     *
     * @param startraum
     */
    public void setStartraumtoRoomHistory(Room startraum) {
		
		this.roomhistory.push(startraum);
		
	}

    public int getRoomhistorySize() {
    	
    	return this.roomhistory.size();
    }
    
    /**
     *
     * @return gibt die aktuellen Lebenspunkte des Spielers zurück
     */
	public int getHp() {
		return hp;
	}

    /**
     * Setter für das Leben des Spielers
     * @param hp
     */
    public void setHp(int hp){this.hp=hp;}

    /**
     * Fügt dem Spieler Lebenspunkte hinzu
     * @param hp
     */
	public void addHp(int hp) {
		this.hp+=hp;
	}

	public void addSchild(int schild) {
		this.schild+=schild;
	}
	
    /**
     *
     * @return maximale Leben des Spielers
     */
    public int getMaxHp(){return maxHp;}

    /**
     * Setter für das maximale Leben des Spielers
      * @param maxHp
     */
    public void setMaxHp(int maxHp){this.maxHp=maxHp;}


    public int getSchild() {
		return schild;
	}

	public void setSchild(int schild) {
		this.schild = schild;
	}

	/**
     * Gibt die Anzahl der bereits umgelegten Schalter zurück
     * @return pressedLever
     */
	public int getPressedLever() {
		return this.pressedLever;
	}

    /**
     * Erhöht die Anzahl der umgelegten Schalter um 1
     */
	public void pressLever() {
		this.pressedLever++;
	}

	public void openchest() {
		this.openedChests++;
	}
	
	public void kill() {
		this.kills++;
	}
	
	public Opponent getGegner() {

        return this.currentRoom.getGegner();
    	
	}
	
	public Optional<Opponent> getOptionalGegner() {

        return this.currentRoom.getOptionalGegner();
    	
	}
	
	public void setUseItemText(String text) {

      this.useItemText=text;
    	
	}
	
	public String getUseItemText() {

	      return this.useItemText;
	    	
		}
	
	public int getAnzahlItems() {

	      return inventar.getInventorySize();
	    	
	}
	
    /**
     *
     */
    public void getDamaged(int damage){

    if(this.getSchild()>0 && this.schild>= damage) {
    	
    	this.schild-=damage;
    }
    else if(this.getSchild()>0 && this.schild< damage) {
    	
    	int verbleibenderDamage= damage-this.schild;
    	this.setSchild(0);
    	this.hp-=verbleibenderDamage;	
    	
    }
    else {
    	
    	 this.hp-=damage;
    }
   	
    }

    public boolean isAlive() {

        return this.hp > 0;
	
    }

	public int getOpenedChests() {
		return openedChests;
	}

	public int getKills() {
		return kills;
	}
    
    

}
