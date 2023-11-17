package Spiel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Spiel.model.items.Item;
import Spiel.model.items.ItemBandage;
import Spiel.model.items.ItemDiamantschwert;
import Spiel.model.items.ItemHealthPotion;
import Spiel.model.items.ItemHolzschwert;
import Spiel.model.items.ItemIronSword;
import Spiel.model.items.ItemMedkit;
import Spiel.model.items.ItemMinischildtrank;
import Spiel.model.items.ItemSaufkrug;
import Spiel.model.items.ItemSchildtrank;
import Spiel.model.items.ItemSchluerfsaft;
import Spiel.model.items.ItemSporteierkuchen;
import Spiel.model.items.ItemStock;
import Spiel.model.items.ItemZauberschwert;

/**
 * 
 * Die Klasse "Room" stellt einen Raum (Ort) in der Spielewelt dar.
 * Die Räume sind miteinander durch Türen verbunden.
 * In jedem Raum sind in der HashMap exits die Ausgänge in alle möglichen Richtungen
 * als Referenz auf den dann erreichten Zielraum gespeichert.
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

public class Room 
{
	
	 /**
     * Beschreibung des Raumes
     */
    private String description;
    
    /**
     * Diese HashMap speichert die Ausgänge des Raumes.
     * Der Schlüssel ist die Bewegungsrichtung, der Eintrag dazu der bei Bewegung in diese
     * Richtung erreichte Raum.
     */
    private HashMap<String, Room> exits;
    private List<Item> itemlist;
    private Hebel hebel;
    private Chest truhe;
    private Opponent gegner;
    
    private int roomId;
    
    /**
     * Konstruktor der Klasse Raum.
     * Lege einen Raum mit einer Raumbeschreibung (description) an.
     * Die Raumbeschreibung ist so etwas wie
     * "der Fahrstuhl" oder "die Herrentoilette".
     * Der neu erzeugte Raum hat noch keine Ausgänge.
     * @param description Die Raumbeschreibung.
     */
    public Room(String description, int roomId) 
    {
        this.description = description;
        this.roomId=roomId;
        exits = new HashMap<String, Room>();
        itemlist =new ArrayList<>();
    }

    /**
     * Lege die Ausgänge des Raumes fest.
     * @param direction Bewegungsrichtung.
     * @param neighbor Der bei Bewegung in diese Richtung erreichte Zielraum.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Gib die Raumbeschreibung zurück, so wie sie im Konstruktor angegeben wurde.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Gib eine längere Beschreibung des Raumes zurück. Beispiel-Format:
     *     Deine Position: die Herrentoilette.
     *     Ausgänge: Norden Westen.
     */
    public String getLongDescription()
    {
        return "Deine Position: " + description + ".\n" + getExitString();
    }
    
    /**
     * Gib einen String zurück, der die Ausgänge des Raumes beschreibt.
     * Beispiel:
     * Ausgänge: Norden Westen.
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Ausgänge:");
        Set<String> keys = exits.keySet();
        for (String key : keys) returnString.append(" ").append(key);

        //for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
         //   returnString += " " + iter.next();

        if(keys.isEmpty()) {
        return returnString.toString()+" Keine";
        }
        
        return returnString.toString();
    }

   
    
    /**
     * Gib ein Optional Objekt zurück, welches möglicherweise einnen Raum enthält, der bei Bewegung in eine Richtung erreicht wird
     * @param direction Bewegungsrichtung.
     * @return Optional mit  erreichtem Zielraum, Leeres Optional, wenn es in die angegebene Richtung keinen Ausgang gibt.
     */
    public Optional<Room> getExit(String direction) 
    {

        return Optional.ofNullable (exits. get(direction));
       // Optional<Room > optionalRoom =Optional.ofNullable (exits. get(direction));
        // return optionalRoom;

    }

    public void addItem(Item item) 
    {
       
    if(item!=null) {	
    this.itemlist.add(item);
    }
    }
    
    public void removeItem(Item item) {
        itemlist.removeIf(i -> i.equals(item));  
    }
    
    public Optional<Item> getItem(int index) {
    	 
        	try {
        	    return Optional.of(itemlist.get(index));
        	}
        	catch(IndexOutOfBoundsException e) {
        		return Optional.empty();
        	}
   
	}

    /**
     * Gib eine längere Beschreibung des Raumes zurück. Beispiel-Format:
     *     Deine Position: die Herrentoilette.
     *     Ausgänge: Norden Westen.
     */
    public String getItemsinRoom()
    {
        
    	String returnString = "Items in diesem Raum: ";
      
    	int index=0;
    	while(index<this.itemlist.size()) {
    		
    		if(index==itemlist.size()-1) {
    			
				if (itemlist.get(index).getItemzustand() != null && itemlist.get(index).getItemzustand() != 100) {
					returnString += itemlist.get(index).getAnzahl() + "x " + itemlist.get(index).getName() + "("
							+ itemlist.get(index).getItemzustand() + ")";
				} else {
					returnString += itemlist.get(index).getAnzahl() + "x " + itemlist.get(index).getName();
				}
    			
    		}
    		else {

				if (itemlist.get(index).getItemzustand() != null && itemlist.get(index).getItemzustand() != 100) {
					returnString += itemlist.get(index).getAnzahl() + "x " + itemlist.get(index).getName() + "("
							+ itemlist.get(index).getItemzustand() + ") ,  ";
				} else {
					returnString += itemlist.get(index).getAnzahl() + "x " + itemlist.get(index).getName() + " ,  ";
				}

    		}
    		
    		index++;
    		
    	}
    	
        if(this.itemlist.isEmpty()) {
        	
        	return returnString += "Keine" ;
        	
        }
        
        return returnString;
    }

    
    public List<String> getItemNamesinRoom()
    {
        
    	List<String> itemnames= new ArrayList<>();
      
        for(Item item : this.itemlist) {
            itemnames.add(item.getName());
        }
        
        return itemnames;
    }
    
    public List<Item> getItemsintheRoom()
    {
        
    	List<Item> itemnames= new ArrayList<>();
      
        for(Item item : this.itemlist) {
            itemnames.add(item);
        }
        
        return itemnames;
    }
    
    public void setItemAnzahl(int index, int anzahlWeggenommen)
    {
        
    	int itemmenge=this.itemlist.get(index).getAnzahl();
    	this.itemlist.get(index).setAnzahl(itemmenge-anzahlWeggenommen);
    	
    	
    }
    
    
    
    /**
     *
     * @return true, wenn Hebel vorhanden ist false, wenn kein Schalter vorhanden ist
     */
    public Optional<Hebel> getHebel() {

        return Optional.ofNullable (this.hebel);
    	
    }

    /**
     *
     * @return Zustand des Hebels
     */
    public boolean leverAvailable() {
        if (getHebel().isPresent()) {
            return !getHebel().get().getZustand();

            // if (!getSchalter().get().getZustand()) {
             //   return true;}
        }
        
        return false;
    }

    /**
     * Setter für Hebel
     * @param hebel
     */
    public void setHebel(Hebel hebel) {
    	
    	if(hebel!=null) {	
    	  this.hebel = hebel;
    	  }	
        
    }

    /**
     *
     * @return true, wenn kein Item im Raum vorhanden ist false, wenn ein Item im Raum vorhanden ist
     */
    public boolean noItemsinRoom() {
    	
    return this.itemlist.isEmpty();
    	
    }

    /**
     *
     * @return true, wenn Truhe vorhanden ist false, wenn keine Truhe vorhanden ist
     */
    public Optional<Chest> getChest() {

        return Optional.ofNullable (this.truhe);	
    }

    /**
     * Setter für Truhe
     * @param chest
     */
    public void setChest(Chest chest) {
    	
    	if(chest!=null) {	
    		this.truhe=chest;
      	  }	
    	     
    }

    /**
     *
     * @return true, wenn Gegner vorhanden false, wenn kein Gegner vorhanden ist
     */
	public Optional<Opponent> getOptionalGegner() {

        return Optional.ofNullable (this.gegner);
    	
	}

	public Opponent getGegner() {

        return this.gegner;
    	
	}
	
	
	
    /**
     * Setter für Gegner
     * @param gegner
     */
	public void setGegner(Opponent gegner) {
		
    	if(gegner!=null) {	
    		this.gegner = gegner;
      	  }	
		
	}

    /**
     *
     * @return true, wenn ein Gegner vorhanden ist false, wenn kein Gegner vorhanden ist
     */
    public boolean gegnerVorhanden() {
    return getOptionalGegner().isPresent();
}

    
    
    /**
     * Überprüft, ob ein Gegner noch am Leben ist und gibt seine verbleibenden Lebenspunkte aus
     */
    public String checkOpponent() {

if(gegnerVorhanden() && !this.gegner.isAlive())	{

System.out.println("Der Gegner "+this.gegner.getOpponentName()+" ist gefallen!");
String opponentName=this.gegner.getOpponentName();

setOpponentItems(this.gegner, this.gegner.getStärke());

for(Item item: this.getGegner().dropAllItems()) {

	Item raumitem=this.ItemVorhanden(item.getName());
	if(raumitem!=null){
		
		raumitem.setAnzahl(raumitem.getAnzahl()+item.getAnzahl());
		
	}
	else {
		this.itemlist.add(item);
	}
	
}

this.gegner=null;

return "Der Gegner "+opponentName+" ist gefallen!";

}
else {
System.out.println("Der Gegner "+this.gegner.getOpponentName()+" hat noch "+this.gegner.getOpponentHp()+" HP.");
return "Der Gegner "+this.gegner.getOpponentName()+" hat noch "+this.gegner.getOpponentHp()+" HP.";
}

}

    public Item ItemVorhanden(String itemname) {
  
    	for(Item item : this.itemlist) {
    		
    		if(item.getName().equals(itemname)) {
    			
    			return item;
    		}
    		
    	}
		return null;
    		
    }    
    
    
	public int getRoomId() {
		return roomId;
	}

	private Item getwhiteItem() {

		int max = 2;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		switch (rand) {

		case 1:
			return new ItemStock(1);
		case 2:
			return new ItemBandage(5);

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

		int rand = (int) (Math.random() * range) + min;

		switch (rand) {

		case 1:
			return new ItemHolzschwert(1);
		case 2:
			return new ItemHealthPotion(1);
		case 3:
			return new ItemMedkit(1);
		case 4:
			return new ItemMinischildtrank(3);

		default:
			System.out.println("Keinen Hunger?");
			break;
		}
		return null;

	}

	private Item getblueItem() {

		int max = 2;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		switch (rand) {

		case 1:
			return new ItemIronSword(1);
		case 2:
			return new ItemSchildtrank(1);

		default:
			System.out.println("Keinen Hunger?");
			break;
		}
		return null;

	}

	private Item getlilaItem() {

		int max = 2;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		switch (rand) {

		case 1:
			return new ItemDiamantschwert(1);
		case 2:
			return new ItemSchluerfsaft(1);

		default:
			System.out.println("Keinen Hunger?");
			break;
		}
		return null;

	}

	private Item getgoldItem() {

		int max = 3;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		switch (rand) {

		case 1:
			return new ItemZauberschwert(1);
		case 2:
			return new ItemSaufkrug(1);
		case 3:
			return new ItemSporteierkuchen(1);

		default:
			System.out.println("Keinen Hunger?");
			break;
		}
		return null;

	}

	private Item getItemGegner1() {

		int max = 1000;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		if (rand >= 1 && rand < 325) {

			return this.getwhiteItem();
		} else if (rand >= 325 && rand < 650) {

			return this.getlightblueItem();
		} else if (rand >= 650 && rand < 850) {

			return this.getblueItem();
		} else if (rand >= 850 && rand < 950) {

			return this.getlilaItem();
		} else if (rand >= 950 && rand <= 1000) {

			return this.getgoldItem();
		}
		return null;

	}

	private Item getItemGegner2() {

		int max = 1000;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		if (rand >= 1 && rand < 250) {

			return this.getwhiteItem();
		} else if (rand >= 250 && rand < 500) {

			return this.getlightblueItem();
		} else if (rand >= 500 && rand < 800) {

			return this.getblueItem();
		} else if (rand >= 800 && rand < 925) {

			return this.getlilaItem();
		} else if (rand >= 925 && rand <= 1000) {

			return this.getgoldItem();
		}
		return null;

	}

	private Item getItemGegner3() {

		int max = 1000;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		if (rand >= 1 && rand < 225) {

			return this.getwhiteItem();
		} else if (rand >= 225 && rand < 475) {

			return this.getlightblueItem();
		} else if (rand >= 475 && rand < 725) {

			return this.getblueItem();
		} else if (rand >= 725 && rand < 925) {

			return this.getlilaItem();
		} else if (rand >= 925 && rand <= 1000) {

			return this.getgoldItem();
		}
		return null;

	}

	private Item getItemGegner4() {

		int max = 1000;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		if (rand >= 1 && rand < 125) {

			return this.getwhiteItem();
		} else if (rand >= 125 && rand < 250) {

			return this.getlightblueItem();
		} else if (rand >= 250 && rand < 650) {

			return this.getblueItem();
		} else if (rand >= 650 && rand < 900) {

			return this.getlilaItem();
		} else if (rand >= 900 && rand <= 1000) {

			return this.getgoldItem();
		}
		return null;

	}

	private void setOpponentItems(Opponent o, int stärke) {

		int max = 100;
		int min = 1;
		int range = max - min + 1;
		int rand = (int) (Math.random() * range) + min;
		int anzahlitems = 0;

		if (rand >= 1 && rand < 25) {

			anzahlitems = 2;
		} else if (rand >= 25 && rand < 65) {

			anzahlitems = 3;
		} else if (rand >= 65 && rand < 90) {

			anzahlitems = 4;
		} else if (rand >= 90 && rand <= 100) {

			anzahlitems = 5;
		}

		switch (stärke) {

		case 1:

			for (int i = 0; i < anzahlitems; i++) {

				o.addItem(getItemGegner1());
			}

			break;

		case 2:

			for (int i = 0; i < anzahlitems; i++) {

				o.addItem(getItemGegner2());
			}

			break;

		case 3:

			for (int i = 0; i < anzahlitems; i++) {

				o.addItem(getItemGegner3());
			}

			break;

		case 4:

			for (int i = 0; i < anzahlitems; i++) {

				o.addItem(getItemGegner4());
			}

			break;

		default:
			System.out.println("Keinen Hunger?");
			break;

		}

	}

}