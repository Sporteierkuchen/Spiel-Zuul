package Spiel.model.items;

import Spiel.model.Player;

public class ItemSporteierkuchen extends Special_Item {

    /**
     * Schaden welches das Item verursacht
     */
      int damage=1;

    /**
     * Chance mit dem das Item doppelten Schaden anrichtet
     */
    int critChance=3;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemSporteierkuchen(Integer anzahl) {
		super("Sporteierkuchen", "Dieser Eierkuchen wurde 3 mal pro Sekunde gewendet...", anzahl, 1, false, null);
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

        int random = ((int)(Math.random() * 100)); //Zahl zwischen 0 und 99

        
     int damage=this.damage;
        if(random<=critChance){damage = 10000;}

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sporteierkuchen: " + damage + " Schaden");
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

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("FullHp Gegner: " + player.getGegner().getMaxHp());
		System.out.println("Hp Gegner: " + player.getGegner().getOpponentHp());
		System.out.println("--------------------------------------------------------------------------");

    	System.out.println("Du hast den Gegner getroffen!");
    	 text+="Du hast den Gegner getroffen!"+"\n"+player.getCurrentRoom().checkOpponent();	
    	
    	
    }
    else {
    	System.out.println("Du wirfst den Sporteierkuchen gegen die Wand. Nichts geschieht");
    	text+="Du wirfst den Sporteierkuchen gegen die Wand. Nichts geschieht";	
    }
    player.setUseItemText(text);
    
    }


}
