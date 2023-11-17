package Spiel.model.items;

import Spiel.model.Player;

public class ItemSchluerfsaft extends Verbrauchs_Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int liveRestore=75;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemSchluerfsaft(Integer anzahl) {
        super("Schlürfsaft", "Dieser Saft kann Wunder bewirken...", anzahl, 2,true);
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
       text+="Du kannst hiermit Leben und Schild verstärken."+"\n";
       if(player.getHp() == player.getMaxHp() && player.getSchild() == player.getMaxSchild()){
           text+="Du hast bereits volles Leben und Schild...";
       }else {
       
    	   int verbleibendepunkte= this.liveRestore-(player.getMaxHp()-player.getHp());
    	   
           if (verbleibendepunkte>0) {
               player.setHp(player.getMaxHp());
               player.addSchild(verbleibendepunkte);
               
               if(player.getSchild()>player.getMaxSchild()) {
            	   player.setSchild(player.getMaxSchild());
               }
               text+="Deine Trefferpunkte betragen nun: " + player.getHp() + "HP. Dein Schild beträgt nun: " + player.getSchild() + " Schild";
               
           }
           else {
        	  
        	   player.addHp(verbleibendepunkte);
        	   text+="Deine Trefferpunkte betragen nun: " + player.getHp() + "HP";
        	   
           }
           
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
