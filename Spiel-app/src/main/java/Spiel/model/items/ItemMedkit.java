package Spiel.model.items;

import Spiel.model.Player;

public class ItemMedkit extends Verbrauchs_Item {

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemMedkit(Integer anzahl) {
        super("Medkit", "Hiermit kannst du deine komplette Kondition wiederherstellen...", anzahl, 3,true);
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
       text+="Mit dem Medkit kannst du dich vollständig heilen."+"\n";
       if(player.getHp() == player.getMaxHp()){
           text+="Du bist bereits vollständig geheilt...";
       }else {
    	   player.setHp(player.getMaxHp());
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
