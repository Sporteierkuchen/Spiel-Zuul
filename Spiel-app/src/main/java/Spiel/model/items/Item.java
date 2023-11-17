package Spiel.model.items;

import Spiel.model.Player;

public abstract class Item implements Cloneable {

    /**
     * Name des Items
     */
    private final String name;
    /**
     * Beschreibung des Items
     */
	private final String itemKategorie;
    private final String beschreibung;
	private int anzahl;
	private final int stackAnzahl;
	private final boolean stackbar;
	private Integer itemzustand;
    
    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
	public Item(String itemName, String itemKategorie, String itemBeschreibung, int anzahl, int stackAnzahl,
			boolean stackbar,
			Integer itemzustand) {

        this.name = itemName;
		this.itemKategorie = itemKategorie;
        this.beschreibung = itemBeschreibung;
        this.anzahl=anzahl;
        this.stackAnzahl=stackAnzahl;
        this.stackbar= stackbar;
		this.itemzustand = itemzustand;
    }

    /**
     * @return Name des Items
     */
    public String getName() {
        return name;
    }

    public Item getCopie() {
        try {
			return (Item) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehler");
		}
		return null;
    }


    /**
     *
     * @return Beschreibung des Items
     */
    public String getDescription() {
        return beschreibung;
    }

	public abstract void use(Player player);

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getStackAnzahl() {
		return stackAnzahl;
	}

	public boolean isStackbar() {
		return stackbar;
	}

	public Integer getItemzustand() {
		return itemzustand;
	}

	public void changeItemzustand(Integer damage) {

		if (this.itemzustand != null) {

			if (damage > 0) {

				this.itemzustand -= damage;

			} else {

				System.out.println("Der Schaden den das Item erleiden soll, darf nicht negativ oder 0 sein!");
			}

		} else {
			System.out.println(
					"Der Itemzustand wurde mit null initialisiert! Wenn das so ist darf er nicht mehr geändert werden!");
		}

	}
	
	public boolean isItemKomplettNeu() {

		return this.itemzustand == 100;

	}

	protected boolean isItemDestroid() {

		return this.itemzustand <= 0;

	}

	public String getItemKategorie() {
		return itemKategorie;
	}

}
