package Spiel.model.items;

public abstract class Special_Item extends Item {

	public Special_Item(String itemName, String itemBeschreibung, int anzahl, int stackAnzahl, boolean stackbar,
			Integer itemzustand) {
		super(itemName, ItemKategorien.SPECIALITEM.toString(), itemBeschreibung, anzahl, stackAnzahl, stackbar,
				itemzustand);

	}

}
