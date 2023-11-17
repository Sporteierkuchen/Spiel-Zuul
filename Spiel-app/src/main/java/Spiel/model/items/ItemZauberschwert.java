package Spiel.model.items;

import Spiel.model.Player;

public class ItemZauberschwert extends Kampf_Item {

    /**
     * Schaden welches das Item verursacht
     */
      int damage=200;
      
    /**
     * Chance mit dem das Item doppelten Schaden anrichtet
     */
    int critChance;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */

    public ItemZauberschwert(Integer anzahl) {
    	
		super("Zauberschwert", "Dieses verzauberte Schwert kann gewaltigen Schaden anrichten...", anzahl);
    }
    
    /**
     *
     * @return Name des Items
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     *
     * @return Beschreibung des Items
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     *
     * @return Schaden den das Item anrichtet
     */
    public int calculateDamage(){

    	int critchancepos= ((int)(Math.random() * 10)); //Zahl zwischen 0 und 9
    	
    	if(critchancepos==0) {
    		
    		this.critChance=10;
    	}
    	if(critchancepos==1) {
    		
    		this.critChance=10;
    	}
    	else if(critchancepos==2) {
    		this.critChance=20;
    		
    	}
    	else if(critchancepos==3) {
    		this.critChance=30;
    		
    	}
    	else if(critchancepos==4) {
    		this.critChance=40;
    		
    	}
    	else if(critchancepos==5) {
    		this.critChance=50;
    		
    	}
    	else if(critchancepos==6) {
    		this.critChance=60;
    		
    	}
    	else if(critchancepos==7) {
    		this.critChance=70;
    		
    	}
    	else if(critchancepos==8) {
    		this.critChance=80;
    		
    	}
    	else {
    		this.critChance=90;
    		
    	}
    	
    	
    	
        int random = ((int)(Math.random() * 100)); //Zahl zwischen 0 und 99
        int damage=this.damage;
        
        if(random<=critChance){damage = damage*(int) (Math.random()*2+2);};

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Zauberschwert: " + damage + " Schaden");
		System.out.println("--------------------------------------------------------------------------");
        return damage;
    }


    /**
     * Methode für das Benutzen des Items
     * @param player
     */
    @Override
    public void use(Player player) {
    	
        String text = "";
       
    if(player.getOptionalGegner().isPresent()) {
    	player.getGegner().getDamage(calculateDamage());
    	System.out.println("Du hast den Gegner getroffen!");

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("FullHp Gegner: " + player.getGegner().getMaxHp());
		System.out.println("Hp Gegner: " + player.getGegner().getOpponentHp());
		System.out.println("--------------------------------------------------------------------------");

    	 text+="Du hast den Gegner getroffen!"+"\n"+player.getCurrentRoom().checkOpponent();	
    	
			this.changeItemzustand(this.calculateItemDamage());
			if (this.isItemDestroid()) {

				player.removeItems(this);
			}

    }
    else {
    	System.out.println("Du schlägst mit deinem Schwert ins Leere...");
    	text+="Du schlägst mit deinem Schwert ins Leere...";	
    }
    player.setUseItemText(text);
    
    }


	private int calculateItemDamage() {

		int max = 1000;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		if (rand >= 1 && rand < 100) {

			return this.calculateItemDamageBis5();
		} else if (rand >= 100 && rand < 200) {

			return this.calculateItemDamage5_10();
		} else if (rand >= 200 && rand <= 500) {

			return this.calculateItemDamage10_15();
		} else if (rand >= 500 && rand < 800) {

			return this.calculateItemDamage15_20();
		} else if (rand >= 800 && rand <= 900) {

			return this.calculateItemDamage20_25();
		} else if (rand >= 900 && rand < 1000) {

			return this.calculateItemDamage25_30();
		}
		return 0;

	}

}
