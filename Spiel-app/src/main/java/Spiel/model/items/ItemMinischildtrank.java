package Spiel.model.items;

import Spiel.model.Player;

public class ItemMinischildtrank extends Verbrauchs_Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int schildRestore=25;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemMinischildtrank(Integer anzahl) {
        super("Minischildtrank", "Damit kann dein Schild etwas erhöht werden...", anzahl, 6,true);
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
       text+="Du kannst hiermit dein Schild verstärken."+"\n";
       if(player.getSchild() >= player.getMaxSchild()-50){
           System.out.println("Mehr geht nicht...");
           text+="Mehr geht nicht...";
       }else {
           player.addSchild(schildRestore);
           if (player.getSchild() > player.getMaxSchild()-50) {
               player.setSchild(player.getMaxSchild()-50);
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
