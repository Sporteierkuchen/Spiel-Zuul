package Spiel.model.items;

import Spiel.model.Player;

public class ItemSchildtrank extends Verbrauchs_Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int schildRestore=50;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemSchildtrank(Integer anzahl) {
        super("Schildtrank", "Dieser Trank erhöht dein Schild...", anzahl, 3,true);
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
       System.out.println("Die Energie des Heiltrankes erfüllt dich mit neuem Leben.");
       String text = "";
       text+="Der Schildtrank bringt dir mehr Schild."+"\n";
       if(player.getSchild() == player.getMaxSchild()){
           System.out.println("Du hast bereits volles Schild!");
           text+="Du hast bereits volles Schild!";
       }else {
           player.addSchild(schildRestore);
           if (player.getSchild() > player.getMaxSchild()) {
               player.setSchild(player.getMaxSchild());
           }
        
           text+="Dein Schild beträgt nun: " + player.getSchild() + " Schild";
           
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
