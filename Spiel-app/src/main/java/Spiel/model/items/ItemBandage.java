package Spiel.model.items;

import Spiel.model.Player;

public class ItemBandage extends Verbrauchs_Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int hpRestore=15;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemBandage(Integer anzahl) {
        super("Verband", "Bringt ein wenig Heilung...", anzahl, 10,true);
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
       System.out.println("Du kannst dich mit Verbänden etwas heilen.");
       String text = "";
       text+="Du kannst dich mit Verbänden etwas heilen."+"\n";
       if(player.getHp() >= player.getMaxHp()-25){
           System.out.println("Mehr geht nicht...");
           text+="Mehr geht nicht...";
       }else {
           player.addHp(hpRestore);
           if (player.getHp() > player.getMaxHp()-25) {
               player.setHp(player.getMaxHp()-25);
           }
           System.out.println("Deine Trefferpunkte betragen nun:" + player.getHp() + "");
           text+="Deine Trefferpunkte betragen nun: " + player.getHp() + "HP";
           
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
