package Spiel.model.items;

public abstract class Verbrauchs_Item extends Item {

	public Verbrauchs_Item(String itemName, String itemBeschreibung, int anzahl, int stackAnzahl, boolean stackbar) {

		super(itemName, ItemKategorien.VERBRAUCHSITEM.toString(), itemBeschreibung, anzahl, stackAnzahl, stackbar,
				null);

	}

	
}
