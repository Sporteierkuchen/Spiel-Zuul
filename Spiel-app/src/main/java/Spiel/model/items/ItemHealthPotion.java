package Spiel.model.items;

import Spiel.model.Player;

public class ItemHealthPotion extends Verbrauchs_Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int hpRestore=40;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemHealthPotion(Integer anzahl) {
        super("Heiltrank", "Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte...", anzahl, 4,true);
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
       text+="Die Energie des Heiltrankes erfüllt dich mit neuem Leben."+"\n";
       if(player.getHp() == player.getMaxHp()){
           System.out.println("Du hast bereits volles Leben!");
           text+="Du hast bereits volles Leben!";
       }else {
           player.addHp(hpRestore);
           if (player.getHp() > player.getMaxHp()) {
               player.setHp(player.getMaxHp());
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
