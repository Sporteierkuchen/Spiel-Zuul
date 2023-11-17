package Spiel.model.items;

import Spiel.model.Player;

public class ItemSaufkrug extends Verbrauchs_Item {



    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemSaufkrug(Integer anzahl) {
        super("Saufkrug", "Der legendäre Saufkrug heilt alles...", anzahl, 1,false);
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
     * Methode für das Benutzen des Items
     * @param player
     */
    @Override
    public void use(Player player) {
       String text = "";
       text+="Du kannst hiermit Leben und Schild komplett auffüllen."+"\n";
       if(player.getHp() == player.getMaxHp() && player.getSchild() == player.getMaxSchild()){
           text+="Du hast bereits volles Leben und Schild...";
       }else {
       
    	player.setHp(player.getMaxHp());
    	player.setSchild(player.getMaxSchild());   
     
    	 text+="Deine Trefferpunkte betragen nun: " + player.getHp() + "HP. Dein Schild beträgt nun: " + player.getSchild() + " Schild";   
    	
           if(getAnzahl()==1) {
        	   player.removeItems(this);
           }
           else {
        	 this.setAnzahl(this.getAnzahl()-1);
           }
           
       }
       player.setUseItemText(text);
    }


}
