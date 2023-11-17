package Spiel.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Spiel.model.items.Item;

public class PlayerInventory {
    /**
     * Liste für Items
     */
	private  ArrayList<Item> items = new ArrayList<>();

	private final int slotanzahl=5;
	
    /**
     * Gibt das Inventar des Spielers mit den enthaltenen Items aus
     * @return Inventar
     */
    public String inventarAusgeben() {
        for (Item i : items) {
            System.out.println(i.getName());

        }

        return String.valueOf(items);
    }

    /**
     * Fügt ein Item der Item-Liste hinzu
     * @param item
     */
    public void addItem(Item item) {
    
    	if(item!=null) {	
    	 this.items.add(item);
    	} 
    	
    }

    /**
     * Entfernt Items
     * @param item
     */
    public void removeItems(Item item) {

        items.removeIf(i -> i.equals(item));

    }

    /**
     *
     * @param secondWord
     * @return
     */
    public boolean contains(String secondWord) {
        for(Item item : items){
            if(item.getName().equals(secondWord)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt zurück, ob das Inventar des Spielers leer ist
     * @return
     */
    public boolean isInventoryempty() {
    	
   return this.items.isEmpty();
    
    }
    
    public boolean isInventoryfull() {
    	
    	if(this.items.size()==this.slotanzahl) {
    		
    		return true;
    		
    	}
    	else {
    		
    		return false;
    	}
    	
    	
    }
    

    /**
     * Benutzen eines Items aus dem Inventar des Spielers
     * @param itemName
     * @return
     */
    public Optional<Item> useItemfromInventory(String itemName) {
    	
        for(Item item : items){
        	
            if(item.getName().equals(itemName)){
  	
                return Optional.ofNullable (item);
            
            }
    	}

        return Optional.empty();

    }
    
    
    /**
     * Ermitteln aller Itemnamen im Inventar
     * @return Liste mit allen Itemnamen
     */   
    public List<String> getItemNames() {
	
	List <String>itemNamesList=new ArrayList<>();
	
	for(Item item: this.items) {
		itemNamesList.add(item.getName());
	}
	return itemNamesList;
	
    }
    
    
    public List<Item> getItems() {
    	
	List <Item>itemNamesList=new ArrayList<>();
	
	for(Item item: this.items) {
		itemNamesList.add(item);
	}
	return itemNamesList;
	
    }
    
    
    public List<Integer> getItemAnzahlen() {
    	
	List <Integer>itemNamesList=new ArrayList<>();
	
	for(Item item: this.items) {
		itemNamesList.add(item.getAnzahl());
	}
	return itemNamesList;
	
    }
    
    
    
    /**
     * Ermitteln der Itembeschreibung eines Items
     *  * @param int index
     * @return String itemdescription
     */  
   public String getItemDescription(int index) {  
	   
	    try {
     	  return items.get(index).getDescription();  
     	}
     	catch(IndexOutOfBoundsException e) {
     		return null;
     	}
	   
	   
	   
   }
  
   
   /**
    * Gibt ein Item aus dem Spielerinventar heraus
    *  * @param int index
    * @return Optional, welches möglicherweise ein Item enthält
    */  
   public Optional<Item> getItem(int index) {  
	   	
	   try {
    	  return Optional.ofNullable (items.get(index));
	   }
	   catch(IndexOutOfBoundsException e) {
  		return Optional.empty();
	   }
      
   }
   
   public int getInventorySize() {  
	   
		 return items.size();
		   
   }

public int getSlotanzahl() {
	return slotanzahl;
}
   
   
}
